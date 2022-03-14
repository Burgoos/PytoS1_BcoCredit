package com.bootcamp.PytoS1_BcoCredit.service;

import com.bootcamp.PytoS1_BcoCredit.dao.CreditCardRepository;
import com.bootcamp.PytoS1_BcoCredit.model.CreditCard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CreditCardServiceImpl implements CreditCardServiceInterface{

    @Autowired
    CreditCardRepository creditCardRepository;

    @Override
    public void addCreditCard(CreditCard creditCard) {
        creditCardRepository.save(creditCard);
    }

    @Override
    public Mono<CreditCard> findCreditCardById(Integer id) {
        return creditCardRepository.findById(id);
    }

    @Override
    public Flux<CreditCard> findAllCreditCard() {
        return creditCardRepository.findAll();
    }

    @Override
    public Mono<CreditCard> updateCreditCard(CreditCard creditCard) {
        return creditCardRepository.save(creditCard);
    }

    @Override
    public Mono<Void> deleteCreditCard(Integer id) {
        return creditCardRepository.deleteById(id);
    }
}
