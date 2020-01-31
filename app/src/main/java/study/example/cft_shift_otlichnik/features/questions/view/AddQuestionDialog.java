package study.example.cft_shift_otlichnik.features.questions.view;

import android.os.Bundle;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import javax.security.auth.Subject;

import study.example.cft_shift_otlichnik.R;
import study.example.cft_shift_otlichnik.features.questions.model.Question;

public final class AddQuestionDialog extends DialogFragment {

    static AddQuestionDialog newInstance() {
        final AddQuestionDialog dialog = new AddQuestionDialog();
        final Bundle args = new Bundle();
        dialog.setArguments(args);
        return dialog;
    }


    private onCreateQuestion onCreateQuestion = null;

    public void setOnCreateQuestion (onCreateQuestion listener) {
        onCreateQuestion  = listener;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = getActivity().getLayoutInflater().inflate(R.layout.dialog_question, null);

        EditText questionSubjectEditText = rootView.findViewById(R.id.questionSubjectEditText);
        EditText questionText = rootView.findViewById(R.id.questionTextEditText);
        EditText answerText = rootView.findViewById(R.id.questionAnswerEditText);
        EditText authorTextEditText = rootView.findViewById(R.id.authorEditText);
        TextView alertTextView = rootView.findViewById(R.id.alertTextView);

        Button applyButton = rootView.findViewById(R.id.acceptEditButton);
        applyButton.setText(R.string.add_question);
        Button editButton = rootView.findViewById(R.id.editButton);

        questionSubjectEditText.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_LONG_MESSAGE);
        questionText.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_LONG_MESSAGE);
        answerText.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_LONG_MESSAGE);

        editButton.setVisibility(View.GONE);
        authorTextEditText.setVisibility(View.INVISIBLE);

        applyButton.setOnClickListener(v -> {
            if(answerText.getText().toString().equals("")
                    || questionText.getText().toString().equals("")
                    || questionSubjectEditText.getText().toString().equals("")) {
                alertTextView.setVisibility(View.VISIBLE);
            } else {
                Question createdQuestion = new Question(questionText.getText().toString(),
                        answerText.getText().toString(),
                        questionSubjectEditText.getText().toString());
                if(onCreateQuestion != null) {
                    onCreateQuestion.createQuestion(createdQuestion);
                    dismiss();
                }
            }
        });

        return rootView;

    }

    interface onCreateQuestion {
        void createQuestion(Question question);
    }
}
