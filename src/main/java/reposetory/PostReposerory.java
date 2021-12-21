package reposetory;

import entity.Post;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

import java.util.List;

@Repository

public interface PostReposerory extends JpaRepository<Post, Long>
{

List<Post> findByAuthorId(long id);

    @Transactional

    void deleteAllByAuthorId(long id);

}
