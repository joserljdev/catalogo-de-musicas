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

import br.com.joserlj.catalogo.domain.Musica;
import br.com.joserlj.catalogo.service.MusicaService;

@Controller
@RequestMapping("/catalogoDeMusicas/catalogos/{catalogoId}/musicas") 
public class MusicaController {

    @Autowired
    private MusicaService musicaService;

    @GetMapping("/listar")
    public ModelAndView listar(@PathVariable("catalogoId") long catalogoId, ModelMap model) {
        model.addAttribute("musicas", musicaService.recuperarPorCatalogo(catalogoId));
        model.addAttribute("catalogoId", catalogoId);
        return new ModelAndView("/musica/list", model);
    }

    @GetMapping("/cadastro")
    public String preSalvar(@ModelAttribute("musica") Musica musica, @PathVariable("catalogoId") long catalogoId) {
        return "/musica/add";
    }

    @PostMapping("/salvar")
    public String salvar(@PathVariable("catalogoId") long catalogoId, @Valid @ModelAttribute("musica") Musica musica, BindingResult result, RedirectAttributes attr) {
        if (result.hasErrors()) {
            return "/musica/add";
        }

        musicaService.salvar(musica, catalogoId);
        attr.addFlashAttribute("mensagem", "Música salva com sucesso!");
        return "redirect:/catalogoDeMusicas/catalogos/" + catalogoId + "/musicas/listar";
    }                     

    @GetMapping("/{musicaId}/atualizar")
    public ModelAndView preAtualizar(@PathVariable("catalogoId") long catalogoId, @PathVariable("musicaId") long musicaId, ModelMap model) {
        Musica musica = musicaService.recuperarPorCatalogoIdEMusicaId(catalogoId, musicaId);
        model.addAttribute("musica", musica);
        model.addAttribute("catalogoId", catalogoId);
        return new ModelAndView("/musica/add", model);
    }

    @PutMapping("/salvar")
    public String atualizar(@PathVariable("catalogoId") long catalogoId, @Valid @ModelAttribute("musica") Musica musica, BindingResult result, RedirectAttributes attr) {
        if (result.hasErrors()) {
            return "/musica/add";
        }

        musicaService.atualizar(musica, catalogoId);
        attr.addFlashAttribute("mensagem", "Música atualizada com sucesso!");
        return "redirect:/catalogoDeMusicas/catalogos/" + catalogoId + "/musicas/listar";
    }

    @GetMapping("/{musicaId}/remover")
    public String remover(@PathVariable("catalogoId") long catalogoId, @PathVariable("musicaId") long musicaId, RedirectAttributes attr) {
        musicaService.excluir(catalogoId, musicaId);
        attr.addFlashAttribute("mensagem", "Música excluída com sucesso!");
        return "redirect:/catalogoDeMusicas/catalogos/" + catalogoId + "/musicas/listar";
    }
}