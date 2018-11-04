package massu_p.co.jp.rssreadsample.rss.defines;

/**
 * RSS Channelの要素定義
 */
public enum ChannelElement {
	CHANNEL("channel"),
	ITEM("item"),
	TITLE("title"),
	LINK("link"),
	DESCRIPTION("description"),
	IMAGE("image"),
	ITEMS("items"),
	TEXT_INPUT("textinput"),
	NOT_DEFINE("");

	private String element;

	ChannelElement(String element) {
		this.element = element;
	}

	public static ChannelElement getChannelElement(String element) {
		for (ChannelElement value : values()) {
			if (value.element.equals(element)) {
				return value;
			}
		}
		return NOT_DEFINE;
	}
}
