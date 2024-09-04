package com.example.ex01ssr.components;

import java.util.List;
import java.util.ArrayList;

import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import com.example.ex01ssr.models.Item;

@Component
@SessionScope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class ListaComponent {
 private List<Item> itens = new ArrayList<>();

 public List<Item> getItens() {
  return itens;
 }

 public void setItens(List<Item> itens) {
  this.itens = itens;
 }

 public Double valorTotal(){
  return this.itens.stream().mapToDouble(i ->{
   return i.getQtd() * i.getVlUnitario();
  }).sum();
 }

}
