package study.example.cft_shift_otlichnik.features.questions.presentation;

import android.widget.Toast;

import java.util.List;

import study.example.cft_shift_otlichnik.MvpPresenter;
import study.example.cft_shift_otlichnik.features.questions.model.Question;
import study.example.cft_shift_otlichnik.features.questions.model.QuestionsRepository;
import study.example.cft_shift_otlichnik.features.questions.model.QuestionsResponse;
import study.example.cft_shift_otlichnik.features.questions.view.QuestionListView;
import study.example.cft_shift_otlichnik.network.Carry;

public final class QuestionListPresenter extends MvpPresenter<QuestionListView> {

    private final QuestionsRepository questionsRepository;

    QuestionListPresenter(QuestionsRepository questionsRepository) {
        this.questionsRepository = questionsRepository;
    }

    @Override
    protected void onViewReady() {
        super.onViewReady();
        loadQuestions();
    }

    //Скачиваем вопросы с сервера. Пробуем, по крайней мере
    private void loadQuestions() {
        view.showProgress();
        questionsRepository.loadQuestions(new Carry<QuestionsResponse>() {
            @Override
            public void onSuccess(QuestionsResponse result) {
                //Вызываем метод в активити, который передает адаптеру данные со списком вопросов, пришедшим с сервера
                view.initQuestionList(result.getQuestions());
                view.initSubjectList(result.getQuestions());
                view.hideProgress();
            }

            @Override
            public void onFailure(Throwable throwable) {
                view.hideProgress();
                view.showError(throwable.getMessage());
            }
        });
    }

    public void updateQuestion(Question question) {
        questionsRepository.updateQuestion(question.getId(), question, new Carry<Question>() {
            @Override
            public void onSuccess(Question result) {
                view.showError("Вопрос успешно редактирован!");
            }

            @Override
            public void onFailure(Throwable throwable) {
                view.showError(throwable.getMessage());
            }
        });
    }


    public void onQuestionSelected(Question question) {
        //открытие вопросика
    }


    public void onCreateQuestion(Question question) {
        questionsRepository.createQuestion(question, new Carry<Question>() {
            @Override
            public void onSuccess(Question result) {
                view.showError("Вопрос успешно добавлен!");
            }

            @Override
            public void onFailure(Throwable throwable) {
                view.showError(throwable.getMessage());
            }
        });
    }
}
