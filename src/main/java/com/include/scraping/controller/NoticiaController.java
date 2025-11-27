package com.include.scraping.controller;

import com.include.scraping.model.Noticia;
import com.include.scraping.repository.NoticiaRepository;
import com.include.scraping.service.G1ScraperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/")
public class NoticiaController {

    @Autowired
    private NoticiaRepository repository;

    @Autowired
    private G1ScraperService scraperService;

    // Página principal com as notícias
    @GetMapping
    public String home(Model model) {
        List<Noticia> noticias = repository.findAllByOrderByDataScrapingDesc();
        model.addAttribute("noticias", noticias);
        return "home"; // template Thymeleaf
    }

    // Endpoint para forçar o scraping agora
    @GetMapping("/atualizar")
    public String atualizar() {
        scraperService.executarAgora();
        return "redirect:/?atualizado=true";
    }

    // API JSON (opcional)
    @GetMapping("/api/noticias")
    @ResponseBody
    public List<Noticia> api() {
        return repository.findAllByOrderByDataScrapingDesc();
    }
}