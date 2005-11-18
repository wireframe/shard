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
import java.util.Collection;

import junit.framework.TestCase;

import org.easymock.MockControl;

import com.codecrate.shard.feat.Feat;
import com.codecrate.shard.feat.FeatDao;
import com.codecrate.shard.feat.FeatFactory;

public class ExcelFeatImporterTest extends TestCase {

    public void testBasicImport() throws Exception {
        MockControl mockFeat = MockControl.createControl(Feat.class);
        Feat feat = (Feat) mockFeat.getMock();
        mockFeat.replay();

        MockControl mockFeatFactory = MockControl.createControl(FeatFactory.class);
        FeatFactory featFactory = (FeatFactory) mockFeatFactory.getMock();
        featFactory.createFeat("Quickness", "Ability to run fast.");
        mockFeatFactory.setReturnValue(feat);
        mockFeatFactory.replay();

        MockControl mockFeatDao = MockControl.createControl(FeatDao.class);
        FeatDao featDao = (FeatDao) mockFeatDao.getMock();
        featDao.saveFeat(feat);
        mockFeatDao.setReturnValue(feat);
        mockFeatDao.replay();

        File file = new File(Thread.currentThread().getContextClassLoader().getResource("feats.xls").getFile());
        Collection results = new ExcelFeatImporter(featFactory, featDao).importObjects(file);
        assertFalse(results.isEmpty());
    }
}
