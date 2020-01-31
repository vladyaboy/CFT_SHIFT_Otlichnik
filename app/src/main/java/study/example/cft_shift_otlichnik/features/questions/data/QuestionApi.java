package study.example.cft_shift_otlichnik.features.questions.data;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.Path;
import study.example.cft_shift_otlichnik.features.questions.model.Question;
import study.example.cft_shift_otlichnik.features.questions.model.QuestionsResponse;

public interface QuestionApi {

    @GET("questions?page=0")
    Call<QuestionsResponse> getQuestionList();

    @GET("questions/{id}")
    Call<Question> getQuestion(@Path("id") String id);

    @POST("questions")
    Call<Question> createQuestion(@Body Question question);

    @PATCH("questions/{id}")
    Call<Question> updateQuestion(@Path("id") String id, @Body Question question);

    @DELETE("questions/{id}")
    Call<Question> deleteQuestion(@Path("id") String id);
}
