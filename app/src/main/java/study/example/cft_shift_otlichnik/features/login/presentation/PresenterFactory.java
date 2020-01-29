package study.example.cft_shift_otlichnik.features.login.presentation;

import android.content.Context;

import study.example.cft_shift_otlichnik.features.login.model.SessionRepository;
import study.example.cft_shift_otlichnik.features.login.model.UserRepository;
import study.example.cft_shift_otlichnik.features.login.presentation.LoginPresenter;
import study.example.cft_shift_otlichnik.features.login.repository.SessionRepositoryImpl;
import study.example.cft_shift_otlichnik.features.login.repository.UserRepositoryImpl;

public final class PresenterFactory {

    public static LoginPresenter createPresenter(Context context) {
        final UserRepository userRepository = new UserRepositoryImpl();

        final SessionRepository sessionRepository = new SessionRepositoryImpl(context);

        return new LoginPresenter(userRepository, sessionRepository);
    }
}
