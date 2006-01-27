package com.codecrate.shard.ui.dragdrop;

import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.io.File;
import java.net.URI;
import java.util.List;

import javax.swing.JComponent;
import javax.swing.TransferHandler;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public abstract class FileTransferHandler extends TransferHandler {
	private static final Log LOG = LogFactory.getLog(FileTransferHandler.class);

    private final DataFlavor fileDataFlavor;
    private final DataFlavor uriListFlavor;

    public FileTransferHandler() {
        fileDataFlavor = DataFlavor.javaFileListFlavor;
        try {
            uriListFlavor = new DataFlavor("text/uri-list;class=java.lang.String");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean canImport(JComponent c, DataFlavor[] flavors) {
        if (hasFileFlavor(flavors)) {
            return true;
        }
        if (hasUriListFlavor(flavors)) {
            return true;
        }

        LOG.info("Can not handle data flavors: " + stringifyFlavors(flavors));
        return false;
    }

    public int getSourceActions(JComponent c) {
        return COPY_OR_MOVE;
    }

    public boolean importData(JComponent c, Transferable t) {
        if (!canImport(c, t.getTransferDataFlavors())) {
            return false;
        }
        try {
            if (hasFileFlavor(t.getTransferDataFlavors())) {
                List files = (List)t.getTransferData(fileDataFlavor);
                for (int i = 0; i < files.size(); i++) {
                    File file = (File)files.get(i);
                    onFileDrop(file);
                }
            }
            if (hasUriListFlavor(t.getTransferDataFlavors())) {
                String fileNames = (String) t.getTransferData(uriListFlavor);
                File file = new File(new URI(fileNames.trim()));
                onFileDrop(file);
            }
            return true;
        } catch (Exception e) {
            LOG.error("Error handling drag/drop", e);
        }
        return false;
    }

    private boolean hasFileFlavor(DataFlavor[] flavors) {
        for (int i = 0; i < flavors.length; i++) {
            if (fileDataFlavor.equals(flavors[i])) {
                return true;
            }
        }
        return false;
    }
    private boolean hasUriListFlavor(DataFlavor[] flavors) {
        for (int i = 0; i < flavors.length; i++) {
            if (uriListFlavor.equals(flavors[i])) {
                return true;
            }
        }
        return false;
    }

    private String stringifyFlavors(DataFlavor[] flavors) {
        String results = "";
        for (int i = 0; i < flavors.length; i++) {
            results += "\n\t" + flavors[i];
        }
        return results;
    }

    protected abstract void onFileDrop(File file);
}
