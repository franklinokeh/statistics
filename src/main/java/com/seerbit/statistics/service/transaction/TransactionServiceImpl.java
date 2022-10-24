package com.seerbit.statistics.service.transaction;

import com.seerbit.statistics.dto.StatisticsDto;
import com.seerbit.statistics.dto.TransactionDto;
import com.seerbit.statistics.exceptions.TransactionInvalidException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.DoubleSummaryStatistics;
import java.util.List;

@Service
@Slf4j
public class TransactionServiceImpl implements TransactionService{

        private final long statsBasedOnSecs = 30000;
        private static final List<TransactionDto> TRANSACTION_LIST = new ArrayList<>();
        private StatisticsDto stats;
        private final Object lock = new Object();

        public StatisticsDto getStats() {
                return stats;
        }

        public void addTransaction(TransactionDto transactionDto) {
                long timeInterval = System.currentTimeMillis() - transactionDto.getTimeStamp();
                if (timeInterval > statsBasedOnSecs) { // check if timeInterval is greater than 30secs
                        throw new TransactionInvalidException();
                }
                synchronized (lock) {
                        TRANSACTION_LIST.add(transactionDto); //add to Array
                        calculateStatistics();
                }
        }

        public void deleteTransactions() {
                TRANSACTION_LIST.clear();
        }

        @Async  // Statistics is ASYNC to guarantee 0(1)
        void calculateStatistics() {
                DoubleSummaryStatistics stat = TRANSACTION_LIST.stream().mapToDouble(TransactionDto::getAmount)
                        .summaryStatistics();
                stats = new StatisticsDto(stat.getSum(), stat.getAverage(), stat.getMax(), stat.getMin(), stat.getCount());
        }

        @Scheduled(fixedRate = 30000, initialDelay = 5000)
        public void clearOld() {
                synchronized (lock) {
                        TRANSACTION_LIST.removeIf(
                                transaction -> (System.currentTimeMillis() - transaction.getTimeStamp()) > statsBasedOnSecs);
                        calculateStatistics();
                }
        }


}
