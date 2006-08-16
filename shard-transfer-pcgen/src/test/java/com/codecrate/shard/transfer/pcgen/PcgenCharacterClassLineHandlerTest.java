package com.codecrate.shard.transfer.pcgen;

import junit.framework.TestCase;

import org.easymock.MockControl;

import com.codecrate.shard.dice.RandomDice;
import com.codecrate.shard.kit.CharacterClassDao;
import com.codecrate.shard.kit.CharacterClassFactory;

public class PcgenCharacterClassLineHandlerTest extends TestCase {

	public void testCharacterClassNameExtractedFromClassTag() {
		MockControl mockKitFactory = MockControl.createControl(CharacterClassFactory.class);
		CharacterClassFactory kitFactory = (CharacterClassFactory) mockKitFactory.getMock();
		kitFactory.createClass("My Kit", null, new RandomDice(6), null);
		mockKitFactory.setReturnValue(null);
		mockKitFactory.replay();

		MockControl mockKitDao = MockControl.createControl(CharacterClassDao.class);
		CharacterClassDao kitDao = (CharacterClassDao) mockKitDao.getMock();
		kitDao.saveClass(null);
		mockKitDao.setReturnValue(null);
		mockKitDao.replay();

		PcgenCharacterClassLineHandler handler = new PcgenCharacterClassLineHandler(kitFactory, kitDao, null);

		handler.handleLine("CLASS:My Kit \t HD:6 \t MAXLEVEL:0", null);
	}
}
