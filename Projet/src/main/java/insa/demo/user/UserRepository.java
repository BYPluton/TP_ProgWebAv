package insa.demo.user;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

/**
 * UserRepository
 *
 * @author  Birkan Yildiz & Nicolas Martin
 * @version 1.0
 */
public interface UserRepository extends CrudRepository<User, Long> {
    Optional<User> findByPseudo(String pseudo);
}
