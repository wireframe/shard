package com.codecrate.shard.transfer.pcgen;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Collection;

public class PcgenSkillImporter {

    public Collection importSkills(File file) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));

        while (reader.ready()) {
            String line = reader.readLine();
            if (isSkill(line)) {
                System.out.println(line);
            }
        }

        reader.close();
        return null;
    }

    private boolean isSkill(String value) {
        return (!isEmpty(value) && !value.startsWith("SOURCE"));
    }

    private boolean isEmpty(String value) {
        return (null == value || value.trim().length() < 1  || value.startsWith("#"));
    }
}
