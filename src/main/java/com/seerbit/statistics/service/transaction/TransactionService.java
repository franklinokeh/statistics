package com.seerbit.statistics.service.transaction;

import com.seerbit.statistics.dto.StatisticsDto;
import com.seerbit.statistics.dto.TransactionDto;

public interface TransactionService {

        void addTransaction(TransactionDto transactionDto);

        void clearOld();

        StatisticsDto getStats();

        void deleteTransactions();
}
