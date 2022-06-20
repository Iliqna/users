package com.users.controller;

import com.users.model.UserModel;
import com.users.service.UserModelService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import static org.springframework.http.ResponseEntity.ok;

@Controller
public class UserController {

    private final UserModelService userModelService;

    public UserController(final UserModelService userModelService) {
        this.userModelService = userModelService;
    }

    @GetMapping(value = "/users/list")
    public ResponseEntity<Iterable<UserModel>> list() {
        final Iterable<UserModel> res = userModelService.findAll();
        return ok(res);
    }

    @GetMapping(value = "/users/list/{name}")
    public ResponseEntity<Iterable<UserModel>> list(@PathVariable String name) {
        final Iterable<UserModel> res = userModelService.findByFullName(name);
        return ok(res);
    }

    @GetMapping(value = "/users/create")
    public String create(Model model) {
        model.addAttribute("user", new UserModel());
        return "submit";
    }

    @GetMapping(value = "/users/update/{id}")
    public String update(@PathVariable Long id, Model model) {
        final UserModel userModel = userModelService.findById(id);
        model.addAttribute("user", userModel);
        return "submit";
    }

    @GetMapping(value = "/users/delete/{id}")
    public String delete(@PathVariable Long id) {
        userModelService.delete(id);
        return "success";
    }

    @PostMapping(value = "/users/submit")
    public String submit(@ModelAttribute UserModel userModel) {
        if (userModel.getId() == null || userModel.getId().isEmpty()) {
            userModelService.save(userModel);
        } else {
            userModelService.update(userModel);
        }
        return "success";
    }
}
