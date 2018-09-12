package com.example.manup.group32_homework03;

import java.io.Serializable;
import java.util.Arrays;

/**
 * Created by manup on 2/16/2018.
 */

public class Question implements Serializable {

    private int questionno;
    private String data;
    private String image;
    private String[] choices;
    private int answer;

    public Question(int questionno, String data, String image, String[] choices, int answer) {
        this.questionno = questionno;
        this.data = data;
        this.image = image;
        this.choices = choices;
        this.answer = answer;
    }

    public int getQuestionno() {
        return questionno;
    }

    public void setQuestionno(int questionno) {
        this.questionno = questionno;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String[] getChoices() {
        return choices;
    }

    public void setChoices(String[] choices) {
        this.choices = choices;
    }

    public int getAnswer() {
        return answer;
    }

    public void setAnswer(int answer) {
        this.answer = answer;
    }

    @Override
    public String toString() {
        return "Question{" +
                "questionno=" + questionno +
                ", data='" + data + '\'' +
                ", image='" + image + '\'' +
                ", choices=" + Arrays.toString(choices) +
                ", answer=" + answer +
                '}';
    }
}
