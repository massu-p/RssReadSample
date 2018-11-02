package massu_p.co.jp.rssreadsample.tasks;

import android.os.AsyncTask;
import android.util.Log;
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
import massu_p.co.jp.rssreadsample.rss.entity.RssItem;

/**
 * RSSを取得するタスク
 */
public class RssReadTask extends AsyncTask {

	private URL rssUrl;
	private CallBack callBack;

	public interface CallBack {
		void onComplete(RssItem channel, List<RssItem> itemList);
		void onError(Exception e);
	}

	public RssReadTask(URL rssUrl, CallBack callBack) {
		this.rssUrl = rssUrl;
		this.callBack = callBack;
	}

	@Override
	protected void onPreExecute() {

	}

	@Override
	protected Object doInBackground(Object[] objects) {
		try {
			InputStream is = rssUrl.openConnection().getInputStream();
			parse(is);
		} catch (IOException e) {
			e.printStackTrace();
			callBack.onError(e);
		}
		return null;
	}

	private List<RssItem> parse(InputStream in) {
		XmlPullParser parser = Xml.newPullParser();
		List<RssItem> itemList = new ArrayList<RssItem>();
		RssItem channel = null;
		RssItem item = null;

		try {
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
								Log.i("parse", "Create ChannelItem:" + item);
								break;
							case ITEM:
								item = new RssItem(RssItem.TYPE.ITEM);
								Log.i("parse", "Create RssItem:" + item);
								break;
							case TITLE:
								if (item != null) {
									item.setTitle(parser.nextText());
									Log.i("parse", "set title(" + item + ") : " + item.getTitle());
								}
								break;
							case LINK:
								if (item != null) {
									item.setLink(parser.nextText());
									Log.i("parse", "set link(" + item + ") : " + item.getLink());
								}
								break;
							case DESCRIPTION:
								if (item != null) {
									item.setDescription(parser.nextText());
									Log.i("parse", "set description(" + item + ") : " + item.getDescription());
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
								Log.i("parse", "add list(" + item + ")");
								break;
						}
						break;
				}
				type = EventType.getEventType(parser.next());
			}
		} catch (XmlPullParserException | IOException e) {
			e.printStackTrace();
			callBack.onError(e);
		}
		callBack.onComplete(channel, itemList);
		return null;
	}

}
