package com.nexeo.kata.services.dao;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.nexeo.kata.model.Account;
import com.nexeo.kata.model.Operation;
import com.nexeo.kata.model.OperationHistory;
/**
 * 
 * @author yahyaoui
 *
 */
@Repository
public interface OperationHistoryRepository extends JpaRepository<OperationHistory, Long>{

//	@Query("select oh from OperationHistory oh where oh.account.id=?1 and oh.operation=?2 order by operationDateTime asc")
//	List<OperationHistory> findByAccountAndOperation(Long accountId, Operation operation);
	
	@Query("select oh from OperationHistory oh where oh.account.id=?1")
	List<OperationHistory> findByAccountID(Long accountId);
}
