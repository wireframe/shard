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
package com.codecrate.shard.ui.view;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.print.Book;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;

import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.velocity.Template;
import org.apache.velocity.app.VelocityEngine;
import org.springframework.richclient.application.PageComponentContext;
import org.springframework.richclient.application.support.AbstractView;
import org.springframework.richclient.command.support.AbstractActionCommandExecutor;
import org.springframework.richclient.dialog.AbstractDialogPage;
import org.springframework.richclient.dialog.FormBackedDialogPage;
import org.springframework.richclient.dialog.TitledPageApplicationDialog;
import org.springframework.richclient.form.FormModelHelper;
import org.springframework.richclient.wizard.Wizard;
import org.springframework.richclient.wizard.WizardListener;

import com.codecrate.shard.action.PrintCharacterAction;
import com.codecrate.shard.character.CharacterDao;
import com.codecrate.shard.character.PlayerCharacter;
import com.codecrate.shard.ui.NewCharacterWizard;
import com.codecrate.shard.ui.ShardCommandIds;
import com.codecrate.shard.ui.form.OpenCharacterForm;

public class CharacterManagerView extends AbstractView implements WizardListener {
    private static final Log LOGGER = LogFactory.getLog(CharacterManagerView.class);

    private PrintCommandExecutor printExecutor;
    private OpenCommandExecutor openExecutor;
    private VelocityEngine velocityEngine;
    private CharacterDao characterDao;

    private JTabbedPane tabbedPane;

    protected JComponent createControl() {
        JPanel view = new JPanel();
        view.setLayout(new BorderLayout());
        view.add(getTabbedPane(), BorderLayout.CENTER);
        return view;
    }
    
    private JTabbedPane getTabbedPane() {
        if (null == tabbedPane) {
            tabbedPane = new JTabbedPane();
        }
        return tabbedPane;
    }

    protected void registerLocalCommandExecutors(PageComponentContext context) {
        context.register(ShardCommandIds.PRINT, getPrintCommand());
        context.register(ShardCommandIds.OPEN, getOpenCommand());
    }
    
    public void setVelocityEngine(VelocityEngine engine) {
    	this.velocityEngine = engine;
    }
    public void setCharacterDao(CharacterDao characterDao) {
        this.characterDao = characterDao;
    }
    
    public void setNewCharacterWizard(NewCharacterWizard wizard) {
    	wizard.addWizardListener(this);
    }
    
    private PrintCommandExecutor getPrintCommand() {
        if (null == printExecutor) {
        	printExecutor = new PrintCommandExecutor();
            printExecutor.setEnabled(false);
        }
        return printExecutor;
    }

    private OpenCommandExecutor getOpenCommand() {
        if (null == openExecutor) {
            openExecutor = new OpenCommandExecutor();
            openExecutor.setEnabled(true);
        }
        return openExecutor;
    }

    private class PrintCommandExecutor extends AbstractActionCommandExecutor {
        public void execute() {
            try {
                Template template = velocityEngine.getTemplate("default.vm");
                PrintCharacterAction printAction = new PrintCharacterAction(getSelectedCharacter(), template);
                
                PrinterJob job = PrinterJob.getPrinterJob();
                Book book = new Book();
                Printable print = new Printable() {

                    public int print(Graphics arg0, PageFormat arg1, int arg2)
                            throws PrinterException {
                        return 0;
                    }
                };
                PageFormat format = job.pageDialog(job.defaultPage());
                int count = 1;//painter.calculatePageCount(pf);
                book.append(print, format, count);
                job.setPageable(book);
                if (job.printDialog()) {
                    System.out.println(printAction.render().toString());
                }
            } catch (Exception e) {
                LOGGER.warn("Error trying to print character", e);
            }
       }

        private PlayerCharacter getSelectedCharacter() {
            PlayerCharacterPanel panel = (PlayerCharacterPanel) getTabbedPane().getSelectedComponent();
            if (null != panel) {
                return panel.getCharacter();
            }
            return null;
        }
    }

    private class OpenCommandExecutor extends AbstractActionCommandExecutor {

		public void execute() {
            final CharacterSelection characterSelection = new CharacterSelection();
            final OpenCharacterForm form = new OpenCharacterForm(characterDao, FormModelHelper.createFormModel(characterSelection));
            final AbstractDialogPage page = new FormBackedDialogPage(form);
            TitledPageApplicationDialog dialog = new TitledPageApplicationDialog(page, getWindowControl()) {
                protected void onAboutToShow() {
                    setEnabled(page.isPageComplete());
                }

                protected boolean onFinish() {
                    form.getFormModel().commit();
                    PlayerCharacter character = characterSelection.getCharacter();
                    addCharacterPanel(character);
                    return true;
                }
            };
            dialog.showDialog();
       }
    }

	public void onPerformFinish(Wizard wizard, boolean arg1) {
		PlayerCharacter character = ((NewCharacterWizard)wizard).getCharacter();
        addCharacterPanel(character);
	}

    private void addCharacterPanel(PlayerCharacter character) {
        PlayerCharacterPanel panel = getPanelForCharacter(character);
        if (null == panel) {
            panel = new PlayerCharacterPanel(character);
            getTabbedPane().add(panel);
        } 
        panel.requestFocusInWindow();
        printExecutor.setEnabled(true);
    }

    private PlayerCharacterPanel getPanelForCharacter(PlayerCharacter character) {
        for (int x = 1; x < getTabbedPane().getComponentCount(); x++) {
            PlayerCharacterPanel panel = (PlayerCharacterPanel) getTabbedPane().getComponentAt(x -1 );
            if (panel.getCharacter().equals(character)) {
                return panel;
            }
        }
        return null;
    }
	public void onPerformCancel(Wizard wizard, boolean arg1) {
	}
    
    public class CharacterSelection {
        private PlayerCharacter character;

        public PlayerCharacter getCharacter() {
            return character;
        }

        public void setCharacter(PlayerCharacter character) {
            this.character = character;
        }
    }


}