package study.example.cft_shift_otlichnik.features.login.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.List;

import study.example.cft_shift_otlichnik.MvpPresenter;
import study.example.cft_shift_otlichnik.MvpView;
import study.example.cft_shift_otlichnik.R;
import study.example.cft_shift_otlichnik.features.BaseActivity;
import study.example.cft_shift_otlichnik.features.login.model.User;
import study.example.cft_shift_otlichnik.features.login.presentation.LoginPresenter;
import study.example.cft_shift_otlichnik.features.login.presentation.PresenterFactory;

public class LoginActivity extends BaseActivity implements LoginView {

   private LoginPresenter presenter;
   private UserAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
    }

   private void initView() {
        final RecyclerView recyclerView = findViewById(R.id.usersRecycleView);
        final Button nextButton = findViewById(R.id.nextButton);

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.onUserClick(adapter.getSelectedUser());
            }
        });

        adapter = new UserAdapter(this);

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
   }

    @Override
    protected MvpPresenter<LoginView> getPresenter() {
        presenter = PresenterFactory.createPresenter(this);
        return presenter;
    }

    @Override
    protected MvpView getMvpView() {
        return this;
    }

    @Override
    public void showUserList(List<User> userList) {
        adapter.setUserList(userList);
    }

    @Override
    public void openQuestionListScreen(String userName) {
        Toast.makeText(this, "Ты тыкнул " + userName, Toast.LENGTH_LONG).show();
    }

    @Override
    public void showUserNotSelectedError() {
        Toast.makeText(this, "Ну ты шо, выбери пользователя то", Toast.LENGTH_SHORT).show();
    }
}
