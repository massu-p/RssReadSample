package massu_p.co.jp.rssreadsample.rss.views;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import massu_p.co.jp.rssreadsample.R;
import massu_p.co.jp.rssreadsample.rss.entity.RssChannel;
import massu_p.co.jp.rssreadsample.rss.entity.RssItem;
import massu_p.co.jp.rssreadsample.rss.listener.OnRssItemClickListener;

public class RssIndexViewAdapter extends RecyclerView.Adapter<RssIndexViewHolder> {

	private ArrayList<RssItem> rssItems;
	private OnRssItemClickListener itemClickListener;

	public RssIndexViewAdapter(RssChannel channel, OnRssItemClickListener itemClickListener) {
		this.rssItems = channel.getItemList();
		this.itemClickListener = itemClickListener;
	}

	@NonNull
	@Override
	public RssIndexViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
		View mainView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.adapter_rss_index, viewGroup, false);
		return new RssIndexViewHolder(mainView);
	}

	@Override
	public void onBindViewHolder(@NonNull RssIndexViewHolder viewHolder, int position) {
		if (position == 0) {
			viewHolder.bindIsHot(rssItems.get(position), itemClickListener);
		} else {
			viewHolder.bindIsNormal(rssItems.get(position), itemClickListener);
		}
	}

	@Override
	public int getItemCount() {
		return rssItems.size();
	}
}
