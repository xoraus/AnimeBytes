package io.xoraus.CyberScribeHub.repositories;

import io.xoraus.CyberScribeHub.entities.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepo  extends JpaRepository<Comment, Integer> {

}
