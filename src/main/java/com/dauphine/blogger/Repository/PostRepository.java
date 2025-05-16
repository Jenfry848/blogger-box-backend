package com.dauphine.blogger.Repository;

import com.dauphine.blogger.models.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;
import java.time.LocalDate;
import java.util.List;


public interface PostRepository extends JpaRepository<Post, UUID>{
    List<Post> findByCreatedDate(LocalDate date);
    List<Post> findByCategoryId(UUID categoryId);
    List<Post> findByTitleContainingIgnoreCaseOrContentContainingIgnoreCase(String title, String content);

}




