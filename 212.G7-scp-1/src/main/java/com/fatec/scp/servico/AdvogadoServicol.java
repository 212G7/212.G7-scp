package com.fatec.scp.servico;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.fatec.scp.model.Advogado;
import com.fatec.scp.model.AdvogadoRepository;


import com.fatec.scp.model.Endereco;
import com.fatec.scp.model.EnderecoRepository;

@Service
public class AdvogadoServicol implements AdvogadoServico {
	Logger logger = LogManager.getLogger(AdvogadoServicol.class);
	@Autowired
	private AdvogadoRepository AdvogadoRepository;
	@Autowired
	private EnderecoRepository enderecoRepository;

	public Iterable<Advogado> findAll() {
		return AdvogadoRepository.findAll();
	}

	public Advogado findByCpf(String cpf) {
		return AdvogadoRepository.findByCpf(cpf);
	}

	public void deleteById(Long id) {
		AdvogadoRepository.deleteById(id);
		logger.info(">>>>>> 2. comando exclusao executado para o id => " + id);
	}

	public Advogado findById(Long id) {
		return AdvogadoRepository.findById(id).get();
	}

	public ModelAndView saveOrUpdate(Advogado advogado) {
		ModelAndView modelAndView = new ModelAndView("consultarAdvogado");
		try {
			Endereco endereco = obtemEndereco(advogado.getCep());
			if (endereco != null) {
				advogado.setDataCadastro(new DateTime());
				endereco.setCpf(advogado.getCpf());
				enderecoRepository.save(endereco);
				advogado.setEndereco(endereco);
				AdvogadoRepository.save(advogado);
				logger.info(">>>>>> 4. comando save executado ");
				modelAndView.addObject("advogados", AdvogadoRepository.findAll());
			}
		} catch (Exception e) {
			modelAndView.setViewName("cadastrarAdvogado");
			if (e.getMessage().contains("could not execute statement")) {
				modelAndView.addObject("message", "Dados invalidos - advogado já cadastrado.");
				logger.info(">>>>>> 5. advogado ja cadastrado ==> " + e.getMessage());
			} else {
				modelAndView.addObject("message", "Erro não esperado - contate o administrador");
				logger.error(">>>>>> 5. erro nao esperado ==> " + e.getMessage());
			}
		}
		return modelAndView;
	}

	public Endereco obtemEndereco(String cep) {
		RestTemplate template = new RestTemplate();
		String url = "https://viacep.com.br/ws/{cep}/json/";
		Endereco endereco = template.getForObject(url, Endereco.class, cep);
		logger.info(">>>>>> 3. obtem endereco ==> " + endereco.toString());
		return endereco;
	}
}
