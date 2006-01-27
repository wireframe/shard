package com.codecrate.shard.ui.component;

import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public abstract class SearchOnPauseJTextField extends JTextField {
    private static final int SEARCH_DELAY_MILLIS = 300;

    public SearchOnPauseJTextField() {
    	super();
        getDocument().addDocumentListener(new SearchDocumentListener());
    }

    public String getSearchableText() {
        return getText().trim();
    }

	protected abstract void onClear();

	protected abstract void onSearch(String input);

    private class SearchDocumentListener implements DocumentListener {
        private Timer timer = new Timer();

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
            String value = getSearchableText();
            if (0 < value.length()) {
                TimerTask delaySearchTask = new DelaySearchTask(value);
                timer.schedule(delaySearchTask, SEARCH_DELAY_MILLIS);
            } else {
                onClear();
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
                onSearch(originalInput);
            }
        }
        private boolean hasInputChanged() {
            return !originalInput.equals(getSearchableText());
        }
    }
}
