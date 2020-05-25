package com.example.sid.Controllers;

import com.example.sid.DAO.CinemaRepository;
import com.example.sid.entits.Cinema;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.kafka.KafkaAutoConfiguration;
import org.springframework.data.domain.Page;

import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CinemaController {
    @Autowired
    private CinemaRepository cinemaRepository;

    @GetMapping(value = "/home")
    public String homePage(Model model, @RequestParam(name = "kw", defaultValue = "") String kayWord,
                           @RequestParam(name = "page",defaultValue = "0") int page){
        Page<Cinema> pageCinema=cinemaRepository.findAll(PageRequest.of(page,5));
        model.addAttribute("cinemaPage",pageCinema);
        model.addAttribute("page",page);
        model.addAttribute("kw", kayWord);
        return "Home";
    }

}
