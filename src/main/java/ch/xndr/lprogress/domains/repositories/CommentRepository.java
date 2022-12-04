package ch.xndr.lprogress.domains.repositories;

import ch.xndr.lprogress.domains.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
