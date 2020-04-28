package br.com.joserlj.catalogo.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.joserlj.catalogo.domain.Usuario;
import br.com.joserlj.catalogo.service.UsuarioService;

@Controller
@RequestMapping("/catalogoDeMusicas/usuarios")
public class UsuarioController {

	@Autowired
	private UsuarioService usuarioService;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@GetMapping("/cadastro")
	public String preSalvar(@ModelAttribute("usuario") Usuario usuario) {
		return "/usuario/add";
	}

	@PostMapping("/salvar")
	public String salvar(@Valid @ModelAttribute("usuario") Usuario usuario, BindingResult result,
			RedirectAttributes attr) {
		if (result.hasErrors()) {
			return "/usuario/add";
		}

		usuario.setSenha(passwordEncoder.encode(usuario.getSenha()));

		usuarioService.salvar(usuario);
		attr.addFlashAttribute("mensagem", "Usuário cadastrado com sucesso!");

		return "redirect:/catalogoDeMusicas/entrar";
	}
}