package com.example.sid.Service;

import com.example.sid.DAO.*;
import com.example.sid.entits.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Random;
import java.util.stream.Stream;

@Service
@Transactional
public class InetServiceImp implements ICinemaInetService {
    @Autowired
    private CinemaRepository cinemaRepository;
    @Autowired
    private VilleRepository villeRepository;
    @Autowired
    private SalleRepository salleRepository;
    @Autowired
    private PlaceRepository placeRepository;
    @Autowired
    private SeanceReposiory seanceReposiory;
    @Autowired
    private CategorieRepository categorieRepository;
    @Autowired
    private FilmRepository filmRepository;
    @Autowired
    private ProjectionFilmRepository projectionFilmRepository;
    @Autowired
    private TicketRepository ticketRepository;
    @Override
    public void initVilles() {
        Stream.of("Tan-Tan","Casa","agadir","dakhla").forEach(v->{
            Ville ville=new Ville();
            ville.setName(v);
            villeRepository.save(ville);
        });
    }

    @Override
    public void initCinemas() {
        villeRepository.findAll().forEach(v->{
            Stream.of("megaraa","Imax","chahrazaf").forEach(cin->{
                Cinema cinema=new Cinema();
                cinema.setName(cin);
                cinema.setNbSalle(3+(int)(Math.random()*7));
                cinema.setVille(v);
                cinemaRepository.save(cinema);
            });
        });
    }

    @Override
    public void initSalles() {
        cinemaRepository.findAll().forEach(v->{
            Stream.of("sale1","sale2","sale3","sale4").forEach(s->{
                Salle salle=new Salle();
                salle.setName(s);
                salle.setCinema(v);
                salle.setNbPlace((int)Math.random());
                salle.setCinema(v);
                salleRepository.save(salle);
            });
        });
    }

    @Override
    public void initPlaces() {
        salleRepository.findAll().forEach(v->{
            Stream.of(1,2,3,4,5,6,7).forEach(p->{
                Place place=new Place();
                place.setNumero(p);
                place.setAltitude(Math.random()*7);
                place.setLatitude(Math.random()*7);
                place.setLongitude(Math.random()*7);
                place.setSalle(v);
                placeRepository.save(place);
            });
        });
    }

    @Override
    public void initSeances() {
        Stream.of(new Date(),new Date(),new Date()).forEach(date->{
            Seance seance=new Seance();
            seance.setHeureDebut(date);
            seanceReposiory.save(seance);
        });
    }

    @Override
    public void initCategorise() {
        Stream.of("action","drama","comid").forEach(v->{
            Categorie categorie=new Categorie();
            categorie.setName(v);
            categorieRepository.save(categorie);
        });
    }

    @Override
    public void initFilmes() {
        categorieRepository.findAll().forEach(v->{
            Stream.of("Ema","the space","star wars","the 100").forEach(t->{
                Film film=new Film();
                film.setTitle(t);
                film.setDure(Math.random()*7);
                film.setDateSortir(new Date());
                film.setDescription(t);
                film.setCategorie(v);
                filmRepository.save(film);
            });
        });

    }

    @Override
    public void initProjection() {
      /*  villeRepository.findAll().forEach(v->{
            v.getCinemas().forEach(cinema->{
                cinema.getSalles().forEach(salle -> {
                    filmRepository.findAll().forEach(film -> {
                        seanceReposiory.findAll().forEach(seance -> {
                            ProjectionFilm projectionFilm=new ProjectionFilm();
                            projectionFilm.setDateProjection(new Date());
                            projectionFilm.setFilm(film);
                            projectionFilm.setSalle(salle);
                            projectionFilm.setSeance(seance);
                            projectionFilm.setPrix(Math.random()*10);
                            projectionFilmRepository.save(projectionFilm);
                        });
                    });
                });
            });
        });*/
        ProjectionFilm projectionFilm=new ProjectionFilm();
        projectionFilm.setDateProjection(new Date());
        projectionFilm.setFilm(filmRepository.findAll().get(1));
        projectionFilm.setSalle(salleRepository.findAll().get(1));
        projectionFilm.setSeance(seanceReposiory.findAll().get(1));
        projectionFilm.setPrix(Math.random()*10);
        projectionFilmRepository.save(projectionFilm);
    }

    @Override
    public void initTicket() {
        placeRepository.findAll().forEach(place->{
            projectionFilmRepository.findAll().forEach(pf->{
                Stream.of("amine","mohamed","khalid","hassan").forEach(nom->{
                    Ticket ticket=new Ticket();
                    ticket.setNomClient(nom);
                    ticket.setCodePayement(new Random().nextInt());
                    ticket.setPrix(Math.random()*10);
                    ticket.setProjectionFilm(pf);
                    ticket.setPlace(place);
                    ticket.setReservee(true);
                    ticket.setCodePayement((int)Math.random()*20);
                    ticketRepository.save(ticket);
                });
            });
        });
    }
}
