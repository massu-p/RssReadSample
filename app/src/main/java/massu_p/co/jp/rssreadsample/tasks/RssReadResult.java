package massu_p.co.jp.rssreadsample.tasks;

import java.util.ArrayList;

import massu_p.co.jp.rssreadsample.rss.entity.RssChannel;

/**
 * RssReadTaskでのRSS読み込み結果を格納するクラス
 */
public class RssReadResult {

	private ResultType resultType;
	private ArrayList<RssChannel> channelList;

	public enum ResultType {
		SUCCESS, READ_FAILED, NOT_CONNECTED
	}

	public RssReadResult(ArrayList<RssChannel> channelList) {
		if (channelList == null) {
			resultType = ResultType.READ_FAILED;
		} else {
			resultType = ResultType.SUCCESS;
			this.channelList = channelList;
		}
	}

	public RssReadResult(ResultType type) {
		resultType = type;
	}

	public ResultType getResultType() {
		return resultType;
	}

	public ArrayList<RssChannel> getChannelList() {
		return channelList;
	}

}
