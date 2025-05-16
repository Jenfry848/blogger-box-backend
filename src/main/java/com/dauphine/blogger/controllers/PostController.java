package com.dauphine.blogger.controllers;

import com.dauphine.blogger.models.Post;
import com.dauphine.blogger.service.PostService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("v1/posts")
public class PostController {

    private final PostService service;

    public PostController(PostService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<Post>> getAll() {
        List<Post> posts = service.getAll();
        return posts.isEmpty()
                ? ResponseEntity.noContent().build()
                : ResponseEntity.ok(posts);
    }

    @GetMapping("{id}")
    public ResponseEntity<Post> getById(@PathVariable UUID id) {
        Post post = service.getById(id);
        return (post == null)
                ? ResponseEntity.notFound().build()
                : ResponseEntity.ok(post);
    }

    @PostMapping
    public ResponseEntity<Post> create(@RequestParam String title,
                       @RequestParam String content,
                       @RequestParam(required = false) UUID categoryId) {
        Post created = (categoryId != null)
                ? service.create(title, content, categoryId)
                : service.create(title, content);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("{id}")
    public ResponseEntity<Post> update(@PathVariable UUID id,
                       @RequestBody Post updatedPost) {
        Post updated = service.update(id, updatedPost.getTitle(), updatedPost.getContent());
        return (updated == null)
                ? ResponseEntity.notFound().build()
                : ResponseEntity.ok(updated);
    }

    @PutMapping("{id}/content")
    public ResponseEntity<Post> updateContent(@PathVariable UUID id,
                              @RequestBody String newContent) {
        Post updated = service.updateContent(id, newContent);
        return (updated == null)
                ? ResponseEntity.notFound().build()
                : ResponseEntity.ok(updated);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteById(@PathVariable UUID id) {
        boolean deleted = service.deleteById(id);
        return deleted
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }

    @GetMapping("/search")
    @Operation(
            summary = "Search posts by title or content",
            description = "Returns posts that contain the keyword in the title or content (case-insensitive)"
    )
    public ResponseEntity<List<Post>> searchPosts(@RequestParam String keyword) {
        List<Post> results = service.searchByKeyword(keyword);
        return results.isEmpty()
                ? ResponseEntity.noContent().build()
                : ResponseEntity.ok(results);
    }

}