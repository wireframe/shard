package com.codecrate.shard;

import org.springframework.richclient.application.ApplicationLauncher;

/**
 *
 */
public class ShardPhoenix {

    public static void main(String[] args) {
        try {
            new ApplicationLauncher("/richclient-startup-context.xml", new String[] {
                    "/richclient-application-context.xml"
                    , "/shard-hibernate-context.xml"
            });
        } catch (Exception e) {
            System.exit(1);
        }
    }
}
