package com.codecrate.shard.ui.component;

import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;

public class SearchComponent extends JPanel {

	private JTextField queryText = null;
	private JButton searchButton = null;
	private JButton clearButton = null;

	/**
	 * This is the default constructor
	 */
	public SearchComponent() {
		super();
		initialize();
	}

	/**
	 * This method initializes this
	 *
	 * @return void
	 */
	private void initialize() {
		this.setSize(new Dimension(300, 40));
		this.add(getQueryText(), null);
		this.add(getSearchButton(), null);
		this.add(getClearButton(), null);
	}

	/**
	 * This method initializes queryText
	 *
	 * @return javax.swing.JTextField
	 */
	private JTextField getQueryText() {
		if (queryText == null) {
			queryText = new JTextField();
			queryText.setColumns(10);
			queryText.getDocument().addDocumentListener(new DocumentListener() {

				public void changedUpdate(DocumentEvent event) {
					processEvent(event);
				}

				public void insertUpdate(DocumentEvent event) {
					processEvent(event);
				}

				public void removeUpdate(DocumentEvent event) {
					processEvent(event);
				}

				private void processEvent(DocumentEvent event) {
					Document document = event.getDocument();
					try {
						String value = document.getText(0, document.getLength());
						System.out.println(value);
					} catch (BadLocationException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}

			});
		}
		return queryText;
	}

	/**
	 * This method initializes searchButton
	 *
	 * @return javax.swing.JButton
	 */
	private JButton getSearchButton() {
		if (searchButton == null) {
			searchButton = new JButton();
			searchButton.setText("Search");
		}
		return searchButton;
	}

	/**
	 * This method initializes clearButton
	 *
	 * @return javax.swing.JButton
	 */
	private JButton getClearButton() {
		if (clearButton == null) {
			clearButton = new JButton();
			clearButton.setText("Clear");
		}
		return clearButton;
	}


}
