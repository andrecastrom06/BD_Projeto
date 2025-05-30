package com.davisory.davisory_bd.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.davisory.davisory_bd.dao.FuncionarioRepositorio;
import com.davisory.davisory_bd.dto.LoginDTO;
import com.davisory.davisory_bd.model.Funcionario;

import jakarta.servlet.http.HttpSession;


@Controller
public class LoginController {

    @GetMapping("/")
    public String inicio() {
        return "login"; 
    }

    @PostMapping("/login")
    public String login(@ModelAttribute LoginDTO loginDTO, HttpSession session, Model model) {
        String nome = loginDTO.getNome();
        String dataStr = loginDTO.getData_contratacao();
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("ddMMyyyy");
            LocalDate dataContratacao = LocalDate.parse(dataStr, formatter);
            Funcionario funcionario = FuncionarioRepositorio.acharAdministrativoPorNomeEData(nome, dataContratacao);
            if (funcionario != null) {
                session.setAttribute("usuarioLogado", funcionario);
                return "inicio";
            } else {
                model.addAttribute("erro", "Login ou senha incorretos.");
                return "login";
            }
        } catch (DateTimeParseException e) {
            model.addAttribute("erro", "Login / senha inválida.");
            return "login";
        }
    }
}