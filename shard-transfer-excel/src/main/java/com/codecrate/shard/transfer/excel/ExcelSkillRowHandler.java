/*
 * Copyright 2004 codecrate consulting
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package com.codecrate.shard.transfer.excel;

import org.apache.poi.hssf.usermodel.HSSFRow;

import com.codecrate.shard.skill.Skill;
import com.codecrate.shard.skill.SkillDao;
import com.codecrate.shard.skill.SkillFactory;

public class ExcelSkillRowHandler extends AbstractExcelRowHandler {
    private static final int NAME_COLUMN = 0;

    private final SkillFactory skillFactory;
    private final SkillDao skillDao;

    public ExcelSkillRowHandler(SkillFactory factory, SkillDao dao) {
        this.skillFactory = factory;
        this.skillDao = dao;
    }

    public Object handleRow(HSSFRow row) {
        String name = getStringFromRow(row, NAME_COLUMN);
        Skill skill = skillFactory.createSkill(name, null, true, false, null);
        return skillDao.saveSkill(skill);
    }
}
