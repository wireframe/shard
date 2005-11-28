package com.codecrate.shard.transfer;

import com.thoughtworks.xstream.XStream;

public class XmlExport {

	public String doStuff(Object arg) {
		XStream xstream = new XStream();
		
		return xstream.toXML(arg);
	}
}
