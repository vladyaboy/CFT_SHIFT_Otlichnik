package study.example.cft_shift_otlichnik.network;

public interface Carry<T> {

    void onSuccess(T result);

    void onFailure(Throwable throwable);
}
