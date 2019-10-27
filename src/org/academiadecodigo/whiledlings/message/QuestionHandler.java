package org.academiadecodigo.whiledlings.message;

import org.academiadecodigo.bootcamp.scanners.menu.MenuInputScanner;
import org.academiadecodigo.bootcamp.scanners.string.StringInputScanner;
import org.academiadecodigo.bootcamp.scanners.string.StringSetInputScanner;

import java.util.Set;

public class QuestionHandler {

    public static StringInputScanner buildQuestion(String message, String error){
        StringInputScanner question = new StringInputScanner();
        question.setMessage(message);
        question.setError(error);
        return question;
    }

    public static StringSetInputScanner buildQuestion(String message, String error, Set<String> options){
        StringSetInputScanner question = new StringSetInputScanner(options);
        question.setMessage(message);
        question.setError(error);
        return question;
    }
    public static MenuInputScanner buildQuestion(String message, String error, String[] options){
        MenuInputScanner question = new MenuInputScanner(options);
        question.setMessage(message);
        question.setError(error);
        return question;
    }
}
