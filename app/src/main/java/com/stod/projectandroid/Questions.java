package com.stod.projectandroid;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.DrawableRes;

import com.stod.projectandroid.api.AnswersData;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class Questions implements Parcelable {



    private final int resPokemon;
    private final String resType;
    private final String resAnimated;
    private final String difficulty;
    private List<AnswersQuestions> answers;


    public Questions(int resPokemon, String resType, String resAnimated, String difficulty, List<AnswersQuestions> answers) {
        this.resPokemon = resPokemon;
        this.resType = resType;
        this.resAnimated = resAnimated;
        this.difficulty = difficulty;
        this.answers = answers;
    }

    protected Questions(Parcel in) {
        resPokemon = in.readInt();
        resType = in.readString();
        resAnimated = in.readString();
        difficulty = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(resPokemon);
        dest.writeString(resType);
        dest.writeString(resAnimated);
        dest.writeString(difficulty);
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

    public int getResPokemon() {
        return resPokemon;
    }

    public String getResType() {
        return resType;
    }

    public String getResAnimated() {
        return resAnimated;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public List<AnswersQuestions> getAnswers() {
        return answers;
    }
}
