package Journal.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import Journal.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    
}
