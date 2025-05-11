package br.com.docdoctor.service.user;

import br.com.docdoctor.dto.UserRequestDTO;
import br.com.docdoctor.entities.User;
import reactor.core.publisher.Mono;

import java.util.List;

public interface IUserService {

    Mono<User> create(UserRequestDTO newUser);
    User update(User user);
    Mono<List<User>> listAll();
    Mono<User> findUserById(Long id);
    void remove(Long id);
}
