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
package com.codecrate.shard.race;

import java.util.ArrayList;
import java.util.Collection;

public class LanguageDao {

    public Collection getLanguages() {
        Collection languages = new ArrayList();
        languages.add(Language.ABYSSAL);
        languages.add(Language.AQUAN);
        languages.add(Language.AURAN);
        languages.add(Language.CELESTIAL);
        languages.add(Language.COMMON);
        languages.add(Language.DRACONIC);
        languages.add(Language.DRUIDIC);
        languages.add(Language.DWARVEN);
        languages.add(Language.ELVEN);
        languages.add(Language.GIANT);
        languages.add(Language.GNOME);
        languages.add(Language.GOBLIN);
        languages.add(Language.GNOLL);
        languages.add(Language.HALFLING);
        languages.add(Language.IGNAN);
        languages.add(Language.INFERNAL);
        languages.add(Language.ORC);
        languages.add(Language.SYLVAN);
        languages.add(Language.TERRAN);
        languages.add(Language.UNDERCOMMON);

        return languages;
    }
}
