package com.codecrate.shard.transfer.pcgen;

import java.util.Map;

import com.codecrate.shard.source.Source;
import com.codecrate.shard.source.SourceDao;
import com.codecrate.shard.source.SourceFactory;

public class PcgenSourceLineHandler extends AbstractPcgenLineHandler implements PcgenObjectImporter.PcgenLineHandler {
    private static final String SOURCE_NAME_TAG_NAME = "SOURCELONG";
    private static final String SOURCE_ABBREVIATION_TAG_NAME = "SOURCESHORT";
    private static final String SOURCE_URL_TAG_NAME = "SOURCEWEB";
	private final SourceFactory sourceFactory;
	private final SourceDao sourceDao;

	public PcgenSourceLineHandler(SourceDao sourceDao, SourceFactory sourceFactory) {
		this.sourceDao = sourceDao;
		this.sourceFactory = sourceFactory;
	}

	public boolean isSourceLine(String line) {
        return line.startsWith("SOURCE");
	}

	public Object handleLine(String line, Source source) {
		Map tags = getTagParser().parseTags(line);
		String name = (String) tags.get(SOURCE_NAME_TAG_NAME);

		return handleParsedLine(name, tags, source);
	}

	public Object handleParsedLine(String name, Map tags, Source arg) {
        Source source = sourceDao.getSource(name);
        if (null == source) {
            String abbreviation = (String) tags.get(SOURCE_ABBREVIATION_TAG_NAME);
            String url = (String) tags.get(SOURCE_URL_TAG_NAME);
            source = sourceFactory.createSource(name, abbreviation, url);
            source = sourceDao.saveSource(source);
        }
        return source;
	}
}
