package com.codecrate.shard.transfer.pcgen;

import junit.framework.TestCase;

import org.easymock.MockControl;

import com.codecrate.shard.source.Source;
import com.codecrate.shard.source.SourceDao;

public class PcgenSourceLineHandlerTest extends TestCase {

	public void testLineIsConsideredSourceIfStartsWithSourceString() {
		PcgenSourceLineHandler handler = new PcgenSourceLineHandler(null, null);

		assertTrue(handler.isSourceLine("SOURCE:SRD"));
	}

	public void testSourceNameIsExtractedFromTheSourceLongTag() {
		Source source = new Source("My Source", null, null);

		MockControl mockSourceDao = MockControl.createControl(SourceDao.class);
		SourceDao sourceDao = (SourceDao) mockSourceDao.getMock();
		sourceDao.getSource("My Source");
		mockSourceDao.setReturnValue(source);
		mockSourceDao.replay();

		PcgenSourceLineHandler handler = new PcgenSourceLineHandler(sourceDao, null);

		handler.handleLine("SOURCELONG:My Source", null);
	}

}
