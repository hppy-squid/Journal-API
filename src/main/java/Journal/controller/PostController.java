package Journal.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import Journal.DTO.PostDto;
import Journal.service.PostService;
import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/posts")
public class PostController {

    private final PostService postService;

    @PostMapping("newPost")
    public ResponseEntity<PostDto>newPost(@RequestBody PostDto postDto, Authentication authentication) {
        PostDto newPost = postService.newPost(postDto, authentication);
        return ResponseEntity.ok(newPost);
    }

     @GetMapping("/{id}")
     public ResponseEntity<PostDto> getPost(@PathVariable Long id) {
        PostDto postDto = postService.getPostById(id);
        return ResponseEntity.ok(postDto);
         
     }

     @GetMapping("/all")
     public ResponseEntity<List<PostDto>> getAllPosts(Authentication authentication) {
        List<PostDto> posts = postService.getAllPosts(authentication);
        return ResponseEntity.ok(posts);
     }
 
}
