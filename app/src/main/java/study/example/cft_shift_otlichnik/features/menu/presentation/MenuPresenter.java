package study.example.cft_shift_otlichnik.features.menu.presentation;

import android.view.Menu;

import study.example.cft_shift_otlichnik.MvpPresenter;
import study.example.cft_shift_otlichnik.features.menu.view.MenuView;

public class MenuPresenter extends MvpPresenter<MenuView> {

    public void practiceButtonClick() {
        view.showPracticeScreen();
    }

    public void examButtonClick() {
        view.showExamScreen();
    }

}
