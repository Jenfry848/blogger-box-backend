package com.dauphine.blogger.service;

import com.dauphine.blogger.models.Post;

import com.dauphine.blogger.models.Post;
import java.util.UUID;
import java.util.List;
public interface PostService {

    List<Post> getAllByCategoryId(UUID categoryId);

    List<Post> getAll();

    Post getById(UUID id);

    Post create(String title, String content, UUID categoryId);

    Post update(UUID id, String title, String content);

    boolean deleteById(UUID id);

    Post create(String title, String content);

    Post updateContent(UUID id, String newContent);
}
