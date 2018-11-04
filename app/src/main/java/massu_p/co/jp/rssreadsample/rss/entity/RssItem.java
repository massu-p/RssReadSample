package massu_p.co.jp.rssreadsample.rss.entity;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Itemの定義
 * Channel要素かItem要素の判断は TYPEで判断する
 */
public class RssItem implements Parcelable {

	private TYPE type;
	private String title;
	private String link;
	private String description;

	public enum TYPE {
		CHANNEL, ITEM
	}

	public RssItem(TYPE type) {
		this.type = type;
	}

	public TYPE getType() {
		return type;
	}

	public void setType(TYPE type) {
		this.type = type;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeInt(this.type == null ? -1 : this.type.ordinal());
		dest.writeString(this.title);
		dest.writeString(this.link);
		dest.writeString(this.description);
	}

	private RssItem(Parcel in) {
		int tmpType = in.readInt();
		this.type = tmpType == -1 ? null : TYPE.values()[tmpType];
		this.title = in.readString();
		this.link = in.readString();
		this.description = in.readString();
	}

	public static final Parcelable.Creator<RssItem> CREATOR = new Parcelable.Creator<RssItem>() {
		@Override
		public RssItem createFromParcel(Parcel source) {
			return new RssItem(source);
		}

		@Override
		public RssItem[] newArray(int size) {
			return new RssItem[size];
		}
	};
}
