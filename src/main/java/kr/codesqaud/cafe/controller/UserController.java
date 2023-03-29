package kr.codesqaud.cafe.controller;

import kr.codesqaud.cafe.domain.User;
import kr.codesqaud.cafe.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users/new")
    public String createForm() {
        return "users/createUserForm";
    }

    @PostMapping("/users/new")
    public String create(UserForm form) {
        User user = new User(form.getId(), form.getPassword(), form.getName(), form.getEmail());

        userService.join(user);

        return "redirect:/";
    }

    @GetMapping("/users")
    public String list(Model model) {
        List<User> users = userService.findMembers();
        model.addAttribute("users", users);
        return "users/userList";
    }

}