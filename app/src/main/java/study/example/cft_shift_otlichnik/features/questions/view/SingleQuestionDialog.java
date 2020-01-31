package study.example.cft_shift_otlichnik.features.questions.view;

import android.os.Bundle;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import study.example.cft_shift_otlichnik.R;
import study.example.cft_shift_otlichnik.features.questions.model.Question;

public final class SingleQuestionDialog extends DialogFragment {

    private static final String QUESTION_KEY = "QUESTION_KEY";


    static SingleQuestionDialog newInstance(Question question) {
        final SingleQuestionDialog dialog = new SingleQuestionDialog();

        final Bundle args = new Bundle();
        args.putSerializable(QUESTION_KEY, question);

        dialog.setArguments(args);

        return dialog;
    }

    private OnQuestionUpdate onQuestionUpdate = null;

    public void setOnQuestionUpdate(OnQuestionUpdate listener) {
        onQuestionUpdate = listener;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = getActivity().getLayoutInflater().inflate(R.layout.dialog_question, null);

        Question question = (Question)getArguments().getSerializable(QUESTION_KEY);

        EditText questionSubjectEditText = (EditText) rootView.findViewById(R.id.questionSubjectEditText);
        EditText questionText = rootView.findViewById(R.id.questionTextEditText);
        EditText answerText = rootView.findViewById(R.id.questionAnswerEditText);
        EditText authorTextEditText = rootView.findViewById(R.id.authorEditText);


        questionSubjectEditText.setText(question.getSubject());
        questionText.setText(question.getQuestionText());
        answerText.setText(question.getAnswer());
        authorTextEditText.setText(question.getAuthor());



        Button applyButton = rootView.findViewById(R.id.acceptEditButton);
        Button editButton = rootView.findViewById(R.id.editButton);



        applyButton.setVisibility(View.INVISIBLE);

        editButton.setOnClickListener(v -> {
            questionSubjectEditText.setInputType(InputType.TYPE_CLASS_TEXT);
            questionText.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_LONG_MESSAGE);
            answerText.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_LONG_MESSAGE);
            applyButton.setVisibility(View.VISIBLE);

        });


        applyButton.setOnClickListener( v -> {
            if(question.getAnswer().equals(answerText.getText().toString())
                    && question.getSubject().equals(questionSubjectEditText.getText().toString())
                    && question.getQuestionText().equals(questionText.getText().toString())) {
                dismiss();

            }
            Question toUpdate = new Question(question.getId(), questionText.getText().toString(), answerText.getText().toString(), question.getAuthor(), questionSubjectEditText.getText().toString());
            if (onQuestionUpdate != null) {
                onQuestionUpdate.update(toUpdate);
            }
            dismiss();
        });

        return rootView;
    }

    interface OnQuestionUpdate {

        void update(Question question);
    }
}
