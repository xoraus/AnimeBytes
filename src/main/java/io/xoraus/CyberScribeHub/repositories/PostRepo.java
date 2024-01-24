package io.xoraus.CyberScribeHub.repositories;

import io.xoraus.CyberScribeHub.entities.Category;
import io.xoraus.CyberScribeHub.entities.Post;
import io.xoraus.CyberScribeHub.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepo extends JpaRepository<Post, Integer> {
    List<Post> findByUser(User user);
    List<Post> findByCategory(Category category);
    List<Post> findByTitleContaining(@Param("key") String title);
    Post save(Post post);
}
