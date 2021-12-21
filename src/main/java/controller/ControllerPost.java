package controller;

import entity.Post;
import entity.User;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import service.PostService;
import service.UserService;

public class ControllerPost
{
    private PostService postService;

    private UserService userService;


    @GetMapping("/posts")
    public String allPosts(@ModelAttribute("post") Post post, Model model, @CookieValue(value = "currentUser", required = false) String currentUser)
    {

        if (currentUser != null)
        {

            model.addAttribute("user", userService.findUserById(Long.parseLong(currentUser)));

            return "posts";
        }
        model.addAttribute("message", "Sign In to view the Posts.");

        return "signin";
    }



    @PostMapping("/add_post")

    public String savePost(@ModelAttribute Post post, Model model, @CookieValue("currentUser") String currUser)
    {

        User curUser = userService.findUserById(Long.parseLong(currUser));

        model.addAttribute("currentUser", currUser);

        post.setAuthor(curUser);

        postService.savePost(post);

        return "redirect:/posts";

    }


   @GetMapping("/delete_post/{id}")

    public String deletePost(@PathVariable long id)
   {
        postService.deletePost(id);

        return "hello";
   }

    @GetMapping("/delete.by.author/{id}")

    public String deleteByAuthor(@PathVariable long id)
    {
        postService.deletePostsByAuthorId(id);

        return "hello";
    }



}
