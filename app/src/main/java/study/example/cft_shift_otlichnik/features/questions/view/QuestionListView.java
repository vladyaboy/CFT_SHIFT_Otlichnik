package study.example.cft_shift_otlichnik.features.questions.view;

import java.util.List;

import study.example.cft_shift_otlichnik.MvpView;
import study.example.cft_shift_otlichnik.features.questions.model.Question;

public interface QuestionListView extends MvpView {

    void showQuestionList(List<Question> list);

    void filterQuestionList(String subjectName);

    void showProgress();

    void hideProgress();

    void showError(String message);
}
