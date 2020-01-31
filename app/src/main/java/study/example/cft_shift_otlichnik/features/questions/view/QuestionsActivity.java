package study.example.cft_shift_otlichnik.features.questions.view;

import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.Toast;

import java.security.AllPermission;
import java.util.ArrayList;
import java.util.List;

import study.example.cft_shift_otlichnik.MvpPresenter;
import study.example.cft_shift_otlichnik.MvpView;
import study.example.cft_shift_otlichnik.R;
import study.example.cft_shift_otlichnik.features.BaseActivity;
import study.example.cft_shift_otlichnik.features.questions.model.Question;
import study.example.cft_shift_otlichnik.features.questions.presentation.PresenterFactory;
import study.example.cft_shift_otlichnik.features.questions.presentation.QuestionAdapter;
import study.example.cft_shift_otlichnik.features.questions.presentation.QuestionListPresenter;
import study.example.cft_shift_otlichnik.features.questions.presentation.SpinnerAdapterFactory;

public class QuestionsActivity extends BaseActivity implements QuestionListView {

    private static final String QUESTION_DIALOG_TAG = "question_dialog";

    public static void start(final Context context) {
        Intent intent = new Intent(context, QuestionsActivity.class);
        context.startActivity(intent);
    }

    private RecyclerView recyclerView;
    private Button createQuestionButton;
    private QuestionAdapter adapter;
    private ProgressBar progressBar;
    private Spinner filterSpinner;
    private ArrayAdapter spinnerAdapter;
    private SpinnerAdapterFactory spinnerAdapterFactory;

    private QuestionListPresenter presenter;

    private AlertDialog questionDialog;

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
        filterSpinner = findViewById(R.id.filterSpinner);


        AdapterView.OnItemSelectedListener itemSelectedListener = new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                // Получаем выбранный объект
                String subjectName = (String)parent.getItemAtPosition(position);
                //тут замутить фильтр вопросов из списка по предмету
                //UPDATE: вроде замутил. Надо чекать теперь на сервере
                filterQuestionList(subjectName);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        };

        filterSpinner.setOnItemSelectedListener(itemSelectedListener);


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
                //реализовать открытие вопроса и мб редактирование
                openDialogWithQuestion(question);
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
    public void initQuestionList(List<Question> list) {
        adapter.initAllQuestions(list);
        adapter.showAllQuestions();
        //adapter.setDynamicSubjectQuestions(list);
    }

    @Override
    public void initSubjectList(List<Question> list) {
        SpinnerAdapterFactory spinnerAdapterFactory = new SpinnerAdapterFactory(list);
        spinnerAdapter = spinnerAdapterFactory.createAdapter(getApplicationContext(), android.R.layout.simple_spinner_item);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        filterSpinner.setAdapter(spinnerAdapter);
    }

    @Override
    public void openDialogWithQuestion(Question question) {
        SingleQuestionDialog singleQuestionDialog = SingleQuestionDialog.newInstance(question);

        singleQuestionDialog.setOnQuestionUpdate(updatedQuestion -> {
            presenter.updateQuestion(updatedQuestion);
        });

        singleQuestionDialog.show(getSupportFragmentManager(), QUESTION_DIALOG_TAG);
    }

    @Override
    public void updateDynamicQuestionList(List<Question> list) {

    }




    @Override
    public void filterQuestionList(String subjectName) {
        adapter.filterQuestions(subjectName);
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
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }
}
