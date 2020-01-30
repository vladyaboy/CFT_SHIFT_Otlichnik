package study.example.cft_shift_otlichnik.features.questions.model;

import com.google.gson.annotations.SerializedName;

public class Question {

    private String id;
    private String subject;
    @SerializedName("text")
    private String questionText;
    private String answer;
    private String author;

    public Question() {
        this.answer = "";
    }

    public Question(String subject, String questionText, String author) {
        this();
        this.subject = subject;
        this.questionText = questionText;
        this.author = author;
    }

    public Question(String subject, String questionText, String answer, String author) {
        this(subject, questionText, author);
        this.answer = answer;
    }

    public String getId() { return  id; }

    public String getSubject() { return subject; }

    public String getQuestionText() { return questionText; }

    public String getAnswer() { return answer; }

    public String getAuthor() { return author; }

    public void setAnswer(String answer) { this.answer = answer; }
}
