package com.bootcamp.PytoS1_BcoCredit.controller;

import com.bootcamp.PytoS1_BcoCredit.model.Consumption;
import com.bootcamp.PytoS1_BcoCredit.model.CreditCard;
import com.bootcamp.PytoS1_BcoCredit.request.ConsumptionRequest;
import com.bootcamp.PytoS1_BcoCredit.service.CreditCardServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.util.ArrayList;

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

    @PutMapping("/consumption")
    @ResponseStatus(HttpStatus.OK)
    public Mono<CreditCard> consumptionCreditCard(@RequestBody ConsumptionRequest consumptionRequest){
        try {
            Mono<CreditCard> creditCard = creditCardService.findCreditCardById(consumptionRequest.getIdCreditCard());
            creditCard.flatMap(
                creditCardResult -> {
                    BigDecimal preAmountUsed = consumptionRequest.getAmount().add(creditCardResult.getAmountUsed());
                    if (preAmountUsed.compareTo(creditCardResult.getLimit()) <= 0){
                        Consumption consumption = new Consumption();
                        consumption.setAmount(consumptionRequest.getAmount());
                        consumption.setDatePay(consumptionRequest.getDatePay());
                        if (creditCardResult.getConsumptions() != null){
                            creditCardResult.getConsumptions().add(consumption);
                        }else {
                            ArrayList<Consumption> consumptionArrayList = new ArrayList<>();
                            consumptionArrayList.add(consumption);
                            creditCardResult.setConsumptions(consumptionArrayList);
                        }
                        creditCardResult.setAmountUsed(creditCardResult.getAmountUsed().add(consumptionRequest.getAmount()));
                        creditCardResult.setFeesPaid(creditCardResult.getFeesPaid() + 1);
                        return creditCardService.updateCreditCard(creditCardResult);
                    }else{
                        return Mono.just("Exceed the limit");
                    }
                }
            ).subscribe();
            return creditCard;
        }catch (Exception e){
            log.error(e.getMessage());
        }
        return  null;
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable("id") Integer id){
        creditCardService.deleteCreditCard(id).subscribe();
    }
}
