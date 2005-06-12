/*
 * Copyright 2002-2004 the original author or authors.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package com.codecrate.shard.ui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JPanel;

import org.springframework.richclient.application.support.AbstractView;

import com.codecrate.shard.ability.AbilityDao;

public class MainView extends AbstractView {
    private JButton rollButton;

    private AbilityDao abilityDao;
    
    /**
     * @return Returns the abilityDao.
     */
    public AbilityDao getAbilityDao() {
        return abilityDao;
    }
    
    /**
     * @param abilityDao The abilityDao to set.
     */
    public void setAbilityDao(AbilityDao abilityDao) {
        this.abilityDao = abilityDao;
    }

    protected JComponent createControl() {
        JPanel view = new JPanel();
        view.add(getRollButton(), BorderLayout.SOUTH);
        return view;
    }
    
    private JButton getRollButton() {
        if (null == rollButton) {
            rollButton = new JButton("Roll");
            rollButton.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent arg0) {
                    System.out.println("here");
                }
                
            });
        }
        return rollButton;
    }
}