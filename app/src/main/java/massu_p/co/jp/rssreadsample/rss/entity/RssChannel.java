package massu_p.co.jp.rssreadsample.rss.entity;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class RssChannel implements Parcelable {

	private RssItem channel;
	private ArrayList<RssItem> itemList;

	public RssChannel(RssItem channel, ArrayList<RssItem> itemList) {
		this.channel = channel;
		this.itemList = itemList;
	}

	public RssItem getChannel() {
		return channel;
	}

	public ArrayList<RssItem> getItemList() {
		return itemList;
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeParcelable(this.channel, flags);
		dest.writeTypedList(this.itemList);
	}

	protected RssChannel(Parcel in) {
		this.channel = in.readParcelable(RssItem.class.getClassLoader());
		this.itemList = in.createTypedArrayList(RssItem.CREATOR);
	}

	public static final Parcelable.Creator<RssChannel> CREATOR = new Parcelable.Creator<RssChannel>() {
		@Override
		public RssChannel createFromParcel(Parcel source) {
			return new RssChannel(source);
		}

		@Override
		public RssChannel[] newArray(int size) {
			return new RssChannel[size];
		}
	};
}
