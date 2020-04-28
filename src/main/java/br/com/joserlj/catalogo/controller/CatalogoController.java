package br.com.joserlj.catalogo.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.joserlj.catalogo.domain.Catalogo;
import br.com.joserlj.catalogo.service.CatalogoService;

@Controller
@RequestMapping("/catalogoDeMusicas/usuarios/{usuarioId}/catalogos")
public class CatalogoController {

	@Autowired
	private CatalogoService catalogoService;

	@GetMapping("/listar")
	public ModelAndView listar(@PathVariable("usuarioId") long usuarioId, ModelMap model) {
		model.addAttribute("catalogos", catalogoService.recuperarPorUsuario(usuarioId));
		model.addAttribute("usuarioId", usuarioId);
		return new ModelAndView("/catalogo/list", model);
	}

	@GetMapping("/cadastro")
	public String preSalvar(@ModelAttribute("catalogo") Catalogo catalogo, @PathVariable("usuarioId") long usuarioId) {
		return "/catalogo/add";
	}

	@PostMapping("/salvar")
	public String salvar(@PathVariable("usuarioId") long usuarioId,
			@Valid @ModelAttribute("catalogo") Catalogo catalogo, BindingResult result, RedirectAttributes attr) {
		if (result.hasErrors()) {
			return "/catalogo/add";
		}

		catalogoService.salvar(catalogo, usuarioId);
		attr.addFlashAttribute("mensagem", "Catalogo criado com sucesso!");
		return "redirect:/catalogoDeMusicas/usuarios/" + usuarioId + "/catalogos/listar";
	}

	@GetMapping("/{catalogoId}/atualizar")
	public ModelAndView preAtualizar(@PathVariable("catalogoId") long catalogoId,
			@PathVariable("usuarioId") long usuarioId, ModelMap model) {
		Catalogo catalogo = catalogoService.recuperarPorCatalogoIdEUsuarioId(catalogoId, usuarioId);
		model.addAttribute("catalogo", catalogo);
		model.addAttribute("usuarioId", usuarioId);
		return new ModelAndView("/catalogo/add", model);
	}

	@PutMapping("/salvar")
	public String atualizar(@PathVariable("usuarioId") long usuarioId,
			@Valid @ModelAttribute("catalogo") Catalogo catalogo, BindingResult result, RedirectAttributes attr) {
		if (result.hasErrors()) {
			return "/catalogo/add";
		}

		catalogoService.atualizar(catalogo, usuarioId);
		attr.addFlashAttribute("mensagem", "Catalogo atualizado com sucesso!");
		return "redirect:/catalogoDeMusicas/usuarios/" + usuarioId + "/catalogos/listar";
	}

	@GetMapping("/{id}/remover")
	public String remover(@PathVariable("usuarioId") long usuarioId, @PathVariable("id") long id, RedirectAttributes attr) {
		catalogoService.excluir(usuarioId, id);
		attr.addFlashAttribute("mensagem", "Catalogo excluído com sucesso!");
		return "redirect:/catalogoDeMusicas/usuarios/" + usuarioId + "/catalogos/listar";
	}
}