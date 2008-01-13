package com.codecrate.shard.transfer.pcgen;

import com.codecrate.shard.source.Source;
import com.codecrate.shard.source.SourceDao;
import com.codecrate.shard.source.SourceFactory;
import com.codecrate.shard.transfer.pcgen.tag.PcgenTags;

public class PcgenSourceLineHandler implements PcgenObjectImporter.PcgenLineHandler {
    private static final String SOURCE_NAME_TAG_NAME = "SOURCELONG";
    private static final String SOURCE_ABBREVIATION_TAG_NAME = "SOURCESHORT";
    private static final String SOURCE_URL_TAG_NAME = "SOURCEWEB";

    private final SourceDao sourceDao;

	public PcgenSourceLineHandler(SourceDao sourceDao) {
		this.sourceDao = sourceDao;
	}

	public boolean isSourceLine(String line) {
        return line.startsWith("SOURCE");
	}

    public Object handleLine(String line, Source arg) {
    	PcgenTags tags = new PcgenTags(line);
    	String name = tags.getStringTagValue(SOURCE_NAME_TAG_NAME);

    	Source source = sourceDao.getSource(name);
        if (null == source) {
            String abbreviation = tags.getStringTagValue(SOURCE_ABBREVIATION_TAG_NAME);
            String url = tags.getStringTagValue(SOURCE_URL_TAG_NAME);
            source = new Source(name, abbreviation, url);
            source = sourceDao.saveSource(source);
        }
        return source;
	}
}
