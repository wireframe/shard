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
