package com.davisory.davisory_bd.controller;

import com.davisory.davisory_bd.dao.MontagemRepositorio;
import com.davisory.davisory_bd.model.Montagem;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.List;

@Controller
public class MontagemController {

    @GetMapping("/montagens")
    public String listar(Model model) {
        MontagemRepositorio repo = new MontagemRepositorio();
        List<Montagem> montagens = repo.listar();
        model.addAttribute("montagens", montagens);
        return "montagens";
    }
}