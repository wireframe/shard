package com.codecrate.shard;

import java.util.jar.Manifest;

public class ShardRuntime {

    public String getProjectVersion() {
        Package pkg = Manifest.class.getPackage();
        //String specVersion = pkg.getSpecificationVersion();
        String implVersion = pkg.getImplementationVersion();
        if (null == implVersion) {
            implVersion = "UNKNOWN";
        }
        return implVersion;
    }
}
