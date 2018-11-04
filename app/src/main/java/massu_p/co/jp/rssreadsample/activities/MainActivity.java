package massu_p.co.jp.rssreadsample.activities;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import massu_p.co.jp.rssreadsample.R;
import massu_p.co.jp.rssreadsample.rss.entity.RssItem;
import massu_p.co.jp.rssreadsample.rss.listener.OnRssItemClickListener;
import massu_p.co.jp.rssreadsample.rss.views.RssIndexViewAdapter;
import massu_p.co.jp.rssreadsample.tasks.RssReadResult;
import massu_p.co.jp.rssreadsample.tasks.RssReadTask;

public class MainActivity extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	@Override
	protected void onStart() {
		super.onStart();
		try {
			URL url = new URL("http://b.hatena.ne.jp/hotentry.rss");
			new RssReadTask(url, new RssReadTask.CallBack() {
				@Override
				public void onComplete(final RssItem channel, final List<RssItem> itemList) {
					runOnUiThread(new Runnable() {
						@Override
						public void run() {
							RecyclerView rssIndexView = (RecyclerView) findViewById(R.id.rss_index);
							RssIndexViewAdapter rssIndexAdapter = new RssIndexViewAdapter(itemList, new OnRssItemClickListener() {
								@Override
								public void onItemClick(RssItem item) {
									RssItemActivity.startActivity(item, MainActivity.this);
								}
							});
							LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
							rssIndexView.setHasFixedSize(true);
							rssIndexView.setLayoutManager(linearLayoutManager);
							rssIndexView.setAdapter(rssIndexAdapter);
						}
					});
				}

				@Override
				public void onError(RssReadResult.ResultType type) {
					switch(type) {
						case READ_FAILED:
							Toast.makeText(getApplicationContext(), R.string.failed_rss_read, Toast.LENGTH_LONG).show();
							break;
						case NOT_CONNECTED:
							Toast.makeText(getApplicationContext(), R.string.failed_connect, Toast.LENGTH_LONG).show();
							break;
					}
				}
			}).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
		} catch (MalformedURLException e) {
			Toast.makeText(getApplicationContext(), R.string.failed_connect, Toast.LENGTH_LONG).show();
		}
	}
}
