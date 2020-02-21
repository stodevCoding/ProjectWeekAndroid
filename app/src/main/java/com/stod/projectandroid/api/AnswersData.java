package com.stod.projectandroid.api;

import com.google.gson.annotations.SerializedName;

public class AnswersData {
    public String sentence;
    @SerializedName("is_right")
    public boolean isRight;
}
