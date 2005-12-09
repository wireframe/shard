/*
 * Copyright 2002-2004 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package com.codecrate.shard.util;

import java.util.Comparator;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class ComparableComparator implements Comparator {
	private static final Log LOG = LogFactory.getLog(ComparableComparator.class);

	public int compare(Object object1, Object object2) {
		if (!(object1 instanceof Comparable)) {
			LOG.warn("Object is not comparable: " + object1.getClass());
			return 0;
		}
		Comparable compare1 = (Comparable) object1;
		Comparable compare2 = (Comparable) object2;
		
		return compare1.compareTo(compare2);
	}
}
