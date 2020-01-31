package study.example.cft_shift_otlichnik.features.questions.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Question implements Serializable {

    private String id;
    private String subject;
    @SerializedName("text")
    private String questionText;
    @SerializedName("correctAnswer")
    private String answer;
    private String author;

    public Question() {
        this.answer = "";
    }

    public Question(String questionText, String answer, String subject) {
        this.questionText = questionText;
        this.answer = answer;
        this.subject = subject;
    }

    public Question(String questionText, String answer, String author, String subject){
        this(questionText, answer, subject);
        this.author = author;
    }


    public Question(String id, String questionText, String answer, String author, String subject) {
        this(questionText, answer, author, subject);
        this.id = id;
    }


    public String getId() { return  id; }

    public String getSubject() { return subject; }

    public String getQuestionText() { return questionText; }

    public String getAnswer() { return answer; }

    public String getAuthor() { return author; }

    public void setAnswer(String answer) { this.answer = answer; }
}
