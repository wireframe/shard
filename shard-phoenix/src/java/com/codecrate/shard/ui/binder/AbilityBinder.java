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

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.swing.JComboBox;
import javax.swing.JComponent;

import org.springframework.binding.form.FormModel;
import org.springframework.binding.value.ValueModel;
import org.springframework.binding.value.support.ValueHolder;
import org.springframework.richclient.form.binding.Binding;
import org.springframework.richclient.form.binding.support.AbstractBinder;
import org.springframework.richclient.form.binding.swing.ComboBoxBinding;
import org.springframework.util.Assert;

import com.codecrate.shard.ability.Ability;
import com.codecrate.shard.ability.AbilityDao;

/**
 * @author <a href="mailto:wireframe@dev.java.net">Ryan Sonnek</a>
 */
public class AbilityBinder extends AbstractBinder {

    private final AbilityDao abilityDao;
    private Map abilities;
    
    public AbilityBinder(AbilityDao abilityDao) {
        super(Ability.class);
        this.abilityDao = abilityDao;
    }

    protected JComponent createControl(Map context) {
        return getComponentFactory().createComboBox();
    }

    protected Binding doBind(JComponent control, FormModel formModel, String formPropertyPath, Map context) {
        Assert.isTrue(control instanceof JComboBox, formPropertyPath);
        ComboBoxBinding binding = new ComboBoxBinding((JComboBox)control, formModel, formPropertyPath) {
            protected ValueModel getValueModel() {
                return null;
            }
        };
        binding.setSelectableItemsHolder(new ValueHolder(getAbilities().keySet()));
        return binding;
    }

    private Map getAbilities() {
        if (null == abilities) {
            abilities = new HashMap();
            Iterator it = abilityDao.getAbilities().iterator();
            while (it.hasNext()) {
                Ability ability = (Ability) it.next();
                abilities.put(ability.getName(), ability);
            }
        }
        return abilities;
    }
}
