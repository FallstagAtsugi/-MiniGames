package com.example.quizapp.game.dao;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@AllArgsConstructor
public class PayRollQuiz {
    /*問題文*/
    @NotNull(message = "数字を入力してください")
    @Size(max = 250, message = "お手数をおかけしますが、"
            + "250以下の数字でお願いいたします")
    private String monthlyIncome; //todo:入力チェック数字の文字列が入っていること
    /*年収(×12)*/
    private String annualIncome;

    public PayRollQuiz(String monthlyIncome) {
        this.monthlyIncome = monthlyIncome;
    }
    /*正解*/ //あとで実装
    // private boolean answer;  //年収と比較し、一致するときは正解！

    @Override
    public String toString() {
        return this.monthlyIncome + " "
                + Integer.parseInt(monthlyIncome) * 12;
    }

    public static PayRollQuiz fromString(String line) {
        String[] tmp = line.split("\\s+");
        String num = String.valueOf(Integer.parseInt(tmp[0]) * 12);
        return new PayRollQuiz(tmp[0], num);
    }

}
