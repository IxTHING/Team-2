package com.qa.team2.util;

import com.thoughtworks.xstream.XStream;

public class XMLUtil {

		private XStream xStream;

		public XMLUtil() {
			this.xStream = new XStream();
		}

		public String getXMLForObject(Object obj) {
			return xStream.toXML(obj); 
		}

		public Object getObjectForXML(String xmlString) {
			return xStream.fromXML(xmlString);
		}
}
