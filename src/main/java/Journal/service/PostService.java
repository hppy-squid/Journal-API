package Journal.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import Journal.DTO.PostDto;
import Journal.enums.MoodStatus;
import Journal.model.Post;
import Journal.repositories.PostRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    public PostDto newPost(PostDto postDto) {
        // 1. Konvertera DTO → Entity
        Post post = new Post();
        post.setContent(postDto.getContent());

        // Konvertera String → Enum (förutsatt att status i DTO är t.ex. "HAPPY", "SAD", "NEUTRAL")
        if (postDto.getStatus() != null) {
            post.setStatus(MoodStatus.valueOf(postDto.getStatus().toUpperCase()));
        }

        // Sätt datum, om inget skickas in → dagens datum
        post.setDate(postDto.getDate() != null ? postDto.getDate() : LocalDate.now());

        // 2. Spara i DB
        Post savedPost = postRepository.save(post);

        // 3. Konvertera Entity → DTO
        PostDto responseDto = new PostDto();
        responseDto.setId(savedPost.getId());
        responseDto.setContent(savedPost.getContent());
        responseDto.setStatus(savedPost.getStatus().name()); // enum → String
        responseDto.setDate(savedPost.getDate());

        return responseDto;
    }

            

    public PostDto getPostById(Long id) {

       Post post = postRepository.findById(id).orElseThrow(() -> new RuntimeException("Post not found"));

       
    PostDto dto = new PostDto();
    dto.setId(post.getId());
    dto.setContent(post.getContent());
    dto.setStatus(post.getStatus().name()); // enum → String
    dto.setDate(post.getDate());

    return dto;

       
    }



    public List<PostDto> getAllPosts() {
        List<Post> posts = postRepository.findAll();

      return posts.stream()
            .map(post -> {
                PostDto dto = new PostDto();
                dto.setId(post.getId());
                dto.setContent(post.getContent());
                dto.setStatus(post.getStatus().name()); // enum → String
                dto.setDate(post.getDate());
                return dto;
            })
            .toList();


    }


    
}
