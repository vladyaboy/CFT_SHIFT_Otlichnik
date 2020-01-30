package study.example.cft_shift_otlichnik.features.questions.view;

import java.util.List;

import study.example.cft_shift_otlichnik.MvpView;
import study.example.cft_shift_otlichnik.features.questions.model.Question;

public interface QuestionListView extends MvpView {


    void filterQuestionList(String subjectName);

    void initQuestionList(List<Question> list);

    void showProgress();

    void hideProgress();

    void showError(String message);
}
