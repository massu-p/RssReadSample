package massu_p.co.jp.rssreadsample.activities;

import android.os.AsyncTask;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import massu_p.co.jp.rssreadsample.R;
import massu_p.co.jp.rssreadsample.rss.entity.RssChannel;
import massu_p.co.jp.rssreadsample.rssViewPager.RssListViewFragmentPagerAdapter;
import massu_p.co.jp.rssreadsample.tasks.RssReadResult;
import massu_p.co.jp.rssreadsample.tasks.RssReadTask;

public class MainActivity extends AppCompatActivity {

	private ViewPager pager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		pager = (ViewPager) findViewById(R.id.view_pager);
		TabLayout tabLayout = (TabLayout) findViewById(R.id.view_table);
		tabLayout.setupWithViewPager(pager);
	}

	@Override
	protected void onStart() {
		super.onStart();
		try {
			List<URL> urlList = new ArrayList<>();
			urlList.add(new URL("http://b.hatena.ne.jp/hotentry.rss"));
			urlList.add(new URL("http://b.hatena.ne.jp/hotentry/social.rss"));

			new RssReadTask(urlList, new RssReadTask.CallBack() {
				@Override
				public void onComplete(final ArrayList<RssChannel> channelList) {
					pager.setAdapter(new RssListViewFragmentPagerAdapter(getSupportFragmentManager(), channelList));
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
