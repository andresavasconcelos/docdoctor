package br.com.docdoctor.service.user;

import br.com.docdoctor.entities.User;

import java.util.List;

public interface IUserService {

    User create(User newUser);
    User update(User user);
    List<User> listAll();
    User findUserById(Long id);
    void remove(Integer id);

}
