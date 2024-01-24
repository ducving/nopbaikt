package com.example.kt.controller;

import com.example.kt.model.Product;
import com.example.kt.repository.IProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/products")
public class ProductController {
    @Autowired
    private IProductRepository repository;

    @GetMapping("")
    public ModelAndView findAll() {
        ModelAndView modelAndView = new ModelAndView("home");
        List<Product> products = repository.findAll();
        modelAndView.addObject("product", products);
        return modelAndView;
    }

    @GetMapping("/create")
    public ModelAndView formCreate() {
        ModelAndView modelAndView = new ModelAndView("/create");
        modelAndView.addObject("product", new Product());
        return modelAndView;
    }

    @PostMapping("/save")
    public ModelAndView create(@Valid Product product) {
        ModelAndView model = new ModelAndView("redirect:/products");
        repository.save(product);
        return model;
    }

    @GetMapping("/{id}/formUpdate")
    public ModelAndView formUpdate(@PathVariable Long id) {
        ModelAndView modelAndView = new ModelAndView("/update");
        modelAndView.addObject("update", repository.findById(id).get());
        return modelAndView;
    }

    @PostMapping("/update")
    public ModelAndView update(@Valid Product product) {
        ModelAndView modelAndView = new ModelAndView("redirect:/products");
        repository.save(new Product(product.getId(), product.getName(), product.getCode(), product.getDescription(), product.getPrice(), product.getDate()));
        List<Product> productList = repository.findAll();
        modelAndView.addObject("productList", productList);
        return modelAndView;
    }

    @GetMapping("/{id}/delete")
    public ModelAndView delete(@PathVariable Long id) {
        ModelAndView modelAndView= new ModelAndView("redirect:/products");
        repository.deleteById(id);
        return modelAndView;
    }
    @GetMapping("/search")
    public ModelAndView search(@RequestParam String search){
        ModelAndView modelAndView = new ModelAndView("home");
        modelAndView.addObject("product", repository.findAllByNameContains(search));
        return modelAndView;
    }
    @GetMapping("/{id}/view")
    public ModelAndView view(@PathVariable long id){
        ModelAndView modelAndView=new ModelAndView("views");
        modelAndView.addObject("product", repository.findById(id).get());
        return modelAndView;
    }
}
