package com.codecrate.shard.ui.view;

import java.awt.FlowLayout;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.codecrate.shard.character.PlayerCharacter;

public class PlayerCharacterPanel extends JPanel {

    private JLabel thumbnailImage = null;
    private JLabel characterNameLabel = null;
    private JLabel characterNameValue = null;

    /**
     * This is the default constructor
     */
    public PlayerCharacterPanel() {
        super();
        initialize();
    }

    public PlayerCharacterPanel(PlayerCharacter character) {
        this();
        this.setName(character.getBio().getName());
        characterNameValue.setText(character.getBio().getName());
    }

    /**
     * This method initializes this
     * 
     * @return void
     */
    private void initialize() {
        characterNameValue = new JLabel();
        characterNameValue.setText("Thor the Almighty");
        characterNameLabel = new JLabel();
        characterNameLabel.setText("Character Name:");
        characterNameLabel.setFont(new java.awt.Font("Dialog", java.awt.Font.BOLD, 14));
        thumbnailImage = new JLabel();
        thumbnailImage.setPreferredSize(new java.awt.Dimension(150,150));
        ImageIcon icon = new ImageIcon(new ImageIcon("/home/rsonnek/260_02_kl2.jpg").getImage().getScaledInstance(150, -1, Image.SCALE_DEFAULT));
        thumbnailImage.setIcon(icon);
        this.setLayout(new FlowLayout());
        this.setName("Thor the Almighty");
        this.setPreferredSize(new java.awt.Dimension(400,200));
        this.setSize(new java.awt.Dimension(400,200));
        this.add(thumbnailImage, null);
        this.add(characterNameLabel, null);
        this.add(characterNameValue, null);
        
    }

}  //  @jve:decl-index=0:visual-constraint="10,10"
