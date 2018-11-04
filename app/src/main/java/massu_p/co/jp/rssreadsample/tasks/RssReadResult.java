package massu_p.co.jp.rssreadsample.tasks;

import java.util.List;

import massu_p.co.jp.rssreadsample.rss.entity.RssItem;

/**
 * RssReadTaskでのRSS読み込み結果を格納するクラス
 */
public class RssReadResult {

	private ResultType resultType;
	private RssItem channel;
	private List<RssItem> itemList;

	public enum ResultType {
		SUCCESS, READ_FAILED, NOT_CONNECTED
	}

	public RssReadResult(RssItem channel, List<RssItem> itemList) {
		if (channel == null && itemList == null) {
			resultType = ResultType.READ_FAILED;
		} else {
			resultType = ResultType.SUCCESS;
			this.channel = channel;
			this.itemList = itemList;
		}
	}

	public RssReadResult(ResultType type) {
		resultType = type;
	}

	public ResultType getResultType() {
		return resultType;
	}

	public RssItem getChannel() {
		return channel;
	}

	public List<RssItem> getItemList() {
		return itemList;
	}

}
