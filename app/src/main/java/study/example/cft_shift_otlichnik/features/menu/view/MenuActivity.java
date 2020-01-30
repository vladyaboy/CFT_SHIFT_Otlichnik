package study.example.cft_shift_otlichnik.features.menu.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import okhttp3.Interceptor;
import study.example.cft_shift_otlichnik.MvpPresenter;
import study.example.cft_shift_otlichnik.MvpView;
import study.example.cft_shift_otlichnik.R;
import study.example.cft_shift_otlichnik.features.BaseActivity;
import study.example.cft_shift_otlichnik.features.login.repository.SessionRepositoryImpl;
import study.example.cft_shift_otlichnik.features.login.view.LoginActivity;
import study.example.cft_shift_otlichnik.features.menu.presentation.MenuPresenter;
import study.example.cft_shift_otlichnik.features.questions.view.QuestionsActivity;

public class MenuActivity extends BaseActivity implements MenuView {

    public static void start(final Context context, String CURRENT_USERNAME) {
        Intent intent = new Intent(context, MenuActivity.class);
        intent.putExtra("CURRENT_USERNAME", CURRENT_USERNAME);
        context.startActivity(intent);
        //не забыть сделать метод обработки юзернейма, если не захочешь делать через sharedpreferences
    }

    private MenuPresenter menuPresenter;
    private Button practiceButton;
    private Button examButton;


    @Override
    protected MvpPresenter<MenuView> getPresenter() {
        menuPresenter = new MenuPresenter();
        return menuPresenter;
    }

    @Override
    protected MenuView getMvpView() {
        return this;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        initView();
    }

    void initView() {
        practiceButton = findViewById(R.id.practiceButton);
        examButton = findViewById(R.id.examButton);

        practiceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                menuPresenter.practiceButtonClick();
            }
        });

        examButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                menuPresenter.examButtonClick();
            }
        });

    }


    @Override
    public void showPracticeScreen() {
        //Toast.makeText(this,"Го в практику", Toast.LENGTH_LONG).show();
        //PracticeActivity.start
        QuestionsActivity.start(this);
    }

    @Override
    public void showExamScreen() {
        Toast.makeText(this,"Го в екзамены", Toast.LENGTH_LONG).show();
        //ExamActivity.start
    }
}
