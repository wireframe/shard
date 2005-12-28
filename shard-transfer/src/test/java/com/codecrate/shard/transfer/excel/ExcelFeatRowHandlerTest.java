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

import java.io.File;
import java.util.Collection;

import junit.framework.TestCase;

import org.easymock.MockControl;

import com.codecrate.shard.feat.Feat;
import com.codecrate.shard.feat.FeatDao;
import com.codecrate.shard.feat.FeatFactory;
import com.codecrate.shard.transfer.FileUtils;
import com.codecrate.shard.transfer.excel.ExcelFeatRowHandler;

public class ExcelFeatRowHandlerTest extends TestCase {

    public void testBasicImport() throws Exception {
        MockControl mockFeat = MockControl.createControl(Feat.class);
        Feat feat = (Feat) mockFeat.getMock();
        mockFeat.replay();

        MockControl mockFeatFactory = MockControl.createControl(FeatFactory.class);
        FeatFactory featFactory = (FeatFactory) mockFeatFactory.getMock();
        featFactory.createFeat("Quickness", "Ability to run fast.", null);
        mockFeatFactory.setReturnValue(feat);
        featFactory.createFeat("NoSummary", null, null);
        mockFeatFactory.setReturnValue(feat);
        mockFeatFactory.replay();

        MockControl mockFeatDao = MockControl.createControl(FeatDao.class);
        FeatDao featDao = (FeatDao) mockFeatDao.getMock();
        featDao.saveFeat(feat);
        mockFeatDao.setReturnValue(feat);
        featDao.saveFeat(feat);
        mockFeatDao.setReturnValue(feat);
        mockFeatDao.replay();

        File file = FileUtils.getFile("excel/feats.xls");
        Collection results = new ExcelObjectImporter(new ExcelFeatRowHandler(featFactory, featDao)).importObjects(file);
        assertFalse(results.isEmpty());
    }

    public void testErrorImportingRowDoesNotStopImport() throws Exception {
        MockControl mockFeat = MockControl.createControl(Feat.class);
        Feat feat = (Feat) mockFeat.getMock();
        mockFeat.replay();

        MockControl mockFeatFactory = MockControl.createControl(FeatFactory.class);
        FeatFactory featFactory = (FeatFactory) mockFeatFactory.getMock();
        featFactory.createFeat("Quickness", "Ability to run fast.", null);
        mockFeatFactory.setThrowable(new IllegalArgumentException("Test: Error creating feat"));
        featFactory.createFeat("NoSummary", null, null);
        mockFeatFactory.setReturnValue(feat);
        mockFeatFactory.replay();

        MockControl mockFeatDao = MockControl.createControl(FeatDao.class);
        FeatDao featDao = (FeatDao) mockFeatDao.getMock();
        featDao.saveFeat(feat);
        mockFeatDao.setReturnValue(feat);
        featDao.saveFeat(feat);
        mockFeatDao.setReturnValue(feat);
        mockFeatDao.replay();

        File file = FileUtils.getFile("excel/feats.xls");
        Collection results = new ExcelObjectImporter(new ExcelFeatRowHandler(featFactory, featDao)).importObjects(file);
        assertFalse(results.isEmpty());
    }
}
