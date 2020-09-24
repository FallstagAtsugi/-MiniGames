package com.example.quizapp.make10.dao;


import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor //全メンバを設定するコンストラクタ
public class Quiz {
    /**
     * 問題文
     */
    private String question;

    @Override
    public String toString(){
        return  question;
    }

    //line・・・・問題文　〇
    public static Quiz fromString(String line){
        return new Quiz(line);
    }
}
