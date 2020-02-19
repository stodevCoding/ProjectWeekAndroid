package com.stod.projectandroid;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.DrawableRes;

public class Questions implements Parcelable {

    public final float rate;
    @DrawableRes
    public final int flagId;
    public final String symbol;

    public Questions(int flagId, float rate, String symbol) {
        this.flagId = flagId;
        this.rate = rate;
        this.symbol = symbol;
    }


    protected Questions(Parcel in) {
        rate = in.readFloat();
        flagId = in.readInt();
        symbol = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeFloat(rate);
        dest.writeInt(flagId);
        dest.writeString(symbol);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Questions> CREATOR = new Creator<Questions>() {
        @Override
        public Questions createFromParcel(Parcel in) {
            return new Questions(in);
        }

        @Override
        public Questions[] newArray(int size) {
            return new Questions[size];
        }
    };
}
