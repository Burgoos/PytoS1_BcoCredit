package com.bootcamp.PytoS1_BcoCredit.dao;

import com.bootcamp.PytoS1_BcoCredit.model.Credit;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface CreditRepository extends ReactiveMongoRepository<Credit, Integer>{
}
