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

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;

/**
 * helper class for parsing excel data rows.
 */
public abstract class AbstractExcelRowHandler implements ExcelObjectImporter.ExcelRowHandler {
    private static final Log LOG = LogFactory.getLog(AbstractExcelRowHandler.class);

    /**
     *
     * @param row
     * @param column zero indexed column number
     * @return
     */
    protected String getStringFromRow(HSSFRow row, int column) {
        HSSFCell nameCell = row.getCell((short) column);
        if (null == nameCell) {
            LOG.info("No string value found for [row, column]: " + row.getRowNum() + ", " + column);
            return null;
        }
        return nameCell.getStringCellValue();
    }

}
