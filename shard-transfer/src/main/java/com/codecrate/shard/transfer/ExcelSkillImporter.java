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
