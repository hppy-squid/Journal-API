package Journal.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import Journal.model.Post;

public interface PostRepository extends JpaRepository<Post, Long> {
    
}
