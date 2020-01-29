package study.example.cft_shift_otlichnik.features.menu.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import okhttp3.Interceptor;
import study.example.cft_shift_otlichnik.MvpPresenter;
import study.example.cft_shift_otlichnik.MvpView;
import study.example.cft_shift_otlichnik.R;
import study.example.cft_shift_otlichnik.features.BaseActivity;
import study.example.cft_shift_otlichnik.features.menu.presentation.MenuPresenter;

public class MenuActivity extends BaseActivity implements MenuView {

    public static void start(final Context context) {
        Intent intent = new Intent(context, MenuActivity.class);
        context.startActivity(intent);
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
        Toast.makeText(this,"Го в практику", Toast.LENGTH_LONG).show();
        //PracticeActivity.start
    }

    @Override
    public void showExamScreen() {
        Toast.makeText(this,"Го в екзамены", Toast.LENGTH_LONG).show();
        //ExamActivity.start
    }
}
