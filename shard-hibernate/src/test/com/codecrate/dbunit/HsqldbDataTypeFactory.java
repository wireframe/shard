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
package com.codecrate.dbunit;

import org.dbunit.dataset.datatype.DataType;
import org.dbunit.dataset.datatype.DataTypeException;
import org.dbunit.dataset.datatype.DefaultDataTypeFactory;

/**
 * Specialized factory that recognizes Hsqldb data types.
 *
 * @author Klas Axell
 */
public class HsqldbDataTypeFactory extends DefaultDataTypeFactory
{
    public DataType createDataType(int sqlType, String sqlTypeName) throws DataTypeException
    {
        if (sqlTypeName.equals("BOOLEAN"))
        {
            return DataType.BOOLEAN;
        }

        return super.createDataType(sqlType, sqlTypeName);
    }
}