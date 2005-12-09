package com.codecrate.shard.magic;

import java.util.Collection;

public interface SpellDao {

    Collection getSpells();

    Spell getSpell(String name);

    void updateSpell(Spell spell);

    Spell saveSpell(Spell spell);

    void deleteSpell(Spell spell);

    Collection searchSpells(String query);
}
