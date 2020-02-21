package com.stod.projectandroid;

import android.os.Parcel;
import android.os.Parcelable;

public class AnswersQuestions implements Parcelable {
    public String sentence;
    public boolean isRight;

    public AnswersQuestions(String sentence, boolean isRight) {
        this.sentence = sentence;
        this.isRight = isRight;
    }

    protected AnswersQuestions(Parcel in) {
        sentence = in.readString();
        isRight = in.readByte() != 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(sentence);
        dest.writeByte((byte) (isRight ? 1 : 0));
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<AnswersQuestions> CREATOR = new Creator<AnswersQuestions>() {
        @Override
        public AnswersQuestions createFromParcel(Parcel in) {
            return new AnswersQuestions(in);
        }

        @Override
        public AnswersQuestions[] newArray(int size) {
            return new AnswersQuestions[size];
        }
    };
}
