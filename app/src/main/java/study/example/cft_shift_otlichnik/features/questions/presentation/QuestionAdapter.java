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

import study.example.cft_shift_otlichnik.R;
import study.example.cft_shift_otlichnik.features.questions.model.Question;

public class QuestionAdapter extends RecyclerView.Adapter<QuestionAdapter.QuestionHolder> {

    private final List<Question> questions = new ArrayList<>();
    private final LayoutInflater inflater;
    private final SelectQuestionListener selectQuestionListener;

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
        holder.bind(questions.get(position));
    }

    @Override
    public int getItemCount() {
        return questions.size();
    }

    public void setQuestions(List<Question> questionList) {
        questions.clear();
        questions.addAll(questionList);
        notifyDataSetChanged();
    }

    public void updateQuestions(List<Question> questionsList) {
        questions.addAll(questionsList);
        notifyDataSetChanged();
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
