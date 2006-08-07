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

import com.codecrate.shard.feat.Feat;
import com.codecrate.shard.feat.FeatDao;
import com.codecrate.shard.feat.FeatFactory;

public class ExcelFeatRowHandler extends AbstractExcelRowHandler {
    private static final int NAME_COLUMN = 0;
    private static final int SUMMARY_COLUMN = 1;

    private final FeatFactory featFactory;
    private final FeatDao featDao;

    public ExcelFeatRowHandler(FeatFactory factory, FeatDao dao) {
        this.featFactory = factory;
        this.featDao = dao;
    }

    public Object handleRow(HSSFRow row) {
        String name = getStringFromRow(row, NAME_COLUMN);
        String summary = getStringFromRow(row, SUMMARY_COLUMN);
        Feat feat = featFactory.createFeat(name, summary, "General", null);
        return featDao.saveFeat(feat);
    }
}
