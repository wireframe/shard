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
package com.codecrate.shard.equipment;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Currency;
import java.util.Locale;

/**
 * An Object representing Money.  
 * 
 * @author <a href="mailto:wireframe@dev.java.net">Ryan Sonnek</a>
 * 
 * @see java.util.Currency
 * @see http://members.shaw.ca/jagarnett/Files/Money_java.html
 */
public class Money implements Comparable {
    private static final int[] cents = new int[] { 1, 10, 100, 1000 };

    private static NumberFormat nf = NumberFormat.getInstance();
    static {
        if (nf instanceof DecimalFormat) {
            DecimalFormat format = (DecimalFormat) nf;
            format.applyPattern("#,##0.00 ¤¤");
        }
    }
    
    /**
     * You may wish to override this with a known currency of your choosing
     */
    private static Currency LOCAL_CURRENCY = Currency.getInstance(Locale.getDefault());

    /**
     * Amount of money in currency.
     * <blockquote>
     *   'If you give us $92,233,720,368,547,758.09 we'll write you a version that
     *   uses BigInteger"<br>
     *   -Martin Fowler
     * </blockquote>
     */
    private long amount;

    /**
     * Currency amount of money is in.
     */
    private Currency currency;

    /**
     * Creates a new money of the provided amount and currency.
     * <p>
     * Example: <code>new Money( 1.48, Currency.getInstance("USD") )</code>
     * </p>
     * @param amount Amount of Money.
     * @param currency Currency Money is to be measured in.
     */
    public Money(double amount, Currency currency) {
        this.currency = currency;
        this.amount = Math.round(amount * centFactor());
    }

    /**
     * Creates a new money of the provided amount and currency.
     * 
     * <p>
     * Example: <code>new Money( 12, Currency.getInstance("CAD") )</code>
     * </p>
     * 
     * @param amount Amount of Money.
     * @param currency Currency Money is to be measured in.
     */
    public Money(long amount, Currency currency) {
        this.currency = currency;
        this.amount = amount * centFactor();
    }

    /**
     * Creates a new money of the provided amount and currency.
     * 
     * User defined rounding mode is used - this method is illuded to in
     * Fowlers book if not actually defined there.
     */
    public Money(BigDecimal amount, Currency currency, int roundingMode) {
        this.currency = currency;
        amount = amount.movePointRight(currency.getDefaultFractionDigits());
        amount = amount.setScale(0, roundingMode);
        this.amount = amount.longValue();
    }

    /**
     * Amount accessor.
     */
    public BigDecimal getAmount() {
        return BigDecimal.valueOf(amount, currency.getDefaultFractionDigits());
    }

    /**
     * Currency accessor.
     */
    public Currency getCurrency() {
        return currency;
    }

    /**
     * Money instances must have same currency and amount to be equal.
     * 
     * @param other
     * @return boolean
     * @see java.lang.Object#equals(Object)
     */
    public boolean equals(Object other) {
        return (other instanceof Money) && equals((Money) other);
    }

    /**
     * Money instances must have same currency and amount to be equal.
     * 
     * @param other
     * @return boolean
     * @see java.lang.Object#equals(Object)
     */
    public boolean equals(Money other) {
        return currency.equals(other.currency) && (amount == other.amount);
    }

    /**
     * Hash value based on amount.
     * 
     * Possibly should include currancy information in order to be consistent
     * with equals implementation.
     * 
     * @see java.lang.Object#hashCode()
     */
    public int hashCode() {
        return (int) (amount ^ (amount >>> 32));
    }

    /**
     * Simple addition ensuring matched Currency.
     * 
     * @param other
     * @return Money
     */
    public Money add(Money other) {
        assertSameCurrencyAs(other);
        return newMoney(amount + other.amount);
    }

    /**
     * Simple addition ensuring matched Currency.
     * 
     * @param other
     * @return Money
     */
    public Money subtract(Money other) {
        assertSameCurrencyAs(other);
        return newMoney(amount - other.amount);
    }

    /**
     * Comparison of Money objects used by Comparable interface.
     * This method allows Money to be sorted.
     * 
     * @param other
     * @return Boolean int -1 if less than, 1 if greater than and 0 if equal to other
     * @throws ClassCastException if other is not a Money
     * @throws AssertionFailure if other Money is not of the same Currency
     * @see java.lang.Comparable#compareTo(java.lang.Object)
     */
    public int compareTo(Object other) {
        return compareTo((Money) other);
    }

    /**
     * Comparison of Money objects.
     * 
     * @param other
     * @return Boolean int -1 if less than, 1 if greater than and 0 if equal to other
     * @throws AssertionFailure if other Money is not of the same Currency
     * @see java.lang.Comparable#compareTo(Object)
     */
    public int compareTo(Money other) {
        assertSameCurrencyAs(other);
        if (amount < other.amount)
            return -1;
        else if (amount == other.amount)
            return 0;
        else
            return 1;
    }

    /**
     * Convience implementation of greater than function.
     * 
     * @param other
     * @return Boolean True if money is greater than other
     * @see #compareTo(type.Money)
     */
    public boolean greaterThan(Money other) {
        return (compareTo(other) > 0);
    }

    /** Convience comparison functions 
     * 
     * @param other
     * @return Boolean True if money is less than other
     * @see #compareTo(Money)
     */
    public boolean lessThan(Money other) {
        return (compareTo(other) < 0);
    }

    /**
     * Money multiplication with default rounding mode.
     * 
     * Equivilent of <code>multiply( amount, BigDecimal.ROUND_HALF_EVEN )</code>
     * @param amount Multiplicator
     * @return Money
     */
    public Money multiply(double amount) {
        return multiply(new BigDecimal(amount));
    }

    /**
     * Money multiplication with default rounding mode.
     * 
     * Equivilent of <code>multiply( amount, BigDecimal.ROUND_HALF_EVEN )</code>
     * @param amount Multiplicator
     * @return Money
     */
    public Money multiply(BigDecimal amount) {
        return multiply(amount, BigDecimal.ROUND_HALF_EVEN);
    }

    /**
     * Money multiplication with user specified rounding mode.
     * 
     * @param amount Multiplicator
     * @param roundingMode Rounding mode as specified by BigDecimal
     * @return Money
     * @see java.math.BigDecimal#ROUND_UP
     * @see java.math.BigDecimal#ROUND_DOWN
     * @see java.math.BigDecimal#ROUND_CEILING
     * @see java.math.BigDecimal#ROUND_FLOOR
     * @see java.math.BigDecimal#ROUND_HALF_UP
     * @see java.math.BigDecimal#ROUND_HALF_DOWN
     * @see java.math.BigDecimal#ROUND_HALF_EVEN
     * @see java.math.BigDecimal#ROUND_UNNECESSARY
     */
    public Money multiply(BigDecimal amount, int roundingMode) {
        return new Money(getAmount().multiply(amount), currency, roundingMode);
    }

    /**
     * Allocates money "evenly' into n amounts.
     * 
     * Modified from the origaionl to handle negative Monetary values.
     */
    public Money[] allocate(int n) {
        Money[] results = new Money[n];
        Money lowResult = newMoney(amount / n);
        Money highResult = newMoney(lowResult.amount + (amount >= 0 ? 1 : -1));

        int remainder = Math.abs((int) amount % n);
        for (int i = 0; i < remainder; i++)
            results[i] = highResult;
        for (int i = remainder; i < n; i++)
            results[i] = lowResult;

        return results;
    }

    /**
     * Allocates money according to provided ratios.
     * 
     * Modified from the origaionl to handle negative Monetary values.
     */
    public Money[] allocate(long[] ratios) {
        long total = 0;
        for (int i = 0; i < ratios.length; i++)
            total += ratios[i];
        long remainder = amount;

        Money[] results = new Money[ratios.length];
        for (int i = 0; i < results.length; i++) {
            results[i] = newMoney(amount * ratios[i] / total);
            remainder -= results[i].amount;
        }
        if (remainder > 0) {
            for (int i = 0; i < remainder; i++) {
                results[i].amount++;
            }
        }
        if (remainder < 0) {
            for (int i = 0; i > remainder; i--) {
                results[i].amount--;
            }
        }
        return results;
    }

    /**
     * Money representation (based on currancy).
     * <ul>
     * <li><code>AMOUNT CODE</code>
     * <li><code>1.00 USD</code> One dollar us
     * <li><code>1.00 CAD</code> One dollar canadian
     * <li><code>1 JPY</code> One yen
     * </ul>
     * You may wish to format the amount according to your locale using
     * the following code fragment:
     * <code><pre>
     * Currency currency = money.currency();
     * double amount = money.amount().doubleValue();
     * 
     * NumberFormat nf=NumberFormat.getCurrencyInstance();
     * nf.setCurrency( currency );
     * nf.setMinimumFractionDigits( currency.getDefaultFractionDigits() );
     * nf.setMaximumFractionDigits( currency.getDefaultFractionDigits() );
     *
     * System.out.println( nf.format( amount ) );
     * </pre></code>
     * This implemenation only really returns the expected result for the
     * local currency.  For non local currencies the Currency CODE is used
     * the symbol as per the Currency.getSymbol() specification - and the
     * resulting string cannot be reparsed back into a amount.
     * <ul>
     * <li><code>$1.00</code> or <code>-$1.00</code> - USD printed in us local
     * <li><code>CAD1.00</code> or <code>-CAD1.00</code> - CAD printed in us local
     * </ul>
     * MoneyUtilites supports a more pleasing, locale independent,
     * printing/parsing representation.
     */
    public String toString() {
        nf.setCurrency(currency);
        nf.setMinimumFractionDigits(currency.getDefaultFractionDigits());
        nf.setMaximumFractionDigits(currency.getDefaultFractionDigits());

        return nf.format(getAmount().doubleValue());
    }

    /**
     * Convience function produces money in the 'local currency'.
     */
    public static Money local(double amount) {
        return new Money(amount, LOCAL_CURRENCY);
    }

    /**
     * Returns Money object represeted by provided string.
     * <ul>
     * <li><code>NUMBER CODE</code>
     * </ul>
     * 
     * @param str Representation of money in the form <code>AMOUNT CODE</code>
     * @return Money
     */
    public static Money valueOf(String str) throws java.text.ParseException {
        Currency currency;
        Number number;
        currency = Currency.getInstance(str.substring(str.length() - 3));

        nf.setCurrency(currency);
        nf.setMinimumFractionDigits(currency.getDefaultFractionDigits());
        nf.setMaximumFractionDigits(currency.getDefaultFractionDigits());

        number = nf.parse(str);
        return new Money(number.doubleValue(), currency);
    }

    /**
     * throw exception if not valid comparison.
     * Fowler's orgional definition:
     * <code>Assert.equals("money math mismatch", currency, arg.currency );</code> 
     */
    private void assertSameCurrencyAs(Money arg) {
        if (null == arg) {
            throw new IllegalArgumentException("Cannot compare money to null.");
        }
        if (!currency.equals(arg.getCurrency())) {
            throw new IllegalArgumentException("Cannot compare different currencies.");
        }
    }

    /** 
     * Used to return Money in the same curancy as this one. 
     */
    private Money newMoney(long amount) {
        return new Money(amount, this.currency);
    }

    private int centFactor() {
        return cents[currency.getDefaultFractionDigits()];
    }
}