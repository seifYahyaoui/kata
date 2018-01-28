package com.nexeo.kata.services.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nexeo.kata.model.Client;
/**
 * 
 * @author yahyaoui
 *
 */
@Repository
public interface ClientRepository extends JpaRepository<Client, Long>{

}
