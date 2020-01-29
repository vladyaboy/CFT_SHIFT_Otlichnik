package study.example.cft_shift_otlichnik.features.login.model;

import androidx.annotation.Nullable;

public final class User {
    private String sessionId;
    private String name;

    public User(String sessionId, String name) {
        this.sessionId = sessionId;
        this.name = name;
    }

    public String getSessionId() {
        return sessionId;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        return super.equals(obj);
    }
}
