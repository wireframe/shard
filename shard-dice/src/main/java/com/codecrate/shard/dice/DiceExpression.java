/*
 * Copyright 2004 codecrate consulting
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package com.codecrate.shard.dice;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.nfunk.jep.JEP;
import org.nfunk.jep.ParseException;
import org.nfunk.jep.function.PostfixMathCommand;


/**
 * parses a dice expression into a set of dice.
 * ex: 1d4+2
 *
 * @author <a href="mailto:wireframe@dev.java.net">Ryan Sonnek</a>
 */
public class DiceExpression extends DiceSupport implements Dice {
  private static final String WEAPON_TOKEN = "\\[W\\]";
	private static final int MODE_MINIMUM = -1;
	private static final int MODE_RANDOM = 0;
	private static final int MODE_MAXIMUM = 1;

    private final String expression;
    private final String functionExpression;
    private final Map<String, Double> variables;

    public DiceExpression(String diceExpression, Map<String, Double> variables, Dice weapon) {
        this.expression = stripSpaces(diceExpression);
        this.functionExpression = parseExpression(expression, weapon);
        this.variables = variables;

        roll(MODE_RANDOM);
    }

    private int roll(int mode) {
        JEP parser = new JEP();
        parser.addStandardFunctions();
        parser.addStandardConstants();
        parser.addFunction("d", new DiceFunction());
        parser.addVariable("m", mode);
        parser.setImplicitMul(true);
        
        for (String variable : variables.keySet()) {
          parser.addVariable(variable, variables.get(variable));
        }
        parser.parseExpression(functionExpression);

        if (parser.hasError()) {
            throw new IllegalArgumentException("Error parsing dice expression [" + expression + "] into [" + functionExpression + "] " + parser.getErrorInfo());
        }
        return (int) parser.getValue();
	}

    private String stripSpaces(String diceExpression) {
        return diceExpression.replaceAll(" ", "");
    }
    
    /**
     * convert the input expression into a JEP expression
     */
    private String parseExpression(String expression, Dice weapon) {
      expression = replaceWeaponExpression(expression, weapon);
      return convertToFunctionExpression(expression);
    }
    
    /**
     * replace the [W] placeholder with the dice expression used for the current weapon. 
     */
    private String replaceWeaponExpression(String expression, Dice weapon) {
      if (weapon == null) {
        return expression;
      }
      return expression.replaceAll(WEAPON_TOKEN, weapon.toString());
    }

    /**
     * convert dice expressions to JEP function expressions.
     * example: change 1d6 to d(1, 6, m)
     * @param expression
     * @return
     */
    private String convertToFunctionExpression(String expression) {
        String patternStr = "(\\d*)d(\\d*)";
        String replaceStr = "d($1, $2, m)";
        Pattern pattern = Pattern.compile(patternStr);
        Matcher matcher = pattern.matcher(expression);

        String newExpression = matcher.replaceAll(replaceStr);
        return addDefaultTimes(newExpression);
    }

    /**
     * the regular expression will add "invalid" params, and this method adds a default value.
     * ex: d(, 4, m) changes to d(1, 4, m)
     */
	private String addDefaultTimes(String newExpression) {
		return newExpression.replaceAll("\\(,", "\\(1,");
	}

    public int getMaxValue() {
    	return roll(MODE_MAXIMUM);
    }

    public int getMinValue() {
    	return roll(MODE_MINIMUM);
    }

    public int roll() {
    	return roll(MODE_RANDOM);
    }

    public String toString() {
        return expression;
    }


    private static class DiceFunction extends PostfixMathCommand {
        public DiceFunction() {
            numberOfParameters = 3;
        }

        public void run(Stack inStack) throws ParseException {
            Double mode = (Double) inStack.pop();
            Double sides = (Double) inStack.pop();
            Double times = (Double) inStack.pop();

			Dice dice = new MultipleDice(new RandomDice(sides.intValue()), times.intValue());
			if (MODE_MINIMUM == mode.intValue()) {
				dice = new MinValueDice(dice);
			} else if (MODE_MAXIMUM == mode.intValue()) {
				dice = new MaxValueDice(dice);
			}
            inStack.push(new Double(dice.roll()));
        }
    }
}
