package study.example.cft_shift_otlichnik.features.questions.presentation;

import android.content.Context;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import study.example.cft_shift_otlichnik.R;
import study.example.cft_shift_otlichnik.features.questions.model.Question;

public final class SpinnerAdapterFactory {

    List<Question> allQuestions;

    public SpinnerAdapterFactory(List<Question> allQuestions) {
        this.allQuestions = allQuestions;
    }


    public ArrayAdapter createAdapter (Context context, int id) {
        List<String> itemsList = new ArrayList<>();
        itemsList.add(context.getResources().getString(R.string.show_all));
        for(Question question : allQuestions) {
            String subjectName = question.getSubject();
            if(!itemsList.contains(subjectName)) {
                itemsList.add(subjectName);
            }
        }
        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(context, id, itemsList);
        return spinnerAdapter;
    }
}
