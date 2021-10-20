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

		return new ModelAndView("cadastrarClientes");
	}

	@GetMapping("/advogados")
	public ModelAndView cadastrarFornecedor() {

		return new ModelAndView("cadastrarAdvogados");
	}

	@GetMapping("/Processos")
	public ModelAndView cadastrarPage02() {

		return new ModelAndView("cadastrarProcessos");
	}

	@GetMapping("/Audiências")
	public ModelAndView cadastrarPage03() {

		return new ModelAndView("cadastrarAudiências");
	}

	@GetMapping("/logout")
	public ModelAndView logout() {

		return new ModelAndView("Logout");
	}

	@GetMapping("/login")
	public ModelAndView autenticacao() {
		return new ModelAndView("paginaLogin");
	}
}
