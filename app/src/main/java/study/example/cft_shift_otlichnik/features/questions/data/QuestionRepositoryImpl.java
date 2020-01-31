package study.example.cft_shift_otlichnik.features.questions.data;

import java.util.List;

import study.example.cft_shift_otlichnik.features.questions.model.Question;
import study.example.cft_shift_otlichnik.features.questions.model.QuestionsRepository;
import study.example.cft_shift_otlichnik.features.questions.model.QuestionsResponse;
import study.example.cft_shift_otlichnik.features.questions.model.Success;
import study.example.cft_shift_otlichnik.network.Carry;
import study.example.cft_shift_otlichnik.network.DefaultCallback;

public class QuestionRepositoryImpl implements QuestionsRepository {

    private final QuestionApi questionApi;

    public QuestionRepositoryImpl(QuestionApi questionApi) { this.questionApi = questionApi; }

    @Override
    public void loadQuestions(Carry<QuestionsResponse> carry) {
        questionApi.getQuestionList().enqueue(new DefaultCallback(carry));
    }

    @Override
    public void editQuestion(String id, String subject, String questionText, String answer, String author) {
        //тут пока хз
    }


    @Override
    public void createQuestion(Question question, Carry<Question> carry) {
        questionApi.createQuestion(question).enqueue(new DefaultCallback(carry));
    }

    @Override
    public void deleteQuestion(String id, Carry<Success> carry) {
        questionApi.deleteQuestion(id).enqueue(new DefaultCallback(carry));
    }
}
