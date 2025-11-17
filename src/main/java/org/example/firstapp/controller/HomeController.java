package org.example.firstapp.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
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

    @GetMapping("/greet/{name}")
    public String greet(@PathVariable String name, Model model) {
        model.addAttribute("name" , "name");
        return "greet";
    }

    @GetMapping("/square/{num}")
    public String square(@PathVariable int num, Model model) {
        int result = num * num;
        model.addAttribute("num", num);
        model.addAttribute("result", result);
        return "square";
    }

    @GetMapping("/food")
    public String food(Model model) {
        List<String> food = Arrays.asList("국밥", "탕수육", "돈까스", "제육볶음", "스테이크");
        Random random = new Random();
        String select = food.get(random.nextInt(food.size()));
        model.addAttribute("select", select);
        return "food";
    }

    @GetMapping("/today")
    public String today(Model model) {
        LocalDate day = LocalDate.now();
        model.addAttribute("day", day);
        return "today";
    }

    @GetMapping("/bmi")
    public String bmi(
            @RequestParam int weight,
            @RequestParam int height,
            Model model) {
        double bmi = weight / Math.pow(height / 100, 2);
        model.addAttribute("bmi", Math.round(bmi * 10) / 10.0);
        return "bmi";
    }

    @GetMapping("/randomColor")
    public String randomColor(Model model) {
        List<String> color = Arrays.asList("red", "blue", "green", "yellow", "black");
        Random random = new Random();
        String list = color.get(random.nextInt(color.size()));
        model.addAttribute("list", list);
        return "randomColor";
    }

    @GetMapping("/grade2")
    public String grade2(
            @RequestParam int score,
            Model model
    ) {
        String grade;
        if (score >= 90) grade = "A";
        else if (score >= 80) grade = "B";
        else if (score >= 70) grade = "C";
        else grade = "D";

        model.addAttribute("score", score);
        model.addAttribute("grade", grade);
        return "grade2";
    }

    @GetMapping("/sum")
    public String sum(
            @RequestParam int num1,
            @RequestParam int num2,
            Model model
    ) {
        int sum = num1 + num2;
        model.addAttribute("sum", sum);
        return "sum";
    }

    @GetMapping("/lotto2")
    public String lotto2(Model model) {
        List<Integer> lotto = IntStream.rangeClosed(1, 45)
                .boxed()
                .collect(Collectors.toList());
        Collections.shuffle(lotto);
        List<Integer> lucky = lotto.subList(0, 6);
        int bonus = lotto.get(6);
        model.addAttribute("lucky", lucky);
        model.addAttribute("bonus", bonus);
        return "lotto2";
    }

    @GetMapping("/calc")
    public String calc(
            @RequestParam int x,
            @RequestParam int y,
            @RequestParam String op,
            Model model) {
        double result;

        switch (op) {
            case "add": result = x + y; break;
            case "sub": result = x - y; break;
            case "mul": result = x * y; break;
            case "div":  result = (y == 0) ? 0 : (double)x / y; break;
            default:
                result = 0;
        }

        model.addAttribute("x", x);
        model.addAttribute("y", y);
        model.addAttribute("op", op);
        model.addAttribute("result", result);

        return "calc";
    }
}
