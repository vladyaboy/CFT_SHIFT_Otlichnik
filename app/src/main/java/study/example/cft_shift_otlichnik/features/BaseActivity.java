package study.example.cft_shift_otlichnik.features;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import study.example.cft_shift_otlichnik.MvpPresenter;
import study.example.cft_shift_otlichnik.MvpView;

public abstract class BaseActivity extends AppCompatActivity {
    protected abstract <T extends MvpView> MvpPresenter<T> getPresenter();

    protected abstract <T extends MvpView> T getMvpView();

    private MvpPresenter<MvpView> presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = getPresenter();
    }

    @Override
    protected void onStart() {
        super.onStart();
        presenter.attachView(getMvpView());
    }

    @Override
    protected void onStop() {
        super.onStop();
        presenter.detachView();
    }
}
