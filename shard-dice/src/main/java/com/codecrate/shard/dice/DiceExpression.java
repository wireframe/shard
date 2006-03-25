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
    private final String expression;
    private final String functionExpression;
    private final JEP parser = new JEP();

    public DiceExpression(String diceExpression) {
        this.expression = stripSpaces(diceExpression);
        this.functionExpression = convertToFunctionExpression(diceExpression);

        parser.addStandardFunctions();
        parser.addStandardConstants();
        parser.addFunction("d", new DiceFunction());
        parser.setImplicitMul(true);
        parser.parseExpression(functionExpression);

        if (parser.hasError()) {
            throw new IllegalArgumentException(parser.getErrorInfo());
        }
    }

    private String stripSpaces(String diceExpression) {
        return diceExpression.replaceAll(" ", "");
    }

    /**
     * convert dice expressions to JEP function expressions.
     * example: change 1d6 to 1d(6)
     * @param expression
     * @return
     */
    private String convertToFunctionExpression(String expression) {
        String patternStr = "d(\\d*)";
        String replaceStr = "d($1)";
        Pattern pattern = Pattern.compile(patternStr);
        Matcher matcher = pattern.matcher(expression);

        String newExpression = matcher.replaceAll(replaceStr);
        return newExpression.replaceAll(" ", "");
    }

    public int getMaxValue() {
        String patternStr = "d\\((\\d*)\\)";
        String replaceStr = "($1)";
        Pattern pattern = Pattern.compile(patternStr);
        Matcher matcher = pattern.matcher(functionExpression);

        String maximumString = matcher.replaceAll(replaceStr);

        JEP parser = new JEP();
        parser.addStandardFunctions();
        parser.addStandardConstants();
        parser.addFunction("d", new DiceFunction());
        parser.setImplicitMul(true);
        parser.parseExpression(maximumString);

        return (int) parser.getValue();
    }
    
    public int getMinValue() {
        String patternStr = "d\\(\\d*\\)";
        String replaceStr = "y";
        Pattern pattern = Pattern.compile(patternStr);
        Matcher matcher = pattern.matcher(functionExpression);

        String minimumString = matcher.replaceAll(replaceStr);

        JEP parser = new JEP();
        parser.addStandardFunctions();
        parser.addStandardConstants();
        parser.addFunction("d", new DiceFunction());
        parser.setImplicitMul(true);
        parser.addVariable("y", 1);
        parser.parseExpression(minimumString);

        return (int) parser.getValue();
    }
    
    public int roll() {
        return (int) parser.getValue();
    }
    
    public String toString() {
        return expression;
    }


    private class DiceFunction extends PostfixMathCommand {
        public DiceFunction() {
            numberOfParameters = 1;
        }

        public void run(Stack inStack) throws ParseException {
            Double param = (Double) inStack.pop();

            RandomDice dice = new RandomDice(param.intValue());
            inStack.push(new Double(dice.roll()));
        }

    }
}
