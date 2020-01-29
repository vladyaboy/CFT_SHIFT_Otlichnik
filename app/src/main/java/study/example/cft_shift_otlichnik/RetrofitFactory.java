package study.example.cft_shift_otlichnik;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Interceptor;
import okhttp3.logging.HttpLoggingInterceptor;
import study.example.cft_shift_otlichnik.features.login.model.SessionRepository;
import study.example.cft_shift_otlichnik.features.login.repository.SessionRepositoryImpl;
import study.example.cft_shift_otlichnik.network.RetrofitProvider;
import study.example.cft_shift_otlichnik.network.SessionInterceptor;

final class RetrofitFactory {

    static RetrofitProvider createRetrofitProvider(Context context) {
        final HttpLoggingInterceptor logInterceptor = new HttpLoggingInterceptor();
        logInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        final SessionRepository sessionRepository = new SessionRepositoryImpl(context);
        final SessionInterceptor sessionInterceptor = new SessionInterceptor(sessionRepository);

        final List<Interceptor> interceptorList = new ArrayList<>();
        interceptorList.add(logInterceptor);
        interceptorList.add(sessionInterceptor);

        return new RetrofitProvider(interceptorList);
    }
}
