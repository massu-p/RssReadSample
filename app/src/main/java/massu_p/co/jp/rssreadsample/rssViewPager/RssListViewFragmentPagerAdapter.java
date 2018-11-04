package massu_p.co.jp.rssreadsample.rssViewPager;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

import massu_p.co.jp.rssreadsample.fragments.RssListViewFragment;
import massu_p.co.jp.rssreadsample.rss.entity.RssChannel;

public class RssListViewFragmentPagerAdapter extends FragmentPagerAdapter {

	private ArrayList<RssChannel> channelList;

	public RssListViewFragmentPagerAdapter(FragmentManager fm, ArrayList<RssChannel> channelList) {
		super(fm);
		this.channelList = channelList;
	}

	@Override
	public Fragment getItem(int position) {
		return RssListViewFragment.newInstance(channelList.get(position));
	}

	@Override
	public int getCount() {
		if (channelList == null) {
			return 0;
		} else {
			return channelList.size();
		}
	}

	@Override
	public CharSequence getPageTitle(int position) {
		return channelList.get(position).getChannel().getTitle();
	}
}
