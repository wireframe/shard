package com.codecrate.shard.magic;

public interface SpellFactory {

    Spell createSpell(String name, String summary);
}
