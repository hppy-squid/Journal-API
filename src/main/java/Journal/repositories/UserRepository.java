package Journal.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import Journal.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);

    boolean existsByUsername(String username);
    
}
