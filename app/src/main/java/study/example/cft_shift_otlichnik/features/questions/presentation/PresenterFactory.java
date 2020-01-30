package study.example.cft_shift_otlichnik.features.questions.presentation;

import android.content.Context;

import study.example.cft_shift_otlichnik.App;
import study.example.cft_shift_otlichnik.features.questions.data.QuestionApi;
import study.example.cft_shift_otlichnik.features.questions.data.QuestionRepositoryImpl;
import study.example.cft_shift_otlichnik.features.questions.model.QuestionsRepository;

public final class PresenterFactory {
    public static QuestionListPresenter createPresenter(Context context) {
        final QuestionApi api = App.getRetrofitProvider(context)
                .getRetrofit()
                .create(QuestionApi.class);

        final QuestionsRepository repository = new QuestionRepositoryImpl(api);

        return new QuestionListPresenter(repository);
    }
}
