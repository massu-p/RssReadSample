package massu_p.co.jp.rssreadsample.rss.views;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.View;
import android.widget.TextView;

import massu_p.co.jp.rssreadsample.R;
import massu_p.co.jp.rssreadsample.rss.entity.RssItem;
import massu_p.co.jp.rssreadsample.rss.listener.OnRssItemClickListener;

/**
 * RSSの一覧表示用View
 */
public class RssIndexViewHolder extends RecyclerView.ViewHolder {

	private Context context;
	private TextView titleView;
	private TextView descriptionView;

	public RssIndexViewHolder(View itemView) {
		super(itemView);
		this.context = itemView.getContext();
		titleView = itemView.findViewById(R.id.adapter_title);
		descriptionView = itemView.findViewById(R.id.adapter_description);
	}


	public void bindIsNormal(final RssItem item, final OnRssItemClickListener itemClickListener) {
		bind(item, false, itemClickListener);
	}

	public void bindIsHot(final RssItem item, final OnRssItemClickListener itemClickListener) {
		bind(item, true, itemClickListener);
	}

	private void bind(final RssItem item, boolean isHot, final OnRssItemClickListener itemClickListener) {
		titleView.setText(item.getTitle());
		descriptionView.setText(item.getDescription());
		if (isHot) {
			titleView.setTextSize(TypedValue.COMPLEX_UNIT_PX, context.getResources().getDimension(R.dimen.rss_index_title_size_hot));
			titleView.setTextColor(context.getColor(R.color.colorAccent));
			descriptionView.setTextSize(TypedValue.COMPLEX_UNIT_PX, context.getResources().getDimension(R.dimen.rss_index_description_size_hot));
		}
		itemView.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				itemClickListener.onItemClick(item);
			}
		});
	}
}
