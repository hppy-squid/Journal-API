package Journal.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import Journal.DTO.PostDto;
import Journal.enums.MoodStatus;
import Journal.model.Post;
import Journal.model.User;
import Journal.repositories.PostRepository;
import Journal.repositories.UserRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;

    public PostDto newPost(PostDto postDto, Authentication authentication) {
        
        Post post = new Post();
        post.setContent(postDto.getContent());

        if (postDto.getStatus() != null) {
            post.setStatus(MoodStatus.valueOf(postDto.getStatus().toUpperCase()));
        }

        post.setDate(postDto.getDate() != null ? postDto.getDate() : LocalDateTime.now());

         String username = authentication.getName();
    User user = userRepository.findByUsername(username)
        .orElseThrow(() -> new RuntimeException("User not found"));

    post.setUser(user);

        Post savedPost = postRepository.save(post);

        PostDto responseDto = new PostDto();
        responseDto.setId(savedPost.getId());
        responseDto.setContent(savedPost.getContent());
        responseDto.setStatus(savedPost.getStatus().name());
        responseDto.setDate(savedPost.getDate());

        return responseDto;
    }

            

    public PostDto getPostById(Long id) {

       Post post = postRepository.findById(id).orElseThrow(() -> new RuntimeException("Post not found"));

       
    PostDto dto = new PostDto();
    dto.setId(post.getId());
    dto.setContent(post.getContent());
    dto.setStatus(post.getStatus().name()); 
    dto.setDate(post.getDate());

    return dto;

       
    }



    public List<PostDto> getAllPosts(Authentication authentication) {
        String username = authentication.getName(); 
    User user = userRepository.findByUsername(username)
        .orElseThrow(() -> new RuntimeException("User not found"));
    

        List<Post> posts = postRepository.findByUserId(user.getId());

      return posts.stream()
            .map(post -> {
                PostDto dto = new PostDto();
                dto.setId(post.getId());
                dto.setContent(post.getContent());
                dto.setStatus(post.getStatus().name());
                dto.setDate(post.getDate());
                return dto;
            })
            .toList();


    }


    
}
