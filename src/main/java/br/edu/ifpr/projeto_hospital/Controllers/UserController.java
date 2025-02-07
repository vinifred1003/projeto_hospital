package br.edu.ifpr.projeto_hospital.Controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.ifpr.projeto_hospital.Models.User;
import br.edu.ifpr.projeto_hospital.Repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@RestController
public class UserController {

    private UserRepository userRepository;

    @PostMapping(path = "/addUser") // Map ONLY POST Requests
    public @ResponseBody String addNewUser(@RequestParam String username, @RequestParam String password,
            @RequestParam boolean isDoctor) {
        // @ResponseBody means the returned String is the response, not a view name
        // @RequestParam means it is a parameter from the GET or POST request

        User u = new User();
        u.setUsername(username);
        u.setPassword(password);
        u.setIsDoctor(isDoctor);
        userRepository.save(u);
        return "Saved";
    }

    @GetMapping(path = "/all")
    public @ResponseBody Iterable<User> getAllUsers() {
        // This returns a JSON or XML with the users
        return userRepository.findAll();
    }
}
