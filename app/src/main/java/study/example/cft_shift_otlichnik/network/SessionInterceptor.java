package study.example.cft_shift_otlichnik.network;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import study.example.cft_shift_otlichnik.features.login.model.SessionRepository;

public final class SessionInterceptor implements Interceptor {

    private static final String HEADER_USER_ID = "userId";

    private final SessionRepository sessionRepository;

    public SessionInterceptor(SessionRepository sessionRepository) {
        this.sessionRepository = sessionRepository;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request.Builder builder = chain.request().newBuilder();
        builder.header(HEADER_USER_ID, sessionRepository.getSessionId());
        return chain.proceed( builder.build());
    }
}
