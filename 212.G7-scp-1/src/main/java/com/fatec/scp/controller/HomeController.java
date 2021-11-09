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
	public ModelAndView cadastrarClientes() {

		return new ModelAndView("cadastrarClientes");
	}

	@GetMapping("/advogados")
	public ModelAndView cadastrarAdvogados() {

		return new ModelAndView("cadastrarAdvogados");
	}

	@GetMapping("/processos")
	public ModelAndView cadastrarProcessos() {

		return new ModelAndView("cadastrarProcessos");
	}

	@GetMapping("/audiencias")
	public ModelAndView cadastrarAudiencias() {

		return new ModelAndView("cadastrarAudiencias");
	}
	
	@GetMapping("/boletos")
	public ModelAndView cadastrarBoletos() {

		return new ModelAndView("cadastrarBoletos");
	}

	@GetMapping("/logout")
	public ModelAndView logout() {

		return new ModelAndView("Logout");
	}

	@GetMapping("/login")
	public ModelAndView paginaLogin() {
		return new ModelAndView("paginaLogin");
	}
	
	@GetMapping("/consultar")
	public ModelAndView consultarCliente() {
		return new ModelAndView("consultarCliente");
	}
	
	@GetMapping("/atualizar")
	public ModelAndView consultarAtualizar() {
		return new ModelAndView("consultarAtualizar");
	}
	
}
