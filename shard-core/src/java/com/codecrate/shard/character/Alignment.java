package com.codecrate.shard.character;

/**
 * defines an alignment.
 */
public interface Alignment {
    boolean isLawful();
    boolean isChaotic();
    
    /**
     * returns if the alignment is neutral.
     * TODO: is the alignment true neutral?
     * @return
     */
    boolean isNeutral();
    
    boolean isGood();
    boolean isEvil();
}
