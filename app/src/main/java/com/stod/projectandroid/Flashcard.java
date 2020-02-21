package com.stod.projectandroid;

import com.stod.projectandroid.api.AnswersData;

import java.util.ArrayList;
import java.util.List;

public class Flashcard {
    String resPokemon  ;
    String resType  ;
    String resAnimated  ;
    String difficulty ;
    AnswersData[] answers ;
    List<AnswersQuestions> answersPurposeList = new ArrayList<AnswersQuestions>();

    public Flashcard(String resPokemon, String resType, String resAnimated, String difficulty, AnswersData[] answers, List<AnswersQuestions> answersPurposeList) {
        this.resPokemon = resPokemon;
        this.resType = resType;
        this.resAnimated = resAnimated;
        this.difficulty = difficulty;
        this.answers = answers;
        this.answersPurposeList = answersPurposeList;
    }

    public String getResPokemon() {
        return resPokemon;
    }

    public void setResPokemon(String resPokemon) {
        this.resPokemon = resPokemon;
    }

    public String getResType() {
        return resType;
    }

    public void setResType(String resType) {
        this.resType = resType;
    }

    public String getResAnimated() {
        return resAnimated;
    }

    public void setResAnimated(String resAnimated) {
        this.resAnimated = resAnimated;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public AnswersData[] getAnswers() {
        return answers;
    }

    public void setAnswers(AnswersData[] answers) {
        this.answers = answers;
    }

    public List<AnswersQuestions> getAnswersPurposeList() {
        return answersPurposeList;
    }

    public void setAnswersPurposeList(List<AnswersQuestions> answersPurposeList) {
        this.answersPurposeList = answersPurposeList;
    }
}
