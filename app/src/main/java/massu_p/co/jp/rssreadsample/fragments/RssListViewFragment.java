package massu_p.co.jp.rssreadsample.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import massu_p.co.jp.rssreadsample.R;
import massu_p.co.jp.rssreadsample.activities.MainActivity;
import massu_p.co.jp.rssreadsample.activities.RssItemActivity;
import massu_p.co.jp.rssreadsample.rss.entity.RssChannel;
import massu_p.co.jp.rssreadsample.rss.entity.RssItem;
import massu_p.co.jp.rssreadsample.rss.listener.OnRssItemClickListener;
import massu_p.co.jp.rssreadsample.rss.views.RssIndexViewAdapter;

public class RssListViewFragment extends Fragment {
	// TODO: Rename parameter arguments, choose names that match
	private static final String ARG_PARAM1 = "param1";
	private static final String ARG_PARAM2 = "param2";

	// TODO: Rename and change types of parameters
	private RssChannel mParam1;

	private OnFragmentInteractionListener mListener;

	public RssListViewFragment() {
		// Required empty public constructor
	}

	public static RssListViewFragment newInstance(RssChannel channel) {
		RssListViewFragment fragment = new RssListViewFragment();
		Bundle args = new Bundle();
		args.putParcelable(ARG_PARAM1, channel);
		fragment.setArguments(args);
		return fragment;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		RssChannel channel = getArguments().getParcelable(ARG_PARAM1);

		View mainView = inflater.inflate(R.layout.fragment_rss_list_view, container, false);
		RecyclerView rssIndexView = (RecyclerView) mainView.findViewById(R.id.rss_index);
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

		return mainView;
	}

	@Override
	public void onAttach(Context context) {
		super.onAttach(context);
//		if (context instanceof OnFragmentInteractionListener) {
//			mListener = (OnFragmentInteractionListener) context;
//		} else {
//			throw new RuntimeException(context.toString()
//					+ " must implement OnFragmentInteractionListener");
//		}
	}

	@Override
	public void onDetach() {
		super.onDetach();
		mListener = null;
	}

	public interface OnFragmentInteractionListener {
		// TODO: Update argument type and name
		void onFragmentInteraction(Uri uri);
	}
}
