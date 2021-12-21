package controller;

import entity.Post;
import entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import service.PostService;
import service.UserService;

import javax.servlet.http.HttpServletResponse;

@Controller
public class ControllerUser {
    @Autowired
    private PostService postService;

    @Autowired
    private UserService userService;


    @GetMapping("Start")

    public String Hi(@ModelAttribute("user") User user, Model model, @CookieValue(value = "currentUser", required = false) String currentUser) {

        if (currentUser != null && currentUser != "") {

            model.addAttribute("user", userService.findUserById(Long.parseLong(currentUser)));

            return "index";

        }

        return "index";
    }


    //*Ä************************************************************************************************************
    @GetMapping("/login")

    public String login() {
        return "login";
    }

    @PostMapping("/auth_user")

    public String authenticate(@RequestParam("username") String username, @RequestParam("pass") String pass, HttpServletResponse response) {

        return "redirect:/profile";

    }

    @GetMapping("/authError")

    public String authError(Model model) {

        model.addAttribute("message", " username and or password you entered is not right. If you dont have a account sign up and try again.");
        return "signin";
    }

    //****************************************************************************************************************
    @PostMapping("/signup")

    public String register(@ModelAttribute("user") User user) {
        return "signup";
    }


    @PostMapping("save_user")

    public String saveUser(User user, @RequestParam("pass") String pass, @RequestParam("pass_con") String pass_con) {
        if (pass.equals(pass_con)) {
            user.setImg("https://via.placeholder.com/150");

            userService.saveUser(user);

            return "redirect:/Success";
        }
        return "redirect:/fail";
    }

    @GetMapping("/Success")

    public String success(@ModelAttribute("user") User user) {
        return "redirect:/login";
    }

    @GetMapping("/failed")

    public String failed(@ModelAttribute("user") User user,

                         Model model) {

        model.addAttribute("Warning", "Sign up didnt work.Try again and make sure everything is right.");

        return "signup";
    }

    @GetMapping("/signout")
    public String signOut(HttpServletResponse response) {

        return "redirect:/";

    }

    @GetMapping("/prof/{id}")
    public String showProfile(@ModelAttribute("post") Post post, Model model, @CookieValue("currentUser") String currentUser, @PathVariable long id) {
        userService.findUserById(id);
        model.addAttribute("user", userService.findUserById(Long.parseLong(currentUser)));
        return "prof";
    }


    @PostMapping("/update_user")

    public String updateUser(@ModelAttribute User user) {
        userService.updateUser(user);

        Long id = user.getId();

        return "redirect:/prof/" + id;

    }


    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable long id) {
        postService.deletePostsByAuthorId(id);

        userService.deleteUser(id);

        return "redirect:/signout";

    }


    @GetMapping("/edit/{id}")
    public String editUser(Model model,
                           @PathVariable long id,
                           @CookieValue("currUser") String currentUser) {
        userService.findUserById(id);
        User curUser = userService.findUserById(Long.parseLong(currentUser));
        model.addAttribute("currUser", currentUser);
        model.addAttribute(curUser);
        return "edit";


    }
}

