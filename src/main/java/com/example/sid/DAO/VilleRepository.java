package com.example.sid.DAO;

import com.example.sid.entits.Ville;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;



@RepositoryRestResource
public interface VilleRepository extends JpaRepository<Ville,Long> {

}