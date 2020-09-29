package com.example.quizapp.game.dao;

import org.apache.tomcat.jni.File;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class PayRollFileDao {
    private static final String FILE_PATH = "calc.txt";

    public void writePayRoll(List<PayRollQuiz> payRollQuiz) throws IOException{
        List<String> lines = new ArrayList<>();
        for (PayRollQuiz quiz : payRollQuiz){
            lines.add(quiz.toString());
        }

        Path path = Paths.get(FILE_PATH);
        Files.write(path, lines);
    }

    public List<PayRollQuiz> readPayRoll() throws IOException {
        Path path = Paths.get(FILE_PATH);
        List<String> lines = Files.readAllLines(path);

        List<PayRollQuiz> payRollQuiz = new ArrayList<>();
        for (String line : lines){
            payRollQuiz.add(PayRollQuiz.fromString(line));
        }
        return payRollQuiz;
    }
}
