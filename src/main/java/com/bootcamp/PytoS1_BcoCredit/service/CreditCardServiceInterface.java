package com.bootcamp.PytoS1_BcoCredit.service;

import com.bootcamp.PytoS1_BcoCredit.model.CreditCard;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CreditCardServiceInterface {

    void addCreditCard(CreditCard creditCard);

    Mono<CreditCard> findCreditCardById(Integer id);

    Flux<CreditCard> findAllCreditCard();

    Mono<CreditCard> updateCreditCard(CreditCard creditCard);

    Mono<Void> deleteCreditCard(Integer id);
}
