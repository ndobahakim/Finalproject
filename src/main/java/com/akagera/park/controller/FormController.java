package com.akagera.park.controller;

import com.akagera.park.model.Form;
import com.akagera.park.services.FormService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class FormController {
    private final FormService formService;


    public FormController(FormService formService) {
        this.formService = formService;
    }

    @GetMapping("/createForm")
    public String getFormForm(Model model) {
        Form form = new Form();
        model.addAttribute("form", form);
        return "createForm";
    }

    @PostMapping("/createForm")
    public String createForm(@ModelAttribute("form") Form form) {
        formService.saveForm(form);
        return "redirect:/admin/dashboard";
    }
    @GetMapping("/delete_profile/{id}")
    public String deleteForm(@PathVariable("id")int id ){
        formService.deleteFormById(id);
        return  "redirect:/table";
    }
    @GetMapping("/update/{id}")
    public  String updateForm(@PathVariable("id")int id, Model model){
        System.out.println("ID from URL: " + id);
        model.addAttribute("student", formService.getFormById(id).orElse(null));
        return "updateForm";

    }
    @PostMapping("/update")
    public String updateForm(@ModelAttribute Form form){
        formService.saveForm(form);
        return "redirect:/table";
    }
}
