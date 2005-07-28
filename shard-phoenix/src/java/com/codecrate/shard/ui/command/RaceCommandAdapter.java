package com.codecrate.shard.ui.command;

import com.codecrate.shard.race.Race;
import com.codecrate.shard.race.RaceDao;
import com.codecrate.shard.race.RaceFactory;

public class RaceCommandAdapter implements NewCommand, PropertiesCommand, DeleteCommand {
	
	private final RaceDao raceDao;
	private final RaceFactory raceFactory;

	private String deleteMessagePropertyName;
	
	public RaceCommandAdapter(RaceDao raceDao, RaceFactory raceFactory) {
		this.raceDao = raceDao;
		this.raceFactory = raceFactory;	
	}
	
	public Object createObject() {
		return raceFactory.createRace("New Race");
	}
	
	public void saveObject(Object object) {
		raceDao.saveRace((Race) object);
	}
	
	public void updateObject(Object object) {
		raceDao.updateRace((Race) object);
	}
	
	public void deleteObject(Object object) {
		raceDao.deleteRace((Race) object);
	}

	public String getDeleteMessagePropertyName() {
		return deleteMessagePropertyName;
	}

	public void setDeleteMessagePropertyName(String deleteMessagePropertyName) {
		this.deleteMessagePropertyName = deleteMessagePropertyName;
	}
}
