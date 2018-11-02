package massu_p.co.jp.rssreadsample.rss.views;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import massu_p.co.jp.rssreadsample.R;
import massu_p.co.jp.rssreadsample.rss.entity.RssItem;
import massu_p.co.jp.rssreadsample.rss.listener.OnRssItemClickListener;

/**
 * RSSの一覧表示用View
 */
public class RssIndexViewHolder extends RecyclerView.ViewHolder {

	public ImageView thumbnailView;
	public TextView titleView;
	public TextView descriptionView;

	public RssIndexViewHolder(@NonNull View itemView) {
		super(itemView);
		thumbnailView = (ImageView) itemView.findViewById(R.id.adapter_thumbnail);
		titleView = (TextView) itemView.findViewById(R.id.adapter_title);
		descriptionView = (TextView) itemView.findViewById(R.id.adapter_description);
	}

	public void bind(final RssItem item, final OnRssItemClickListener itemClickListener) {
		titleView.setText(item.getTitle());
		descriptionView.setText(item.getDescription());
		itemView.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				itemClickListener.onItemClick(item);
			}
		});
	}
}
