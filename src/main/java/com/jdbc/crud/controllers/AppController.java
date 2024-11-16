package com.jdbc.crud.controllers;

import com.jdbc.crud.model.Sales;
import com.jdbc.crud.repository.SalesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class AppController {

    @Autowired
    private SalesRepository repository;

    @RequestMapping(value = "/")
    public String index(Model model) {
        model.addAttribute("sales", repository.findAll());
        return "index";
    }

    @RequestMapping(value = "/new")
    public String form(Model model) {
        Sales sales = new Sales();
        model.addAttribute("sales", sales);
        return "form";
    }

    @RequestMapping(value = "/detail/{id}")
    public String detail(@PathVariable("id") int id, Model model) {
        Sales sale = repository.findById(id).orElse(null);
        if (sale != null) {
            model.addAttribute("sales", sale);
        } else {
            return "redirect:/";
        }
        return "detail";
    }

    @RequestMapping(value = "/edit/{id}")
    public String editForm(@PathVariable("id") int id, Model model) {
        Sales sales = repository.findById(id).orElse(null);
        model.addAttribute("sales", sales);
        return "form";
    }

    @PostMapping(value = "/save")
    public RedirectView save(@ModelAttribute("sales") Sales sales) {
        repository.save(sales);
        return new RedirectView("/");
    }


    @RequestMapping(value = "/delete/{id}")
    public RedirectView delete(@PathVariable("id") int id) {
        repository.deleteById(id);
        return new RedirectView("/");
    }
}

