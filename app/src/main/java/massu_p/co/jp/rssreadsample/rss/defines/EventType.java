package massu_p.co.jp.rssreadsample.rss.defines;

import org.xmlpull.v1.XmlPullParser;

/**
 * XmlPullParser のイベントタイプ定義
 */
public enum EventType {
	START_DOCUMENT(XmlPullParser.START_DOCUMENT),
	END_DOCUMENT(XmlPullParser.END_DOCUMENT),
	START_TAG(XmlPullParser.START_TAG),
	END_TAG(XmlPullParser.END_TAG),
	TEXT(XmlPullParser.TEXT),
	CDSECT(XmlPullParser.CDSECT),
	ENTITY_REF(XmlPullParser.ENTITY_REF),
	IGNORABLE_WHITESPACE(XmlPullParser.IGNORABLE_WHITESPACE),
	PROCESSING_INSTRUCTION(XmlPullParser.PROCESSING_INSTRUCTION),
	COMMENT(XmlPullParser.COMMENT),
	DOCDECL(XmlPullParser.DOCDECL),
	NOT_DEFINE(-1);

	private int eventType;

	EventType(int eventType) {
		this.eventType = eventType;
	}

	public static EventType getEventType(int eventType) {
		for (EventType value : values()) {
			if (value.eventType == eventType) {
				return value;
			}
		}
		return NOT_DEFINE;
	}
}

