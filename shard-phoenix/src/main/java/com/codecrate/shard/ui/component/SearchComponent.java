package com.codecrate.shard.ui.component;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class SearchComponent {
	private static final int SEARCH_DELAY_MILLIS = 300;

	private static final Log LOG = LogFactory.getLog(SearchComponent.class);
	
	private JPanel mainPanel = null;
	private JTextField queryText = null;
	private JButton searchButton = null;
	private JButton clearButton = null;
	private Collection searchListeners = new ArrayList();
	private Timer timer = new Timer();

	public JPanel getPanel() {
		if (null == mainPanel) {
			mainPanel = new JPanel();
			mainPanel.setSize(new Dimension(300, 40));
			mainPanel.add(getQueryText(), null);
			mainPanel.add(getSearchButton(), null);
			mainPanel.add(getClearButton(), null);
		}
		return mainPanel;
	}

	public void addSearchListener(SearchAction action) {
		searchListeners.add(action);
	}
	
	public void removeSearchListener(SearchAction action) {
		searchListeners.remove(action);
	}
	
	private void fireSearch() {
		Iterator it = searchListeners.iterator();
		while (it.hasNext()) {
			SearchAction action = (SearchAction) it.next();
			action.search(getQueryText().getText());
		}
	}
	
	private void fireClear() {
		Iterator it = searchListeners.iterator();
		while (it.hasNext()) {
			SearchAction action = (SearchAction) it.next();
			action.clear();
		}
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
			queryText.getDocument().addDocumentListener(new SearchDocumentListener());
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
			searchButton.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent arg0) {
					fireSearch();
				}
				
			});
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
			clearButton.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent arg0) {
					getQueryText().setText("");
				}
				
			});
		}
		return clearButton;
	}

	private class SearchDocumentListener implements DocumentListener {
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
				String value = document.getText(0, document.getLength()).trim();
				if (0 < value.length()) {
					TimerTask delaySearchTask = new DelaySearchTask(value);
					timer.schedule(delaySearchTask, SEARCH_DELAY_MILLIS);
				} else {
					fireClear();
				}
			} catch (BadLocationException e) {
				LOG.warn("Error getting search query", e);
			}
		}
	}


	private class DelaySearchTask extends TimerTask {
        private final String originalInput;

		public DelaySearchTask(String originalInput) {
			this.originalInput = originalInput;
		}
		public void run() {
			if (!hasInputChanged()) {
	            fireSearch();
			}
        }
        private boolean hasInputChanged() {
            return !originalInput.equals(getQueryText().getText());
        }
    }
	

	public interface SearchAction {
		void search(String query);
		
		void clear();
	}
}
