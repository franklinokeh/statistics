package com.seerbit.statistics.controllers;


import com.seerbit.statistics.dto.StatisticsDto;
import com.seerbit.statistics.dto.TransactionDto;
import com.seerbit.statistics.exceptions.TransactionInvalidException;
import com.seerbit.statistics.service.transaction.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class TransactionController {

        @Autowired
        private TransactionService transactionService;

        @GetMapping("/statistics")
        public ResponseEntity<StatisticsDto> get() {
                return new ResponseEntity<StatisticsDto>(transactionService.getStats(), HttpStatus.OK);
        }


        @PostMapping("/transactions")
        public ResponseEntity<Void> addTransaction(@RequestBody TransactionDto transactionDto){
                try {
                        transactionService.addTransaction(transactionDto);
                        return new ResponseEntity<>(HttpStatus.ACCEPTED);
                }catch (TransactionInvalidException e) {
                        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
                }
        }


        @DeleteMapping("/transactions")
        public ResponseEntity<Void> deleteTransactions(){
                transactionService.deleteTransactions();
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }



}
