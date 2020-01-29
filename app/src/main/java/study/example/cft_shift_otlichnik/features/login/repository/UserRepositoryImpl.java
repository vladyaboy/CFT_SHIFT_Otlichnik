package study.example.cft_shift_otlichnik.features.login.repository;

import java.util.ArrayList;
import java.util.List;

import study.example.cft_shift_otlichnik.features.login.model.User;
import study.example.cft_shift_otlichnik.features.login.model.UserRepository;

public final class UserRepositoryImpl implements UserRepository {
    @Override
    public List<User> getUserList() {
        final List<User> userList = new ArrayList<>();

        userList.add(new User("UserA", "Генадий Пупидон"));
        userList.add(new User("UserB", "Артемий Лебудёв"));
        userList.add(new User("UserC", "Евгений Лукохлебов"));
        userList.add(new User("UserD", "Пирог Кренделёв"));
        userList.add(new User("UserE", "Евлпамий Нуиимяконечнов"));

        return userList;
    }
}
