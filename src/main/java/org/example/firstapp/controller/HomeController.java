package org.example.firstapp.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home() {
        return "home";
    }

    @GetMapping("/hello")
    public String hello(Model model) {
        String name = "myeongje";
        model.addAttribute("name", name);
        return "hello";
    }

    @GetMapping("/user")
    public String user(Model model) {
        model.addAttribute("userName", "Kim");
        model.addAttribute("age", 20);
        model.addAttribute("city", "seoul");
        return "user";
    }

    @GetMapping("/fruits")
    public String fruits(Model model) {
        List<String> fruitsList = new ArrayList<>();
        fruitsList.add("apple");
        fruitsList.add("orange");
        fruitsList.add("banana");
        fruitsList.add("kiwi");

        model.addAttribute("fruits", fruitsList);
        return "fruits";
    }

    @GetMapping("/grade")
    public String grade(Model model) {
        int score = 100;
        model.addAttribute("score",score);
        return "grade";
    }

    @GetMapping("/lunch")
    public String lunch(Model model) {
        List<String> menus = Arrays.asList("김밥", "라면", "돈까스");

        Random random = new Random();
        String pick = menus.get(random.nextInt(menus.size()));

        model.addAttribute("pick", pick);
        return "lunch";
    }

    @GetMapping("/lotto")
    public String lotto(Model model) {
        List<Integer> numbers = IntStream.rangeClosed(1,45)
                .boxed()
                .collect(Collectors.toList());

        Collections.shuffle(numbers);

        List<Integer> lucky = numbers.subList(0, 6);

        model.addAttribute("lucky", lucky);
        return "lotto";
    }
}
