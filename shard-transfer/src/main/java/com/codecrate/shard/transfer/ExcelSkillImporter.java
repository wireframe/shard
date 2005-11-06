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
package com.codecrate.shard.transfer;

import java.io.FileInputStream;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

import com.codecrate.shard.skill.Skill;
import com.codecrate.shard.skill.SkillDao;
import com.codecrate.shard.skill.SkillFactory;

public class ExcelSkillImporter {

    private final SkillFactory skillFactory;
    private final SkillDao skillDao;

    public ExcelSkillImporter(SkillFactory factory, SkillDao dao) {
        this.skillFactory = factory;
        this.skillDao = dao;
    }

    public void doImport() throws Exception {
        FileInputStream fin = new FileInputStream("/home/rsonnek/Projects/shard/shard-transfer/src/test/resources/skills.xls");
        POIFSFileSystem poifs = new POIFSFileSystem(fin);

        HSSFWorkbook workbook = new HSSFWorkbook(poifs);
        HSSFSheet sheet = workbook.getSheetAt(0);

        int startRow = sheet.getFirstRowNum();
        int lastRow = sheet.getLastRowNum();

        for (int currentRow = startRow; currentRow <= lastRow; currentRow++) {
            HSSFRow row = sheet.getRow(currentRow);

            String name = getStringFromRow(row, 1);
            Skill skill = skillFactory.createSkill(name);
            skillDao.saveSkill(skill);

        }
        fin.close();
    }

    private String getStringFromRow(HSSFRow row, int column) {
        HSSFCell nameCell = row.getCell((short) column);
        return nameCell.getStringCellValue();
    }
}
