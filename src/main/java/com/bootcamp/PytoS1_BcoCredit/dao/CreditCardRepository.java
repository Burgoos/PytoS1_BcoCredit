package com.bootcamp.PytoS1_BcoCredit.dao;

import com.bootcamp.PytoS1_BcoCredit.model.CreditCard;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface CreditCardRepository extends ReactiveMongoRepository<CreditCard, Integer> {
}
