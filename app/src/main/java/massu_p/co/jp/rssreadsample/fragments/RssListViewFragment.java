package massu_p.co.jp.rssreadsample.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import massu_p.co.jp.rssreadsample.R;
import massu_p.co.jp.rssreadsample.activities.RssItemActivity;
import massu_p.co.jp.rssreadsample.rss.entity.RssChannel;
import massu_p.co.jp.rssreadsample.rss.entity.RssItem;
import massu_p.co.jp.rssreadsample.rss.listener.OnRssItemClickListener;
import massu_p.co.jp.rssreadsample.rss.views.RssIndexViewAdapter;

/**
 * RSSの一覧を表示する画面
 */
public class RssListViewFragment extends Fragment {
	private static final String RSS_CHANNEL = "RSS_CHANNEL";

	public RssListViewFragment() {
	}

	public static RssListViewFragment newInstance(RssChannel channel) {
		RssListViewFragment fragment = new RssListViewFragment();
		Bundle args = new Bundle();
		args.putParcelable(RSS_CHANNEL, channel);
		fragment.setArguments(args);
		return fragment;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View mainView = inflater.inflate(R.layout.fragment_rss_list_view, container, false);

		Bundle bundle = getArguments();
		RssChannel channel = null;
		if (bundle != null) {
			channel = getArguments().getParcelable(RSS_CHANNEL);
		}
		if (channel != null) {
			RecyclerView rssIndexView = mainView.findViewById(R.id.rss_index);
			RssIndexViewAdapter rssIndexAdapter = new RssIndexViewAdapter(channel, new OnRssItemClickListener() {
				@Override
				public void onItemClick(RssItem item) {
					RssItemActivity.startActivity(item, getActivity());
				}
			});

			LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
			rssIndexView.setHasFixedSize(true);
			rssIndexView.setLayoutManager(linearLayoutManager);
			rssIndexView.setAdapter(rssIndexAdapter);
		}

		return mainView;
	}

	@Override
	public void onAttach(Context context) {
		super.onAttach(context);
	}

	@Override
	public void onDetach() {
		super.onDetach();
	}

}
