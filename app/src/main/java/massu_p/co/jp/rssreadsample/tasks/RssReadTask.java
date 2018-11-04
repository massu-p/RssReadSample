package massu_p.co.jp.rssreadsample.tasks;

import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.util.Xml;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import massu_p.co.jp.rssreadsample.rss.defines.ChannelElement;
import massu_p.co.jp.rssreadsample.rss.defines.EventType;
import massu_p.co.jp.rssreadsample.rss.entity.RssChannel;
import massu_p.co.jp.rssreadsample.rss.entity.RssItem;

/**
 * RSSを取得するタスク
 */
public class RssReadTask extends AsyncTask<Void, Void, RssReadResult> {

	private List<URL> rssUrlList;
	private CallBack callBack;

	public interface CallBack {
		void onComplete(ArrayList<RssChannel> channelList);

		void onError(RssReadResult.ResultType type);
	}

	/**
	 * 第１引数に指定したURLを読み込み、第２引数に指定したコールバックに結果を返します。
	 *
	 * @param urlList RSSのURL
	 * @param callBack 結果通知コールバック
	 */
	public RssReadTask(@NonNull List<URL> urlList, @NonNull CallBack callBack) {
		this.rssUrlList = urlList;
		this.callBack = callBack;
	}

	@Override
	protected void onPreExecute() {

	}

	@Override
	protected RssReadResult doInBackground(Void... objects) {
		try {
			ArrayList<RssChannel> channelList = new ArrayList<>();
			for (URL url : rssUrlList) {
				InputStream is = url.openConnection().getInputStream();
				channelList.add(parse(is));
			}
			return new RssReadResult(channelList);
		} catch (IOException e) {
			return new RssReadResult(RssReadResult.ResultType.NOT_CONNECTED);
		} catch (XmlPullParserException e) {
			return new RssReadResult(RssReadResult.ResultType.READ_FAILED);
		}
	}

	@Override
	protected void onPostExecute(RssReadResult result) {
		switch (result.getResultType()) {
			case SUCCESS:
				callBack.onComplete(result.getChannelList());
				break;
			default:
				callBack.onError(result.getResultType());
				break;
		}
	}

	private RssChannel parse(InputStream in) throws XmlPullParserException, IOException {
		XmlPullParser parser = Xml.newPullParser();
		ArrayList<RssItem> itemList = new ArrayList<RssItem>();
		RssItem channel = null;
		RssItem item = null;

		parser.setInput(in, "UTF-8");
		EventType type = EventType.getEventType(parser.getEventType());
		while (type != EventType.END_DOCUMENT) {
			ChannelElement element;
			switch (type) {
				case START_TAG:
					element = ChannelElement.getChannelElement(parser.getName());
					switch (element) {
						case CHANNEL:
							item = new RssItem(RssItem.TYPE.CHANNEL);
							break;
						case ITEM:
							item = new RssItem(RssItem.TYPE.ITEM);
							break;
						case TITLE:
							if (item != null) {
								item.setTitle(parser.nextText());
							}
							break;
						case LINK:
							if (item != null) {
								item.setLink(parser.nextText());
							}
							break;
						case DESCRIPTION:
							if (item != null) {
								item.setDescription(parser.nextText());
							}
							break;
					}
					break;
				case END_TAG:
					element = ChannelElement.getChannelElement(parser.getName());
					switch (element) {
						case CHANNEL:
							channel = item;
							break;
						case ITEM:
							itemList.add(item);
							break;
					}
					break;
			}
			type = EventType.getEventType(parser.next());
		}
		return new RssChannel(channel, itemList);
	}

}
