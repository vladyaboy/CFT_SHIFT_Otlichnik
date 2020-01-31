package study.example.cft_shift_otlichnik.features.questions.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

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
        questionSubjectEditText.setText(question.getSubject());



        Button applyButton = (Button) rootView.findViewById(R.id.acceptEditButton);
        applyButton.setOnClickListener( v -> {
            Question toUpdate = new Question(question.getId(), question.getQuestionText(), question.getAuthor(), question.getAnswer(), questionSubjectEditText.getText().toString());
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
