package com.example.ex01ssr.controllers;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.ex01ssr.components.ListaComponent;
import com.example.ex01ssr.models.Item;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequestMapping({ "", "/" })
public class IndexController {

 @Autowired
 ListaComponent listaComponent;

 @GetMapping("")
 public String getMethodName(Model model) {
  Integer h = LocalDateTime.now().getHour();
  String mess = (h > 18) ? "boa noite" : (h > 12) ? "boa tarde" : "bom dia";
  model.addAttribute("mensagem", mess);
  return "index";
 }

 @GetMapping("/carrinho")
 public String carrinho(Model model) {
  model.addAttribute("lista", listaComponent.getItens());
  model.addAttribute("total", listaComponent.valorTotal());
  return "carrinho";
 }

 @PostMapping("/carrinho")
 public String carrinho(Item item) {
  listaComponent.getItens().add(item);
  return "redirect:/carrinho";
 }

 @GetMapping("/carrinho/remove/{indice}")
 public String carrinho(@PathVariable(name = "indice", required = true) Integer indice) {
  listaComponent.getItens().remove(listaComponent.getItens().get(indice));
  return "redirect:/carrinho";
 }

}
