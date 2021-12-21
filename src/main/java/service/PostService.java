package service;


import entity.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reposetory.PostReposerory;

import java.util.List;

@Service
public class PostService
{
    @Autowired
    private PostReposerory postReposerory;




    public List<Post> findAllPosts(){return postReposerory.findAll();}

    public List<Post> findPostByAuthorId(long id) {return postReposerory.findByAuthorId(id);}

    public void savePost(Post post) {postReposerory.save(post);}


  @Autowired
  public void deletePost(long id) {postReposerory.deleteById(id);
  }

    public void deletePostsByAuthorId(long id) {postReposerory.deleteAllByAuthorId(id); }


}
