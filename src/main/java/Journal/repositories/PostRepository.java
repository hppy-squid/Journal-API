package Journal.repositories;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import Journal.model.Post;

public interface PostRepository extends JpaRepository<Post, Long> {

    List<Post> findByDateBetween(LocalDate startDate, LocalDate endDate);
    
}
