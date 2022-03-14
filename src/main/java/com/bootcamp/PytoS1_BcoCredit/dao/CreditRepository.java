package com.bootcamp.PytoS1_BcoCredit.dao;

import com.bootcamp.PytoS1_BcoCredit.model.Credit;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;

import java.util.List;

public interface CreditRepository extends ReactiveMongoRepository<Credit, Integer>{
    public Mono<Credit> findByIdClient(Integer idClient);
}
