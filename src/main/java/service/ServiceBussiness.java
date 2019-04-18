package service;

import model.User;

public interface ServiceBussiness {
    void register(User user);

    User login(String username, String password);
}
