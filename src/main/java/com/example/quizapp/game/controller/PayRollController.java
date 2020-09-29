package com.example.quizapp.game.controller;

import com.example.quizapp.game.dao.PayRollFileDao;
import com.example.quizapp.game.dao.PayRollQuiz;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Controller
@RequestMapping("play")
public class PayRollController {
    private List<PayRollQuiz> payRollQuizzes = new ArrayList<>();
    private PayRollFileDao payRollFileDao = new PayRollFileDao();
    private PayRollQuiz payRollQuiz;

    /**
     * 　Play用
     */
    @GetMapping("quiz")
    public String quiz(Model model) {
        int index = new Random().nextInt(payRollQuizzes.size());

        model.addAttribute("quiz", payRollQuizzes.get(index));
        return "PayRollQuiz";
    }

    /**
     * クイズの表示、登録(作成)、答え合わせ
     */
    @GetMapping("show")
    public String show(Model model) {
        model.addAttribute("payRollQuizzes", payRollQuizzes);
        return "PayRollList";
    }

    @PostMapping("create")
    public String create(@RequestParam String monthlyIncome) {
        payRollQuiz = new PayRollQuiz(monthlyIncome);
        payRollQuizzes.add(payRollQuiz);
        return "redirect:/play/show";
    }


    /**
     * txtファイル読み込み、保存
     */
    @PostMapping("save")
    public String save(RedirectAttributes attributes) {
        try {
            payRollFileDao.writePayRoll(payRollQuizzes);
            attributes.addFlashAttribute("successMessage", "ファイルに保存しました");
        } catch (IOException e) {
            e.printStackTrace();
            attributes.addFlashAttribute("errorMessage", "ファイルに失敗しました");
        }
        return "redirect:/play/show";
    }


    @GetMapping("load")
    public String load(RedirectAttributes attributes) {
        try {
            payRollQuizzes = payRollFileDao.readPayRoll();
            attributes.addFlashAttribute("successMassage", "ファイルを読み込みました");
        } catch (IOException e) {
            e.printStackTrace();
            attributes.addFlashAttribute("errorMessage", "ファイルの読み込みに失敗しました");
        }
        return "redirect:/play/show";
    }
}
