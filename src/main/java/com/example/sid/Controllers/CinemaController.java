package com.example.sid.Controllers;

import com.example.sid.DAO.CinemaRepository;
import com.example.sid.DAO.FilmRepository;
import com.example.sid.DAO.TicketRepository;
import com.example.sid.entits.Cinema;
import com.example.sid.entits.Film;
import com.example.sid.entits.Ticket;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.kafka.KafkaAutoConfiguration;
import org.springframework.data.domain.Page;

import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin("*")
public class CinemaController {
    @Autowired
    private FilmRepository filmRepository;
    @Autowired
    private TicketRepository ticketRepository;


    @GetMapping(value = "/imageFilm/{id}",produces = MediaType.IMAGE_JPEG_VALUE)
    public byte[] image(@PathVariable(name = "id") Long id) throws IOException {
        Film film=filmRepository.findById(id).get();
        String photo=film.getPhoto();
        File file=new File(System.getProperty("user.home")+"\\cinema\\"+photo+".jpg");
        System.out.println(System.getProperty("user.home")+"\\cinema\\"+photo+".jpg");
        Path path=Paths.get(file.toURI());
        return Files.readAllBytes(path);
    }

    @PostMapping("/payerTicker")
    @Transactional
    public List<Ticket> payerTicket(@RequestBody TicketForm ticketForm){
        List<Ticket> tick = new ArrayList();
        System.out.println(ticketForm.toString());
        ticketForm.getListTicket().forEach(v->{
           System.out.println(v);
            Ticket ticket=ticketRepository.findById(v).get();
            ticket.setNomClient(ticketForm.getNomClent());
            ticket.setReservee(true);
            ticket.setCodePayement(ticketForm.getCodePayment());
            tick.add(ticket);
            ticketRepository.save(ticket);

        });
        return tick;
    }

}

@Data
class TicketForm{
    private List<Long> listTicket=new ArrayList<>();
    private String nomClent;
    private int codePayment;

}
