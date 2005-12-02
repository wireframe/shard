package com.codecrate.shard.transfer.pcgen;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.StringTokenizer;

import com.codecrate.shard.skill.Skill;
import com.codecrate.shard.skill.SkillDao;
import com.codecrate.shard.skill.SkillFactory;

public class PcgenSkillImporter {

//    private final SkillFactory skillFactory;
//    private final SkillDao skillDao;
//
//    public PcgenSkillImporter(SkillFactory skillFactory, SkillDao skillDao) {
//        this.skillFactory = skillFactory;
//        this.skillDao = skillDao;
//    }
//
    public Collection importSkills(File file) throws Exception {
        Collection results = new ArrayList();
        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));

        while (reader.ready()) {
            String line = reader.readLine();
            if (isUsableRow(line)) {
                handleLine(line);
            }
        }

        reader.close();
        return results;
    }

    private void handleLine(String line) {
        StringTokenizer tokens = new StringTokenizer(line, "\t");

        String name = tokens.nextToken().trim();

        while (tokens.hasMoreTokens()) {
            String token = tokens.nextToken().trim();

            String[] tags = token.split(":");
            String tagName = tags[0].trim();
            String tagValue = tags[1].trim();

            handleTag(tagName, tagValue);
        }

        //Skill skill = skillFactory.createSkill(name);
        //results.add(skillDao.saveSkill(skill));
    }

    private void handleTag(String tagName, String tagValue) {
        System.out.println(tagName);
        System.out.println(tagValue);

//                    if (PObjectLoader.parseTag(obj, token))
//                    {
//                        continue;
//                    }
//                    else if (token.startsWith("ACHECK:"))
//                    {
//                        obj.setACheck(token.substring(7));
//                    }
//                    else if (token.startsWith("CLASSES:"))
//                    {
//                        obj.addClassList(token.substring(8));
//                    }
//
//                    //else if (colString.startsWith(Constants.s_TAG_TYPE))
//                    //{
//                    //  obj.setType(colString.substring(Constants.s_TAG_TYPE.length()));
//                    //}
//                    else if (token.startsWith("EXCLUSIVE:"))
//                    {
//                        obj.setIsExclusive(token.charAt(10) == 'Y');
//                    }
//                    else if (token.startsWith("KEYSTAT:"))
//                    {
//                        obj.setKeyStat(token.substring(8));
//                    }
//                    else if (token.startsWith("QUALIFY:"))
//                    {
//                        obj.setQualifyString(token.substring(8));
//                    }
//                    else if ("REQ".equals(token))
//                    {
//                        obj.setRequired(true);
//                    }
//                    else if (token.startsWith("ROOT:"))
//                    {
//                        obj.setRootName(token.substring(5));
//                    }
//                    else if (token.startsWith("USEUNTRAINED:"))
//                    {
//                        obj.setUntrained(token.substring(13));
//                    }
    }

    private boolean isUsableRow(String value) {
        return (!isEmpty(value) && !value.startsWith("SOURCE"));
    }

    private boolean isEmpty(String value) {
        return (null == value || value.trim().length() < 1  || value.startsWith("#"));
    }
}
