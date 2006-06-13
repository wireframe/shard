package com.codecrate.shard.ui.view;

import java.awt.GridLayout;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import com.codecrate.shard.character.PlayerCharacter;

public class PlayerCharacterPanel extends JPanel {

    private PlayerCharacter character;
    
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
	private JLabel stengthBonusValue = null;
	private JLabel wisdomBonusValue = null;
	private JLabel intelegenceBonusValue = null;
	private JLabel dextarityBonusValue = null;
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
    public PlayerCharacterPanel() {
        super();
        initialize();
    }

    public PlayerCharacterPanel(PlayerCharacter character) {
        this();
        
        this.character = character;
        
        this.setName(character.getBio().getName());
        characterNameValue.setText(character.getBio().getName());
        alignmentValue.setText(character.getAlignment().getAbbreviation());
        raceValue.setText(character.getRace().getName());
//        classValue.setText(character.getCharacterProgression().getDescription());
//        levelValue.setText(Integer.toString(character.getCharacterProgression().getCharacterLevel()));
//        
//        strengthValue.setText(Integer.toString(character.getAbilities().getStrength().getModifiedValue()));
//        dexterityValue.setText(Integer.toString(character.getAbilities().getDexterity().getModifiedValue()));
//        constitutionValue.setText(Integer.toString(character.getAbilities().getConstitution().getModifiedValue()));
//        intelligenceValue.setText(Integer.toString(character.getAbilities().getIntelligence().getModifiedValue()));
//        wisdomValue.setText(Integer.toString(character.getAbilities().getWisdom().getModifiedValue()));
//        charismaValue.setText(Integer.toString(character.getAbilities().getCharisma().getModifiedValue()));
    }

    /**
     * This method initializes this
     * 
     * @return void
     */
    private void initialize() {
        characterNameValue = new JLabel();
        characterNameValue.setText("Thor the Almighty");
        characterNameValue.setBounds(new java.awt.Rectangle(165,15,114,15));
        thumbnailImage = new JLabel();
        thumbnailImage.setPreferredSize(new java.awt.Dimension(150,150));
        thumbnailImage.setBounds(new java.awt.Rectangle(5,5,150,150));
        ImageIcon icon = new ImageIcon(new ImageIcon("C:/Documents and Settings/Tyler/My Documents/My Pictures/games/dnd/beastman3.jpg").getImage().getScaledInstance(150, -1, Image.SCALE_DEFAULT));
        thumbnailImage.setIcon(icon);
        this.setLayout(null);
        this.setName("Thor the Almighty");
        this.setPreferredSize(new java.awt.Dimension(400,200));
        this.setSize(new java.awt.Dimension(587,331));
        this.add(thumbnailImage, null);
        this.add(characterNameValue, null);
        this.add(getBasicInfoPanel(), null);
        this.add(getJPanel(), null);
        this.add(getAppearance(), null);
        this.add(getLevelUp(), null);
        this.add(getDescription(), null);
        this.add(getBio(), null);
        
    }

    /**
     * This method initializes jPanel	
     * 	
     * @return javax.swing.JPanel	
     */
    private JPanel getBasicInfoPanel() {
        if (basicInfoPanel == null) {
            alignmentValue = new JLabel();
            alignmentValue.setText("NG");
            alignmentScript = new JLabel();
            alignmentScript.setText("Alignment:");
            alignmentScript.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
            raceValue = new JLabel();
            raceValue.setText("Human");
            raceScript = new JLabel();
            raceScript.setText("Race:");
            raceScript.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
            classValue = new JLabel();
            classValue.setText("Fighter");
            classScript = new JLabel();
            classScript.setText("Class:");
            classScript.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
            levelValue = new JLabel();
            levelValue.setText("1");
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
            basicInfoPanel.add(classValue, null);
            basicInfoPanel.add(levelScript, null);
            basicInfoPanel.add(levelValue, null);
            basicInfoPanel.add(raceScript, null);
            basicInfoPanel.add(raceValue, null);
            basicInfoPanel.add(alignmentScript, null);
            basicInfoPanel.add(alignmentValue, null);
        }
        return basicInfoPanel;
    }

    /**
     * This method initializes jPanel	
     * 	
     * @return javax.swing.JPanel	
     */
    private JPanel getJPanel() {
        if (jPanel == null) {
            dextarityBonusValue = new JLabel();
            dextarityBonusValue.setText("+3");
            intelegenceBonusValue = new JLabel();
            intelegenceBonusValue.setText("-6");
            wisdomBonusValue = new JLabel();
            wisdomBonusValue.setText("+4");
            stengthBonusValue = new JLabel();
            stengthBonusValue.setText("+4");
            constitutionBonusValue = new JLabel();
            constitutionBonusValue.setText("+1");
            charsimaBonusValue = new JLabel();
            charsimaBonusValue.setText("-1");
            strengthValue = new JLabel();
            strengthValue.setText("18");
            jLabel10 = new JLabel();
            jLabel10.setText("DEX");
            dexterityValue = new JLabel();
            dexterityValue.setText("16");
            jLabel8 = new JLabel();
            jLabel8.setText("CON");
            constitutionValue = new JLabel();
            constitutionValue.setText("12");
            charismaValue = new JLabel();
            charismaValue.setText("9");
            jLabel5 = new JLabel();
            jLabel5.setText("CHA");
            jLabel4 = new JLabel();
            jLabel4.setText("STR");
            jLabel3 = new JLabel();
            jLabel3.setText("INT");
            intelligenceValue = new JLabel();
            intelligenceValue.setText("4");
            wisdomValue = new JLabel();
            wisdomValue.setText("19");
            jLabel = new JLabel();
            jLabel.setText("WIS");
            GridLayout gridLayout1 = new GridLayout();
            gridLayout1.setRows(6);
            jPanel = new JPanel();
            jPanel.setLayout(gridLayout1);
            jPanel.setBounds(new java.awt.Rectangle(6,165,151,151));
            jPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(java.awt.Color.black,2), "Ability Scores", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", java.awt.Font.BOLD, 12), new java.awt.Color(51,51,51)));
            jPanel.add(jLabel4, null);
            jPanel.add(strengthValue, null);
            jPanel.add(stengthBonusValue, null);
            jPanel.add(jLabel10, null);
            jPanel.add(dexterityValue, null);
            jPanel.add(dextarityBonusValue, null);
            jPanel.add(jLabel8, null);
            jPanel.add(constitutionValue, null);
            jPanel.add(constitutionBonusValue, null);
            jPanel.add(jLabel3, null);
            jPanel.add(intelligenceValue, null);
            jPanel.add(intelegenceBonusValue, null);
            jPanel.add(jLabel, null);
            jPanel.add(wisdomValue, null);
            jPanel.add(wisdomBonusValue, null);
            jPanel.add(jLabel5, null);
            jPanel.add(charismaValue, null);
            jPanel.add(charsimaBonusValue, null);
        }
        return jPanel;
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
