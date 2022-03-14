package com.bootcamp.PytoS1_BcoCredit.controller;

import com.bootcamp.PytoS1_BcoCredit.model.Credit;
import com.bootcamp.PytoS1_BcoCredit.model.Payment;
import com.bootcamp.PytoS1_BcoCredit.request.PaymentRequest;
import com.bootcamp.PytoS1_BcoCredit.service.CreditServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;

@Slf4j
@RestController
@RequestMapping("/credit")
public class CreditController {

    @Autowired
    private CreditServiceImpl creditServiceImpl;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void addCredit(@RequestBody Credit credit){
        creditServiceImpl.addCredit(credit);
    }

    @GetMapping
    @ResponseBody
    public Flux<Credit> findAll(){
        return creditServiceImpl.findAllCredit();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Mono<Credit>> findById(@PathVariable("id") Integer id){
        Mono<Credit> credit = creditServiceImpl.findCreditById(id);
        return new ResponseEntity<Mono<Credit>> (credit, credit != null ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public Mono<Credit> update(@RequestBody Credit credit){
        return creditServiceImpl.updateCredit(credit);
    }

    @PutMapping("/payment")
    @ResponseStatus(HttpStatus.OK)
    public  Mono<Credit> updatePayment(@RequestBody PaymentRequest paymentRequest){
        try {
            Mono<Credit> credit = creditServiceImpl.findCreditById(paymentRequest.getIdCredit());
            credit.flatMap(
                    creditResult -> {
                        Payment payment = new Payment();
                        payment.setAmount(paymentRequest.getAmount());
                        payment.setDatePay(paymentRequest.getDatePay());
                        if (creditResult.getPayments() != null){
                            creditResult.getPayments().add(payment);
                        }else {
                            ArrayList<Payment> paymentArrayList = new ArrayList<>();
                            paymentArrayList.add(payment);
                            creditResult.setPayments(paymentArrayList);
                        }
                        return creditServiceImpl.updateCredit(creditResult);
                    }
            ).subscribe();
            return credit;
        }catch (Exception e){
            log.error(e.getMessage());
        }
        return null;
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public  void  delete(@PathVariable("id") Integer id){
        creditServiceImpl.deleteCredit(id).subscribe();
    }
}
