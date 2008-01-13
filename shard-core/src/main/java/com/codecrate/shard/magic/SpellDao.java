package com.codecrate.shard.magic;

import java.util.Collection;

public interface SpellDao {

    Collection<Spell> getSpells();

    void updateSpell(Spell spell);

    Spell saveSpell(Spell spell);

    void deleteSpell(Spell spell);

    Collection<Spell> searchSpells(String query);
}
