package study.example.cft_shift_otlichnik.features.questions.model;

import java.util.List;

public class QuestionsResponse {

    private List<Question> questions;

    public  QuestionsResponse (List<Question> list) {
        this.questions = list;
    }


    public List<Question> getQuestions() {
        return questions;
    }
}
