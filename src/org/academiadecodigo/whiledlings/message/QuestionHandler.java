package org.academiadecodigo.whiledlings.message;

import org.academiadecodigo.bootcamp.scanners.string.StringInputScanner;

public class QuestionHandler {

    public static StringInputScanner buildQuestion(String message, String error){
        StringInputScanner question = new StringInputScanner();
        question.setMessage(message);
        question.setError(error);
        return question;
    }
}
