package com.loveplusplus.demo.chart;

import android.os.Parcel;
import android.os.Parcelable;

public class ChartBean  implements Parcelable{

	public String label;
	public int value;
	public String color;

	public ChartBean(String label, int value, String color) {
		this.label = label;
		this.value = value;
		this.color = color;
	}

	@Override
	public String toString() {
		return "ChartBean [label=" + label + ", value=" + value + ", color=" + color + "]";
	}
	
	public ChartBean(Parcel source){
		this.label=source.readString();
		this.value=source.readInt();
		this.color=source.readString();
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(label);
		dest.writeInt(value);
		dest.writeString(color);
	}
	
	//实例化静态内部对象CREATOR实现接口Parcelable.Creator  
    public static final Parcelable.Creator<ChartBean> CREATOR = new Creator<ChartBean>() {  
          
        @Override  
        public ChartBean[] newArray(int size) {  
            return new ChartBean[size];  
        }  
          
        //将Parcel对象反序列化为ParcelableDate  
        @Override  
        public ChartBean createFromParcel(Parcel source) {  
            return new ChartBean(source);  
        }  
    };  
	
}
