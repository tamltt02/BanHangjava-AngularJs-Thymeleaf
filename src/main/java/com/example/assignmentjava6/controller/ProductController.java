package com.example.assignmentjava6.controller;

import com.example.assignmentjava6.entity.Product;
import com.example.assignmentjava6.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Controller
public class ProductController {

    @Autowired
    ProductService productService;

    @RequestMapping("/product/list")
    public String list(Model model, @RequestParam("id") Optional<String> id) {
        if (id.isPresent()) {
            List<Product> list = productService.findByCategory(id.get());
            model.addAttribute("items", list);
        } else {
            List<Product> list = productService.findAll();
            model.addAttribute("items", list);
        }
        return "product/list";
    }


    @RequestMapping("/product/detail/{id}")
    public String detail(Model model, @PathVariable("id") Integer id) {
        Product product = productService.findById(id);
        model.addAttribute("item", product);
        return "product/detail";
    }
}
