package massu_p.co.jp.rssreadsample.rss.listener;

import massu_p.co.jp.rssreadsample.rss.entity.RssItem;

/**
 * RSS一覧のitemタップリスナー
 * タップしたitemを渡す
 */
public interface OnRssItemClickListener {
	void onItemClick(RssItem item);
}
