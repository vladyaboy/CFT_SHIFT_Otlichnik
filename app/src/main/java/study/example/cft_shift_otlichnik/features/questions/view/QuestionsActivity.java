package study.example.cft_shift_otlichnik.features.questions.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import java.util.List;

import study.example.cft_shift_otlichnik.MvpPresenter;
import study.example.cft_shift_otlichnik.MvpView;
import study.example.cft_shift_otlichnik.R;
import study.example.cft_shift_otlichnik.features.BaseActivity;
import study.example.cft_shift_otlichnik.features.questions.model.Question;
import study.example.cft_shift_otlichnik.features.questions.presentation.PresenterFactory;
import study.example.cft_shift_otlichnik.features.questions.presentation.QuestionAdapter;
import study.example.cft_shift_otlichnik.features.questions.presentation.QuestionListPresenter;

public class QuestionsActivity extends BaseActivity implements QuestionListView {

    public static void start(final Context context) {
        Intent intent = new Intent(context, QuestionsActivity.class);
        context.startActivity(intent);
    }

    private RecyclerView recyclerView;
    private Button createQuestionButton;
    private Button filterButton;
    private QuestionAdapter adapter;
    private ProgressBar progressBar;

    private QuestionListPresenter presenter;

    @Override
    protected MvpPresenter<QuestionListView> getPresenter() {
        presenter = PresenterFactory.createPresenter(this);
        return presenter;
    }

    @Override
    protected MvpView getMvpView() {
        return this;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questions);
        initView();
    }

    private void initView() {
        progressBar = findViewById(R.id.progressBar);
        recyclerView = findViewById(R.id.questionsRecyclerView);
        createQuestionButton = findViewById(R.id.create_button);
        filterButton = findViewById(R.id.filter_button);

        createQuestionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.onCreateQuestion();
            }
        });

        adapter = new QuestionAdapter(this, new QuestionAdapter.SelectQuestionListener() {
            @Override
            public void onQuestionSelect(Question question) {
                presenter.onQuestionSelected(question);
            }

            @Override
            public void onQuestionLongClick(Question question) {
                //реализовать попытку удаления
            }
        });

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public void showQuestionList(List<Question> list) {
        adapter.setQuestions(list);
    }

    @Override
    public void updateQuestionList(List<Question> list) {
        adapter.updateQuestions(list);
    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
        recyclerView.setVisibility(View.GONE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.GONE);
        recyclerView.setVisibility(View.VISIBLE);
    }

    @Override
    public void showError(String message) {

    }
}
