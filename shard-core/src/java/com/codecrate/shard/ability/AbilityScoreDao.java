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
package com.codecrate.shard.ability;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @author <a href="mailto:wireframe@dev.java.net">Ryan Sonnek</a>
 */
public class AbilityScoreDao {
    private static final Log LOG = LogFactory.getLog(AbilityScoreDao.class);
    
    public int getPointCost(int score) {
        int points = 0;
        
    	switch (score) {
    	case 0:
    		points = -8;
    		break;
    	case 1:
    		points = -7;
    		break;
    	case 2:
    		points = -6;
    		break;
    	case 3:
    		points = -5;
    		break;
    	case 4:
    		points = -4;
    		break;
    	case 5:
    		points = -3;
    		break;
    	case 6:
    		points = -2;
    		break;
    	case 7:
    		points = -1;
    		break;
    	case 8:
    		points = 0;
    		break;
    	case 9:
    		points = 1;
    		break;
    	case 10:
    		points = 2;
    		break;
    	case 11:
    		points = 3;
    		break;
    	case 12:
    		points = 4;
    		break;
    	case 13:
    		points = 5;
    		break;
    	case 14:
    		points = 6;
    		break;
    	case 15:
    		points = 8;
    		break;
    	case 16:
    		points = 10;
    		break;
    	case 17:
    		points = 13;
    		break;
    	case 18:
    		points = 16;
    		break;
    	case 19:
    		points = 19;
    		break;
    	case 20:
    		points = 22;
    		break;
    	case 21:
    		points = 25;
    		break;
    	case 22:
    		points = 28;
    		break;
    	default:
    		LOG.info("Unknown point cost for score: " + score);
    	}
    	
    	return points;
    }
}
