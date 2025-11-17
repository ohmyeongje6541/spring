package org.example.firstapp.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.Year;
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

    @GetMapping("/profile/{username}")
    public String profile(@PathVariable String username, Model model) {
        model.addAttribute("username", username);
        return "profile";
    }

    @GetMapping("/cube/{number}")
    public String cube(@PathVariable int number, Model model) {
        int result = number * number * number;

        model.addAttribute("number", number);
        model.addAttribute("result", result);
        return "cube";
    }

    // 짝수 홀수 판별
    // number/{num} => 짝수인지 홀수인지 판별해서 화면에 출력
    @GetMapping("/number/{num}")
    public String number(@PathVariable int num, Model model) {
//        int result = num;
//
//        model.addAttribute("number", num);
//        model.addAttribute("result", result);
//        return "number";
        String result = null;
        if (num % 2 == 0) {
            result = "짝수";
        } else {
            result = "홀수";
        }
        model.addAttribute("number", num);
        model.addAttribute("result", result);
        return "number";
    }

    //나이 계산
    // /age/{birthYear} => 현재 나이를 계산해서 출력
    // /age/19990 => 36살입니다.
    @GetMapping("/age/{birthYear}")
    public String age(@PathVariable int birthYear, Model model) {
        int currentYear = Year.now().getValue();
        int age = currentYear - birthYear;

        model.addAttribute("age", age);
        return "age";
    }

    @GetMapping("/ping")
    public String ping(Model model) {
        return "ping";
    }

    @GetMapping("/pong")
    public String pong(
            @RequestParam String title,
            @RequestParam String content,
            Model model) {

        model.addAttribute("title", title);
        model.addAttribute("content", content);
        return "pong";
    }
}
