package com.seerbit.statistics.dto;

public class TransactionDto {

        private Double amount;
        private Long timeStamp;

        public TransactionDto(double amount, long timeStamp) {
                this.amount = amount;
                this.timeStamp = timeStamp;
        }

        public Double getAmount() {
                return amount;
        }

        public Long getTimeStamp() {
                return timeStamp;
        }

}
