package com.example.sid.DAO;

import com.example.sid.entits.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;



@RepositoryRestResource
public interface TicketRepository extends JpaRepository<Ticket,Long> {

}