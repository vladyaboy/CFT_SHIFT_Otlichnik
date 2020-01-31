package study.example.cft_shift_otlichnik.features.login.repository;

import java.util.ArrayList;
import java.util.List;

import study.example.cft_shift_otlichnik.features.login.model.User;
import study.example.cft_shift_otlichnik.features.login.model.UserRepository;

public final class UserRepositoryImpl implements UserRepository {
    @Override
    public List<User> getUserList() {
        final List<User> userList = new ArrayList<>();

        userList.add(new User("UserA", "Антон"));
        userList.add(new User("UserB", "Артём"));
        userList.add(new User("UserC", "Женя"));

        return userList;
    }
}
