package com.nexeo.kata.services.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nexeo.kata.model.Account;
/**
 * 
 * @author yahyaoui
 *
 */
@Repository
public interface AccountRepository extends JpaRepository<Account, Long>{

}
