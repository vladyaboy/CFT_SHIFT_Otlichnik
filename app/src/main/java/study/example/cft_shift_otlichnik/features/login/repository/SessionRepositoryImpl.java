package study.example.cft_shift_otlichnik.features.login.repository;

import android.content.Context;
import android.content.SharedPreferences;

import study.example.cft_shift_otlichnik.features.login.model.SessionRepository;

public final class SessionRepositoryImpl implements SessionRepository {
    private final static String SESSION_PREFERENCES_NAME = "SESSION_PREFERENCES_NAME";
    private final static String SESSION_ID_KEY = "SESSION_ID_KEY";

    private SharedPreferences sharedPreferences;

    public SessionRepositoryImpl(Context context) {
        sharedPreferences = context.getSharedPreferences(SESSION_PREFERENCES_NAME, Context.MODE_PRIVATE);
    }

    @Override
    public String getSessionId() {
        return sharedPreferences.getString(SESSION_ID_KEY, "");
    }

    @Override
    public void setSessionId(String sessionId) {
        sharedPreferences.edit().putString(SESSION_ID_KEY, sessionId).apply();
    }
}
