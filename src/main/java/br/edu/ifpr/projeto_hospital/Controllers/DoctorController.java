package br.edu.ifpr.projeto_hospital.Controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.ifpr.projeto_hospital.Repository.DoctorRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@RestController
public class DoctorController {

  private DoctorRepository doctorRepository;

  // @GetMapping("/")
  // public String index() {
  // return "Welcome to the home page!";
  // }

  // @PostMapping(path="/add") // Map ONLY POST Requests
  // public @ResponseBody String addNewDoctor (@RequestParam String name
  // , @RequestParam String email) {
  // // @ResponseBody means the returned String is the response, not a view name
  // // @RequestParam means it is a parameter from the GET or POST request

  // Doctor n = new Doctor();
  // n.setName(name);
  // n.setEmail(email);
  // userRepository.save(n);
  // return "Saved";
  // }

  // @GetMapping(path="/all")
  // public @ResponseBody Iterable<User> getAllUsers() {
  // // This returns a JSON or XML with the users
  // return userRepository.findAll();
  // }

}