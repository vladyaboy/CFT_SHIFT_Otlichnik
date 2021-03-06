package study.example.cft_shift_otlichnik.features.questions.presentation;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import okhttp3.Interceptor;
import study.example.cft_shift_otlichnik.R;
import study.example.cft_shift_otlichnik.features.questions.model.Question;

public final class QuestionAdapter extends RecyclerView.Adapter<QuestionAdapter.QuestionHolder> {

    private List<Question> allQuestions = new ArrayList<>();
    private final LayoutInflater inflater;
    private final List<String> subjectsList = new ArrayList<>();
    private final SelectQuestionListener selectQuestionListener;
    private List<Question> dynamicSubjectQuestions = new ArrayList<>();



    public QuestionAdapter(Context context, SelectQuestionListener selectQuestionListener) {
        inflater = LayoutInflater.from(context);
        this.selectQuestionListener = selectQuestionListener;
    }

    @NonNull
    @Override
    public QuestionHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        final View itemView = inflater.inflate(R.layout.question_item, parent, false);
        return new QuestionHolder(itemView, selectQuestionListener);
    }

    @Override
    public void onBindViewHolder(@NonNull QuestionHolder holder, int position) {
        holder.bind(dynamicSubjectQuestions.get(position));
    }

    @Override
    public int getItemCount() {
        return dynamicSubjectQuestions.size();
    }

    //Заполняем лист отфильтрованными вопросами
    public void setDynamicSubjectQuestions(List<Question> questionList) {
        //allQuestions.clear();
        //allQuestions.addAll(questionList);
        dynamicSubjectQuestions.clear();
        dynamicSubjectQuestions.addAll(questionList);
        notifyDataSetChanged();
    }


    //Сбрасываем фильтр на нейтральный
    public void showAllQuestions() {
        dynamicSubjectQuestions.clear();
        dynamicSubjectQuestions.addAll(allQuestions);
        notifyDataSetChanged();
    }

    //Заполняем список уникальных предметов
    /*
    Перекочевал в SpinnerAdapter
    public void initSubjectsList() {
            subjectsList.clear();
            for(Question question : allQuestions) { subjectsList.add(question.getSubject()); }
        subjectsList.stream().distinct().collect(Collectors.toList());
    }
     */


    //Заполняем лист со всеми вопросами и дальше его не меняем, только достаем данные
    public void initAllQuestions(List<Question> questionList) {
        allQuestions.clear();
        allQuestions.addAll(questionList);
        dynamicSubjectQuestions.clear();
        dynamicSubjectQuestions.addAll(allQuestions);
        notifyDataSetChanged();
    }

    //Фильтруем вопросы по запросу пользователя, нажавшего на значение в спиннере
    public void filterQuestions(String subjectName) {

        ArrayList<Question> temporaryList = new ArrayList<>();

        if(subjectName.equals(R.string.show_all)){
            showAllQuestions();
        } else {
            for (Question question : allQuestions) {
                if (question.getSubject().equals(subjectName)) { temporaryList.add(question); }
                if (temporaryList.size() > 0) {
                    setDynamicSubjectQuestions(temporaryList);
                } else {
                    setDynamicSubjectQuestions(allQuestions);
                }
            }
        }

    }


    class QuestionHolder extends RecyclerView.ViewHolder {
        private final TextView questionTextView;
        private final TextView questionSubjectView;
        private final SelectQuestionListener selectQuestionListener;

        QuestionHolder(View view, SelectQuestionListener selectQuestionListener) {
            super(view);
            this.selectQuestionListener = selectQuestionListener;
            questionTextView = view.findViewById(R.id.question_item_text);
            questionSubjectView = view.findViewById(R.id.question_item_subject);
        }

        void bind(final Question question) {
            questionTextView.setText(question.getQuestionText());
            questionSubjectView.setText(question.getSubject());

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    selectQuestionListener.onQuestionSelect(question);
                }
            });

            //Не факт что пригодится
            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    selectQuestionListener.onQuestionLongClick(question);
                    return true;
                }
            });
        }
    }

    public interface  SelectQuestionListener {

        void onQuestionSelect(Question question);

        void onQuestionLongClick(Question question);

    }
}
