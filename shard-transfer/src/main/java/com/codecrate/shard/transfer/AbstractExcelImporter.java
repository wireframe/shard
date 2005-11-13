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

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Collection;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

public abstract class AbstractExcelImporter {
    private static final Log LOG = LogFactory.getLog(AbstractExcelImporter.class);

    public Collection importObjects(File file) {
        Collection results = new ArrayList();

        try {
            POIFSFileSystem poifs = new POIFSFileSystem(new FileInputStream(file));

            HSSFWorkbook workbook = new HSSFWorkbook(poifs);
            HSSFSheet sheet = workbook.getSheetAt(0);

            int firstRow = sheet.getFirstRowNum();
            int lastRow = sheet.getLastRowNum();

            for (int currentRow = firstRow; currentRow <= lastRow; currentRow++) {
                HSSFRow row = sheet.getRow(currentRow);

                Object result = handleRow(row);
                results.add(result);
            }
        } catch (Exception e) {
            LOG.error("Error importing file: " + file, e);
        }

        return results;
    }

    protected abstract Object handleRow(HSSFRow row);

    protected String getStringFromRow(HSSFRow row, int column) {
        HSSFCell nameCell = row.getCell((short) column);
        return nameCell.getStringCellValue();
    }
}
