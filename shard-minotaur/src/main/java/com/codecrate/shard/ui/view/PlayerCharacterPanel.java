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

import java.awt.Component;
import java.awt.GridLayout;
import java.awt.Image;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import org.springframework.richclient.command.ActionCommand;
import org.springframework.richclient.command.SwingActionAdapter;

import com.codecrate.shard.ability.DefaultAbilityScoreContainer;
import com.codecrate.shard.ability.HibernateAbilityScoreDao;
import com.codecrate.shard.character.DefaultPlayerCharacter;
import com.codecrate.shard.character.PlayerCharacter;

public class PlayerCharacterPanel extends JPanel {
    private final PlayerCharacter character;

    private JLabel thumbnailImage = null;
    private JLabel characterNameValue = null;
    private JPanel basicInfoPanel = null;
    private JLabel levelScript = null;
    private JLabel levelValue = null;
    private JLabel classScript = null;
    private JLabel classValue = null;
    private JLabel raceScript = null;
    private JLabel raceValue = null;
    private JLabel alignmentScript = null;
    private JLabel alignmentValue = null;
    private JPanel jPanel = null;
    private JLabel jLabel = null;
    private JLabel wisdomValue = null;
    private JLabel intelligenceValue = null;
    private JLabel jLabel3 = null;
    private JLabel jLabel4 = null;
    private JLabel jLabel5 = null;
    private JLabel charismaValue = null;
    private JLabel constitutionValue = null;
    private JLabel jLabel8 = null;
    private JLabel dexterityValue = null;
    private JLabel jLabel10 = null;
    private JLabel strengthValue = null;
	private JLabel charsimaBonusValue = null;
	private JLabel constitutionBonusValue = null;
	private JLabel strengthBonusValue = null;
	private JLabel wisdomBonusValue = null;
	private JLabel intelligenceBonusValue = null;
	private JLabel dexterityBonusValue = null;
	private JPanel Appearance = null;
	private JLabel haircolorValue = null;
	private JLabel heightScript = null;
	private JLabel eyecolorScript = null;
	private JLabel weightScript = null;
	private JLabel weightValue = null;
	private JLabel haircolorScript = null;
	private JLabel heightValue = null;
	private JLabel eyecolorValue = null;
	private JLabel ageScript = null;
	private JLabel skincolorScript = null;
	private JLabel ageValue = null;
	private JLabel skincolorValue = null;
	private JButton levelUp = null;
	private JScrollPane description = null;
	private JScrollPane bio = null;

	/**
     * This is the default constructor
     */
    private PlayerCharacterPanel() {
    	this(new DefaultPlayerCharacter("Thor the Almighty", DefaultAbilityScoreContainer.averageScores(new HibernateAbilityScoreDao()), null, null, null, null, null), new ActionCommand("levelUpCommand") {
			protected void doExecuteCommand() {
			}
		});
    }

    public PlayerCharacterPanel(PlayerCharacter character, ActionCommand levelUpCommand) {
        this.character = character;

        initialize();
		levelUp.setAction(new SwingActionAdapter(levelUpCommand));
    }

	/**
     * This method initializes this
     *
     * @return void
     */
    private void initialize() {
        this.setName(character.getBio().getName());

        this.setLayout(null);
        this.setPreferredSize(new java.awt.Dimension(400,200));
        this.setSize(new java.awt.Dimension(587,331));
        this.add(getThumbnailImage(), null);
        this.add(getCharacterNameValue(), null);
        this.add(getBasicInfoPanel(), null);
        this.add(getJPanel(), null);
        this.add(getAppearance(), null);
        this.add(getLevelUp(), null);
        this.add(getDescription(), null);
        this.add(getBio(), null);
    }

    private Component getCharacterNameValue() {
    	if (null == characterNameValue) {
            characterNameValue = new JLabel();
            characterNameValue.setBounds(new java.awt.Rectangle(165,15,114,15));
            characterNameValue.setText(character.getBio().getName());
    	}

        return characterNameValue;
	}

	private Component getThumbnailImage() {
    	if (null == thumbnailImage) {
            thumbnailImage = new JLabel();
            thumbnailImage.setPreferredSize(new java.awt.Dimension(150,150));
            thumbnailImage.setBounds(new java.awt.Rectangle(5,5,150,150));
            thumbnailImage.setIcon(getPortraitIcon());
    	}
        return thumbnailImage;
	}

    private ImageIcon getPortraitIcon() {
        Image image = character.getBio().getPortraitImage();

    	if (null == image) {
        	URL portraitUrl = this.getClass().getClassLoader().getResource("images/default-portrait.jpg");
    		image = new ImageIcon(portraitUrl).getImage();
    	}
		Image scaledImage = image.getScaledInstance(150, -1, Image.SCALE_DEFAULT);
		return new ImageIcon(scaledImage);
	}

	/**
     * This method initializes jPanel
     *
     * @return javax.swing.JPanel
     */
    private JPanel getBasicInfoPanel() {
        if (basicInfoPanel == null) {
            alignmentScript = new JLabel();
            alignmentScript.setText("Alignment:");
            alignmentScript.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
            raceScript = new JLabel();
            raceScript.setText("Race:");
            raceScript.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
            classScript = new JLabel();
            classScript.setText("Class:");
            classScript.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
            levelScript = new JLabel();
            levelScript.setText("Level:");
            levelScript.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
            GridLayout gridLayout = new GridLayout();
            gridLayout.setRows(2);
            gridLayout.setHgap(5);
            basicInfoPanel = new JPanel();
            basicInfoPanel.setLocation(new java.awt.Point(165,44));
            basicInfoPanel.setSize(new java.awt.Dimension(300,60));
            basicInfoPanel.setLayout(gridLayout);
            basicInfoPanel.add(classScript, null);
            basicInfoPanel.add(getClassValue(), null);
            basicInfoPanel.add(levelScript, null);
            basicInfoPanel.add(getLevelValue(), null);
            basicInfoPanel.add(raceScript, null);
            basicInfoPanel.add(getRaceValue(), null);
            basicInfoPanel.add(alignmentScript, null);
            basicInfoPanel.add(getAlignmentValue(), null);
        }
        return basicInfoPanel;
    }

	private Component getClassValue() {
		if (null == classValue) {
            classValue = new JLabel();
            classValue.setText(character.getCharacterProgression().getDescription());
		}
		return classValue;
	}

	private JLabel getLevelValue() {
		if (levelValue == null) {
			levelValue = new JLabel();
			levelValue.setText(Integer.toString(character.getCharacterProgression().getCharacterLevel()));
		}
		return levelValue;
	}

	private JLabel getRaceValue() {
		if (null == raceValue) {
            raceValue = new JLabel();
            raceValue.setText(character.getRace().getName());
		}
		return raceValue;
	}

	private JLabel getAlignmentValue() {
		if (null == alignmentValue) {
			alignmentValue = new JLabel();
	        alignmentValue.setText(character.getAlignment().getAbbreviation());
		}
		return alignmentValue;
	}

    /**
     * This method initializes jPanel
     *
     * @return javax.swing.JPanel
     */
    private JPanel getJPanel() {
        if (jPanel == null) {
            jLabel10 = new JLabel();
            jLabel10.setText("DEX");
            jLabel8 = new JLabel();
            jLabel8.setText("CON");
            jLabel5 = new JLabel();
            jLabel5.setText("CHA");
            jLabel4 = new JLabel();
            jLabel4.setText("STR");
            jLabel3 = new JLabel();
            jLabel3.setText("INT");
            jLabel = new JLabel();
            jLabel.setText("WIS");
            GridLayout gridLayout1 = new GridLayout();
            gridLayout1.setRows(6);
            jPanel = new JPanel();
            jPanel.setLayout(gridLayout1);
            jPanel.setBounds(new java.awt.Rectangle(6,165,151,151));
            jPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(java.awt.Color.black,2), "Ability Scores", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", java.awt.Font.BOLD, 12), new java.awt.Color(51,51,51)));
            jPanel.add(jLabel4, null);
            jPanel.add(getStrengthValue(), null);
            jPanel.add(getStrengthBonusValue(), null);
            jPanel.add(jLabel10, null);
            jPanel.add(getDexterityValue(), null);
            jPanel.add(getDexterityBonusValue(), null);
            jPanel.add(jLabel8, null);
            jPanel.add(getConstitutionValue(), null);
            jPanel.add(getConstitutionBonusValue(), null);
            jPanel.add(jLabel3, null);
            jPanel.add(getIntelligenceValue(), null);
            jPanel.add(getIntelligenceBonusValue(), null);
            jPanel.add(jLabel, null);
            jPanel.add(getWisdomValue(), null);
            jPanel.add(getWisdomBonusValue(), null);
            jPanel.add(jLabel5, null);
            jPanel.add(getCharismaValue(), null);
            jPanel.add(getCharsimaBonusValue(), null);
        }
        return jPanel;
    }

	private Component getCharsimaBonusValue() {
		if (null == charsimaBonusValue) {
            charsimaBonusValue = new JLabel();
            charsimaBonusValue.setText(formatModifier(character.getAbilities().getCharisma().getModifier()));
		}
		return charsimaBonusValue;
	}

	private String formatModifier(int modifier) {
		if (0 <= modifier) {
			return "+" + modifier;
		}
		return Integer.toString(modifier);
	}

	private Component getConstitutionBonusValue() {
		if (null == constitutionBonusValue) {
            constitutionBonusValue = new JLabel();
            constitutionBonusValue.setText(formatModifier(character.getAbilities().getConstitution().getModifier()));
		}
		return constitutionBonusValue;
	}

	private Component getStrengthBonusValue() {
		if (null == strengthBonusValue) {
            strengthBonusValue = new JLabel();
            strengthBonusValue.setText(formatModifier(character.getAbilities().getStrength().getModifier()));
		}
		return strengthBonusValue;
	}

	private Component getWisdomBonusValue() {
		if (null == wisdomBonusValue) {
            wisdomBonusValue = new JLabel();
            wisdomBonusValue.setText(formatModifier(character.getAbilities().getWisdom().getModifier()));
		}
		return wisdomBonusValue;
	}

	private Component getIntelligenceBonusValue() {
		if (null == intelligenceBonusValue) {
            intelligenceBonusValue = new JLabel();
            intelligenceBonusValue.setText(formatModifier(character.getAbilities().getIntelligence().getModifier()));
		}
		return intelligenceBonusValue;
	}

	private Component getDexterityBonusValue() {
		if (null == dexterityBonusValue) {
            dexterityBonusValue = new JLabel();
            dexterityBonusValue.setText(formatModifier(character.getAbilities().getDexterity().getModifier()));
		}
		return dexterityBonusValue;
	}

	private Component getCharismaValue() {
		if (null == charismaValue) {
            charismaValue = new JLabel();
            charismaValue.setText(Integer.toString(character.getAbilities().getCharisma().getModifiedValue()));
		}
		return charismaValue;
	}

	private Component getWisdomValue() {
		if (null == wisdomValue) {
            wisdomValue = new JLabel();
            wisdomValue.setText(Integer.toString(character.getAbilities().getWisdom().getModifiedValue()));
		}
		return wisdomValue;
	}

	private Component getIntelligenceValue() {
		if (null == intelligenceValue) {
            intelligenceValue = new JLabel();
            intelligenceValue.setText(Integer.toString(character.getAbilities().getIntelligence().getModifiedValue()));
		}
		return intelligenceValue;
	}

	private Component getConstitutionValue() {
		if (null == constitutionValue) {
            constitutionValue = new JLabel();
            constitutionValue.setText(Integer.toString(character.getAbilities().getConstitution().getModifiedValue()));
		}
		return constitutionValue;
	}

	private JLabel getDexterityValue() {
		if (null == dexterityValue) {
			dexterityValue = new JLabel();
			dexterityValue.setText(Integer.toString(character.getAbilities().getDexterity().getModifiedValue()));
		}
		return dexterityValue;
	}

	private Component getStrengthValue() {
		if (null == strengthValue) {
			strengthValue = new JLabel();
			strengthValue.setText(Integer.toString(character.getAbilities().getStrength().getModifiedValue()));
		}
		return strengthValue;
	}

	/**
	 * This method initializes Appearance
	 *
	 * @return javax.swing.JPanel
	 */
	private JPanel getAppearance() {
		if (Appearance == null) {
			skincolorValue = new JLabel();
			skincolorValue.setText("Pale");
			ageValue = new JLabel();
			ageValue.setText("21");
			skincolorScript = new JLabel();
			skincolorScript.setText("Skin Color:");
			ageScript = new JLabel();
			ageScript.setText("Age:");
			eyecolorValue = new JLabel();
			eyecolorValue.setText("Blue");
			heightValue = new JLabel();
			heightValue.setText("5'11");
			haircolorScript = new JLabel();
			haircolorScript.setText("HairColor:");
			weightValue = new JLabel();
			weightValue.setText("180");
			weightScript = new JLabel();
			weightScript.setText("Weight:");
			eyecolorScript = new JLabel();
			eyecolorScript.setText("Eye Color:");
			GridLayout gridLayout2 = new GridLayout();
			gridLayout2.setRows(2);
			heightScript = new JLabel();
			heightScript.setText("Height:");
			haircolorValue = new JLabel();
			haircolorValue.setText("Blond");
			Appearance = new JPanel();
			Appearance.setLayout(gridLayout2);
			Appearance.setBounds(new java.awt.Rectangle(165,120,392,60));
			Appearance.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(java.awt.Color.black,1), "Appearance", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", java.awt.Font.BOLD, 12), new java.awt.Color(51,51,51)));
			Appearance.add(heightScript, null);
			Appearance.add(heightValue, null);
			Appearance.add(eyecolorScript, null);
			Appearance.add(eyecolorValue, null);
			Appearance.add(skincolorScript, null);
			Appearance.add(skincolorValue, null);
			Appearance.add(weightScript, null);
			Appearance.add(weightValue, null);
			Appearance.add(haircolorScript, null);
			Appearance.add(haircolorValue, null);
			Appearance.add(ageScript, null);
			Appearance.add(ageValue, null);
		}
		return Appearance;
	}

	/**
	 * This method initializes levelUp
	 *
	 * @return javax.swing.JButton
	 */
	private JButton getLevelUp() {
		if (levelUp == null) {
			levelUp = new JButton();
			levelUp.setBounds(new java.awt.Rectangle(480,45,91,27));
			levelUp.setFont(new java.awt.Font("Dialog", java.awt.Font.BOLD | java.awt.Font.ITALIC, 12));
			levelUp.setText("Level Up");
		}
		return levelUp;
	}

	/**
	 * This method initializes description
	 *
	 * @return javax.swing.JScrollPane
	 */
	private JScrollPane getDescription() {
		if (description == null) {
			description = new JScrollPane();
			description.setBounds(new java.awt.Rectangle(165,190,392,36));
			description.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(java.awt.Color.black,1), "Description", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", java.awt.Font.BOLD, 12), new java.awt.Color(51,51,51)));
		}
		return description;
	}

	/**
	 * This method initializes bio
	 *
	 * @return javax.swing.JScrollPane
	 */
	private JScrollPane getBio() {
		if (bio == null) {
			bio = new JScrollPane();
			bio.setBounds(new java.awt.Rectangle(165,232,392,84));
			bio.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(java.awt.Color.black,1), "Bio", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", java.awt.Font.BOLD, 12), new java.awt.Color(51,51,51)));
		}
		return bio;
	}

    public PlayerCharacter getCharacter() {
        return character;
    }

}  //  @jve:decl-index=0:visual-constraint="10,10"
