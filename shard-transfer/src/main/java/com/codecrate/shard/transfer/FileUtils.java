package com.codecrate.shard.transfer;

import java.io.File;

public class FileUtils {

    public static File getFile(String filename) {
        return new File(Thread.currentThread().getContextClassLoader().getResource(filename).getFile());
    }
}
