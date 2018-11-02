package massu_p.co.jp.rssreadsample.activities;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import massu_p.co.jp.rssreadsample.R;
import massu_p.co.jp.rssreadsample.rss.entity.RssItem;

public class RssItemActivity extends AppCompatActivity {

	private final static String KEY_RSS_ITEM = "KEY_RSS_ITEM";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_rss_item);

		RssItem item = (RssItem) getIntent().getParcelableExtra(KEY_RSS_ITEM);
		setTitle(item.getTitle());

		TextView description = (TextView) findViewById(R.id.rss_descriotion);
		description.setText(item.getDescription());
	}

	public static void startActivity(RssItem item, Activity activity) {
		Intent intent = new Intent(activity, RssItemActivity.class);
		intent.putExtra(KEY_RSS_ITEM, item);
		activity.startActivity(intent);
	}
}
