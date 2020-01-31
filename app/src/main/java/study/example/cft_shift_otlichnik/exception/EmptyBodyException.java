package study.example.cft_shift_otlichnik.exception;

/**
 * Created: samokryl
 * Date: 01.07.18
 * Time: 23:51
 */

public final class EmptyBodyException extends IllegalStateException {

    public EmptyBodyException() {
        super("Вы не смогли получить доступ от сервера, скорее всего, это не ваш вопрос!");
    }

}