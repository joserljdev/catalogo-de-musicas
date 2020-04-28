package br.com.joserlj.catalogo.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.joserlj.catalogo.domain.Usuario;

@Controller
@RequestMapping("/catalogoDeMusicas")
public class SegurancaController {

	@GetMapping(path = "/acessar")
	public String carregarApresentacao() {
		return "redirect:/catalogoDeMusicas/entrar";
	}

	@GetMapping(path = "/entrar")
	public String realizarLogin(@AuthenticationPrincipal Usuario usuario) {
		if (usuario != null) {
			int usuarioId = (int) usuario.getId();

			return "redirect:/catalogoDeMusicas/usuarios/" + usuarioId + "/catalogos/listar";
		}

		return "Login";
	}
}