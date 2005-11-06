package com.codecrate.shard.transfer;

import junit.framework.TestCase;

import org.easymock.MockControl;

import com.codecrate.shard.skill.Skill;
import com.codecrate.shard.skill.SkillDao;
import com.codecrate.shard.skill.SkillFactory;

public class ExcelSkillImporterTest extends TestCase {

    public void testBasicImport() throws Exception {
        MockControl mockSkill = MockControl.createControl(Skill.class);
        Skill skill = (Skill) mockSkill.getMock();
        mockSkill.replay();

        MockControl mockSkillFactory = MockControl.createControl(SkillFactory.class);
        SkillFactory skillFactory = (SkillFactory) mockSkillFactory.getMock();
        skillFactory.createSkill("Ability to climb sheer surfaces");
        mockSkillFactory.setReturnValue(skill);
        mockSkillFactory.replay();

        MockControl mockSkillDao = MockControl.createControl(SkillDao.class);
        SkillDao skillDao = (SkillDao) mockSkillDao.getMock();
        skillDao.saveSkill(skill);
        mockSkillDao.setReturnValue(skill);
        mockSkillDao.replay();

        new ExcelSkillImporter(skillFactory, skillDao).doImport();
    }
}
