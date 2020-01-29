package study.example.cft_shift_otlichnik.features.login.presentation;

import java.util.List;

import study.example.cft_shift_otlichnik.MvpPresenter;
import study.example.cft_shift_otlichnik.features.login.model.SessionRepository;
import study.example.cft_shift_otlichnik.features.login.model.User;
import study.example.cft_shift_otlichnik.features.login.model.UserRepository;
import study.example.cft_shift_otlichnik.features.login.view.LoginView;

public final class LoginPresenter extends MvpPresenter<LoginView> {

    private final UserRepository userRepository;
    private final SessionRepository sessionRepository;

    public LoginPresenter(UserRepository userRepository, SessionRepository sessionRepository) {
        this.userRepository = userRepository;
        this.sessionRepository = sessionRepository;
    }

    @Override
    protected void onViewReady() {
        super.onViewReady();
        final List<User> userList = userRepository.getUserList();
        view.showUserList(userList);
    }

    public void onUserClick(User selectedUser) {
        if(selectedUser != null) {
            sessionRepository.setSessionId(selectedUser.getSessionId());
            view.openQuestionListScreen(selectedUser.getName());
        } else {
            view.showUserNotSelectedError();
        }
    }
}
