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
package com.codecrate.shard.ui.binder;

import java.awt.FlowLayout;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Map;

import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFormattedTextField;
import javax.swing.JPanel;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.NumberFormatter;

import org.springframework.binding.form.FormModel;
import org.springframework.richclient.form.binding.Binding;
import org.springframework.richclient.form.binding.support.AbstractBinder;
import org.springframework.richclient.form.binding.support.CustomBinding;

import com.codecrate.shard.equipment.Currency;
import com.codecrate.shard.equipment.CurrencyDao;
import com.codecrate.shard.equipment.Money;

public class MoneyBinder extends AbstractBinder {

    private final CurrencyDao currencyDao;

    public MoneyBinder(CurrencyDao currencyDao) {
        super(Money.class);
        this.currencyDao = currencyDao;
    }

    protected JComponent createControl(Map context) {
        return new MoneyInputPanel(currencyDao);
    }

    protected Binding doBind(JComponent control, FormModel formModel, String formPropertyPath, Map context) {
        final MoneyInputPanel moneyInput = (MoneyInputPanel)control;
        return new CustomBinding(formModel, formPropertyPath, Money.class) {

            protected JComponent doBindControl() {
                moneyInput.setMoney((Money)getValue());
                moneyInput.amount.addPropertyChangeListener("value", new PropertyChangeListener() {
                    public void propertyChange(PropertyChangeEvent evt) {
                        controlValueChanged(moneyInput.getMoney());
                    }
                });
                moneyInput.currency.addPropertyChangeListener("value", new PropertyChangeListener() {
                    public void propertyChange(PropertyChangeEvent evt) {
                        controlValueChanged(moneyInput.getMoney());
                    }
                });
                return moneyInput;
            }

            protected void readOnlyChanged() {
                moneyInput.setEnabled(isEnabled() && !isReadOnly());
            }

            protected void enabledChanged() {
                moneyInput.setEnabled(isEnabled() && !isReadOnly());
            }

            protected void valueModelChanged(Object newValue) {
                moneyInput.setMoney((Money)newValue);
            }
        };
    }

    public static class MoneyInputPanel extends JPanel {
        private JFormattedTextField amount;
        private JComboBox currency;
        private final CurrencyDao currencyDao;

        public MoneyInputPanel(CurrencyDao currencyDao) {
            super();
            this.currencyDao = currencyDao;

            setLayout(new FlowLayout());

            add(getAmount());
            add(getCurrency());
        }

        private JFormattedTextField getAmount() {
            if (null == amount) {
                NumberFormat amountDisplayFormat = NumberFormat.getNumberInstance();
                amount = new JFormattedTextField(new DefaultFormatterFactory(
                        new NumberFormatter(amountDisplayFormat),
                        new NumberFormatter(amountDisplayFormat),
                        new NumberFormatter(amountDisplayFormat)));
                amount.setColumns(10);
            }
            return amount;
        }

        private JComboBox getCurrency() {
            if (null == currency) {
                currency = new JComboBox(currencyDao.getCurrencies().toArray());
            }
            return currency;
        }

        public void setMoney(Money money) {
            getAmount().setValue(new Long(money.getAmount().toString()));
            getCurrency().setSelectedItem(money.getCurrency());
        }

        public Money getMoney() {
            Currency currency = (Currency) getCurrency().getSelectedItem();
            BigDecimal amount = new BigDecimal(((Long) getAmount().getValue()).intValue());

            return new Money(amount, currency, BigDecimal.ROUND_HALF_UP);
        }
    }
}
