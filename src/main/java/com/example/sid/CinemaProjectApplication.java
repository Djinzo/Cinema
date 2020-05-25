package com.example.sid;

import com.example.sid.DAO.CinemaRepository;
import com.example.sid.DAO.SeanceReposiory;
import com.example.sid.Service.ICinemaInetService;
import com.example.sid.entits.Cinema;
import com.example.sid.entits.Salle;
import com.example.sid.entits.Seance;
import com.example.sid.entits.Ville;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

@SpringBootApplication
public class CinemaProjectApplication  implements CommandLineRunner {

    @Autowired
    private ICinemaInetService iCinemaInetService;

    @Autowired
    private CinemaRepository cinemaRepository;
    public static void main(String[] args) {
        SpringApplication.run(CinemaProjectApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        iCinemaInetService.initVilles();
        iCinemaInetService.initCinemas();
        iCinemaInetService.initSalles();
        iCinemaInetService.initPlaces();
        iCinemaInetService.initSeances();
        iCinemaInetService.initCategorise();
        iCinemaInetService.initFilmes();
        iCinemaInetService.initProjection();
        iCinemaInetService.initTicket();
    }
}
