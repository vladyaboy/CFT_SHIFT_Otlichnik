package study.example.cft_shift_otlichnik.features.login.view;

import java.util.List;

import study.example.cft_shift_otlichnik.MvpView;
import study.example.cft_shift_otlichnik.features.login.model.User;

public interface LoginView extends MvpView {

    void showUserList(List<User> userList);

    void openMenuScreen(String userName);

    void showUserNotSelectedError();

}
