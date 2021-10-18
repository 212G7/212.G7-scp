package com.fatec.scp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {
	@GetMapping("/")
	public ModelAndView menu() {

		return new ModelAndView("paginaMenu");
	}

	@GetMapping("/clientes")
	public ModelAndView cadastrarCliente() {

		return new ModelAndView("cadastrarCliente");
	}

	@GetMapping("/fornecedores")
	public ModelAndView cadastrarFornecedor() {

		return new ModelAndView("cadastrarFornecedor");
	}

	@GetMapping("/Page02")
	public ModelAndView cadastrarPage02() {

		return new ModelAndView("cadastrarPage02");
	}

	@GetMapping("/Page03")
	public ModelAndView cadastrarPage03() {

		return new ModelAndView("cadastrarPage03");
	}

	@GetMapping("/logout")
	public ModelAndView logout() {

		return new ModelAndView("Logout");
	}
	
}
