package com.github.i0kits.app.web;

import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class IndexController {

  @GetMapping(value = "/hello")
  public String hello(Model model,
                      @RequestParam(value = "name", required = false, defaultValue = "Thymeleaf") String name) {
    model.addAttribute("data", name);
    return "hello";
  }
}
