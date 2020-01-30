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

    private List<String> itemsList = new ArrayList<>();;

    public ArrayAdapter createAdapter (Context context, int id) {
        itemsList.clear();
        itemsList.add(context.getResources().getString(R.string.show_all));
        for(Question question : allQuestions) { itemsList.add(question.getSubject()); }
        itemsList.stream().distinct().collect(Collectors.toList());
        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(context, id, itemsList);
        return new ArrayAdapter<String>(context, id, itemsList);
    }
}
