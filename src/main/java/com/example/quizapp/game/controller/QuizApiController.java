package com.example.quizapp.game.controller;

import com.example.quizapp.game.dao.Quiz;
import com.example.quizapp.game.dao.QuizFileDao;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


//API　画面がないDataのみを返している　プログラムから呼び出して利用する
@Controller
@RequestMapping("page")
public class QuizApiController {

    private List<Quiz> quizzes = new ArrayList<>();
    private QuizFileDao quizFileDao = new QuizFileDao();
    private Quiz quiz;

    /**
     * 遊ぶゲームの選択画面
     */
    @GetMapping("/select")
    public String select(){
        return "select";
    }

    /**
     * Make10
     */

    /*　ランダムでクイズを一軒だけ取得する　*/
    @GetMapping("quiz")
    public String quiz(Model model) {
        int index = new Random().nextInt(quizzes.size());

        model.addAttribute("quiz", quizzes.get(index));
        return "quiz";
    }

    @GetMapping("show")
    public String show(Model model) {
        model.addAttribute("quizzes",quizzes);
        return "list";
    }

    @GetMapping("check")
    public String check(Model model,
                        @RequestParam String question) {

        for (Quiz check : quizzes) {
            if (check.getQuestion().equals(question)) {
                model.addAttribute("quiz",check);
            }
        }
        return "redirect:/page/quiz";
    }

    @GetMapping("load")
    public String load(RedirectAttributes attributes) {
        try {
            quizzes = quizFileDao.read();//読み込んだ結果をフィールドに代入してあげる
            attributes.addFlashAttribute("successMessage","ファイルを読み込みました");
        } catch (IOException e) { //read()メソッドで投げられた例外をこちらの処理でキャッチしている
            e.printStackTrace();
            attributes.addFlashAttribute("errorMessage","ファイルの読み込みに失敗しました");
        }
        return "redirect:/page/show";
    }
}
