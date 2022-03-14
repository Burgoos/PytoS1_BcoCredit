package com.bootcamp.PytoS1_BcoCredit.service;

import com.bootcamp.PytoS1_BcoCredit.dao.CreditRepository;
import com.bootcamp.PytoS1_BcoCredit.model.Credit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CreditServiceImpl implements CreditServiceInterface{

    @Autowired
    CreditRepository creditRepository;

    @Override
    public Mono<Credit> addCredit(Credit credit) {
        creditRepository.save(credit).subscribe();
        return Mono.just(credit);
    }

    @Override
    public Mono<Credit> findCreditById(Integer id) {
        return creditRepository.findById(id);
    }

    @Override
    public Flux<Credit> findAllCredit() {
        return creditRepository.findAll();
    }

    @Override
    public Mono<Credit> updateCredit(Credit credit) {
        return creditRepository.save(credit);
    }

    @Override
    public Mono<Void> deleteCredit(Integer id) {
        return creditRepository.deleteById(id);
    }

    @Override
    public Mono<Credit> findCreditByIdClient(Integer id) {
         return creditRepository.findByIdClient(id);
    }
}
