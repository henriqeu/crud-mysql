package com.example.crudmysql.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.List;
import com.example.crudmysql.model.User;
import com.example.crudmysql.model.UserRepository;

@Controller // Informa que classe UserController é o nosso controller
public class UserController {
	@Autowired // ela serv para nos comunicar com UserRepository
	private UserRepository userRepository;

	// Acesaa a pagina inicial
	@GetMapping("/")
	public String home(Model model) {
		List<User> users = (List<User>) userRepository.findAll();
		model.addAttribute("users", users);
		return "home";
	}

	// Acessar o Form de cadastro da pagina em html
	@GetMapping(path = "/form")
	public String userForm(User users) {
		return "cadastrarCrud";
	}

	// Metodo adicionar dentro do form da página html
	@PostMapping(path = "/add")
	public String novo(@Validated User user, BindingResult result) {
		if (result.hasFieldErrors()) {
			return "redirect:/form";
		}
		userRepository.save(user);
		return "redirect:/";
	}

	//
	@GetMapping(path = "/all")
	public @ResponseBody Iterable<User> allUser() {

		return userRepository.findAll();
	}

	// update
	@PutMapping(path = "/update")
	public @ResponseBody String updateUser(@RequestParam Integer id, @RequestParam String nome,
			@RequestParam String email) {
		User u = userRepository.findById(id).get();
		if (!nome.isEmpty()) {
			u.setNome(nome);
		}
		if (!email.isEmpty()) {
			u.setEmail(email);
		}
		userRepository.save(u);

		return "Alterado";
	}

	// Delete
	@DeleteMapping(path = "/delete/{id}")
	public String delete(@PathVariable(name = "id") int id, Model model) {

			userRepository.deleteById(id);
			return "Alterado";	
		
	}
}
