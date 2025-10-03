package Journal.repositories;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import Journal.model.Post;

public interface PostRepository extends JpaRepository<Post, Long> {

    List<Post> findByDateBetween(LocalDateTime startDateTime, LocalDateTime endDateTime);

    List<Post> findByUserId(Long userId);
    
}
