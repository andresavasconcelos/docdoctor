package br.com.docdoctor.service.user;

import br.com.docdoctor.dto.UserRequestDTO;
import br.com.docdoctor.entities.User;
import br.com.docdoctor.mapper.UserMapper;
import br.com.docdoctor.repository.UserRepository;
import org.hibernate.service.spi.ServiceException;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.util.List;

@Service
public class UserServiceImpl implements IUserService {

    private final UserRepository repository;
    private final UserMapper userMapper;

    public UserServiceImpl(UserRepository repository, UserMapper userMapper) {
        this.repository = repository;
        this.userMapper = userMapper;
    }

    @Override
    public Mono<User> create(UserRequestDTO newUser) {
        return Mono.fromCallable(() -> {
                    User user = userMapper.toEntity(newUser);
                    return repository.save(user);
                })
                .subscribeOn(Schedulers.boundedElastic())
                .onErrorResume(e -> Mono.error(new ServiceException("Erro ao salvar usuário", e)));
    }

    @Override
    public User update(User user) {
        //Todo: implementar
        return null;
    }

    @Override
    public Mono<List<User>> listAll() {
        return Mono.fromCallable(() -> repository.findAll())
                .subscribeOn(Schedulers.boundedElastic())
                .onErrorResume(e -> Mono.error(new ServiceException("Erro ao listar usuários", e)));
    }

    @Override
    public Mono<User> findUserById(Long id) {
        return Mono.fromCallable(() -> repository.findById(id).orElseThrow(() ->
                        new RuntimeException("Usuário não encontrado com ID: " + id)))
                .subscribeOn(Schedulers.boundedElastic());
    }

//    Todo: Colocar userfundByname para ser mais facil de encontrar

    @Override
    public void remove(Long id) {
        //Todo: implementar
    }
}
