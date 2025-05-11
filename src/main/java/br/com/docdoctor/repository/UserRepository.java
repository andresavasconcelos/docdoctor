package br.com.docdoctor.repository;

import br.com.docdoctor.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
