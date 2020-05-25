package com.example.sid.DAO;


import com.example.sid.entits.Film;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;



@RepositoryRestResource
public interface FilmRepository extends JpaRepository<Film,Long> {

}
