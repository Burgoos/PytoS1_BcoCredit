package com.bootcamp.PytoS1_BcoCredit.controller;

import com.bootcamp.PytoS1_BcoCredit.model.CreditCard;
import com.bootcamp.PytoS1_BcoCredit.service.CreditCardServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@RestController
@RequestMapping("/creditCard")
public class CreditCardController {

    @Autowired
    private CreditCardServiceImpl creditCardService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void addCreditCard(@RequestBody CreditCard creditCard){
        creditCardService.addCreditCard(creditCard);
    }

    @GetMapping
    @ResponseBody
    public Flux<CreditCard> findAll(){
        return creditCardService.findAllCreditCard();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Mono<CreditCard>> findById(@PathVariable("id") Integer id){
        Mono<CreditCard> creditCard = creditCardService.findCreditCardById(id);
        return  new ResponseEntity<Mono<CreditCard>>(creditCard, creditCard != null ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public  Mono<CreditCard> update (@RequestBody CreditCard creditCard){
        return  creditCardService.updateCreditCard(creditCard);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable("id") Integer id){
        creditCardService.deleteCreditCard(id).subscribe();
    }
}
