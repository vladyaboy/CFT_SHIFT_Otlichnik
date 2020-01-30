package study.example.cft_shift_otlichnik.features.questions.presentation;

import java.util.List;

import study.example.cft_shift_otlichnik.MvpPresenter;
import study.example.cft_shift_otlichnik.features.questions.model.Question;
import study.example.cft_shift_otlichnik.features.questions.model.QuestionsRepository;
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

    private void loadQuestions() {
        view.showProgress();
        questionsRepository.loadQuestions(new Carry<List<Question>>() {
            @Override
            public void onSuccess(List<Question> result) {
                view.showQuestionList(result);
                view.hideProgress();
            }

            @Override
            public void onFailure(Throwable throwable) {
                view.hideProgress();
                view.showError(throwable.getMessage());
            }
        });
    }


    public void onQuestionSelected(Question question) {
        //открытие вопросика

    }

    public void onCreateQuestion() {

    }
}
