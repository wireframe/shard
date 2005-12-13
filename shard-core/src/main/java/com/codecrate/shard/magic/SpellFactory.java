package com.codecrate.shard.magic;

import com.codecrate.shard.source.Source;

public interface SpellFactory {

    Spell createSpell(String name, String summary, Source source);
}
