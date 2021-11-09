package com.fatec.scp.controller;

import javax.validation.Valid;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import com.fatec.scp.servico.AdvogadoServico;
import com.fatec.scp.model.Advogado;



@Controller
@RequestMapping(path = "/sig")
public class AdvogadoController {
	Logger logger = LogManager.getLogger(AdvogadoController.class);
	@Autowired
	AdvogadoServico servico;

	@GetMapping("/advogados")
	public ModelAndView retornaFormDeConsultaTodosAdvogados() {
		ModelAndView modelAndView = new ModelAndView("consultarAdvogados");
		modelAndView.addObject("advogados", servico.findAll());
		return modelAndView;
	}

	@GetMapping("/advogado")
	public ModelAndView retornaFormDeCadastroDe(Advogado advogado) {
		ModelAndView mv = new ModelAndView("cadastrarAdvogados");
		mv.addObject("advogado", advogado);
		return mv;
	}

	@GetMapping("/advogados/{cpf}") // diz ao metodo que irá responder a uma requisicao do tipo get
	public ModelAndView retornaFormParaEditarAdvogados(@PathVariable("cpf") String cpf) {
		ModelAndView modelAndView = new ModelAndView("atualizarAdvogados");
		modelAndView.addObject("advogados", servico.findByCpf(cpf)); // o repositorio e injetado no controller
		return modelAndView; // addObject adiciona objetos para view
	}

	@GetMapping("/advogados/{id}")
	public ModelAndView excluirNoFormDeConsultaAdvogados(@PathVariable("id") Long id) {
		servico.deleteById(id);
		logger.info(">>>>>> 1. servico de exclusao chamado para o id => " + id);
		ModelAndView modelAndView = new ModelAndView("consultarAdvogados");
		modelAndView.addObject("clientes", servico.findAll());
		return modelAndView;
	}

	@PostMapping("/advogados")
	public ModelAndView save(@Valid Advogado advogado, BindingResult result) {
		ModelAndView modelAndView = new ModelAndView("consultarAdvogado");
		if (result.hasErrors()) {
			modelAndView.setViewName("cadastrarAdvogados");
		} else {
			modelAndView = servico.saveOrUpdate(advogado);
		}
		return modelAndView;
	}

	@PostMapping("/advogados/{id}")
	public ModelAndView atualizaAdvogados(@PathVariable("id") Long id, @Valid Advogado advogado, BindingResult result) {
		ModelAndView modelAndView = new ModelAndView("consultarAdvogados");
		if (result.hasErrors()) {
			advogado.setId(id);
			return new ModelAndView("atualizarAdvogados");
		}
// programacao defensiva - deve-se verificar se o Cliente existe antes de atualizar
		Advogado umAdvogado = servico.findById(id);
		umAdvogado.setCpf(advogado.getCpf());
		umAdvogado.setNome(advogado.getNome());
		umAdvogado.setEmail(advogado.getEmail());
		umAdvogado.setCep(advogado.getCep());
		modelAndView = servico.saveOrUpdate(umAdvogado);
		return modelAndView;
	}
}