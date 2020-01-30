package study.example.cft_shift_otlichnik.features.questions.model;

import java.util.List;

import study.example.cft_shift_otlichnik.network.Carry;

public interface QuestionsRepository {
    void loadQuestions(Carry<List<Question>> carry);

    //void loadQuestion(String id, Carry<Question> carry);

    void editQuestion(String id, String subject, String questionText, String answer, String author);

    void updateQuestions(Carry<List<Question>> carry);

    void createQuestion(Question question, Carry<Question> carry);

    void deleteQuestion(String id, Carry<Success> carry);
}
