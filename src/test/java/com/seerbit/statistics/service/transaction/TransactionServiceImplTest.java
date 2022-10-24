package com.seerbit.statistics.service.transaction;

import com.seerbit.statistics.dto.StatisticsDto;
import com.seerbit.statistics.dto.TransactionDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.*;

class TransactionServiceImplTest {
        @Mock
        List<TransactionDto> TRANSACTION_LIST;
        @Mock
        StatisticsDto stats;
        @Mock
        Object lock;
        @Mock
        Logger log;
        @InjectMocks
        TransactionServiceImpl transactionServiceImpl;

        @BeforeEach
        void setUp() {
                MockitoAnnotations.openMocks(this);
        }

        @Test
        void testAddTransaction() {
                transactionServiceImpl.addTransaction(new TransactionDto(1220.00, System.currentTimeMillis()));
        }

        @Test
        void testDeleteTransactions() {
                transactionServiceImpl.deleteTransactions();
        }

        @Test
        void testCalculateStatistics() {
                transactionServiceImpl.calculateStatistics();
        }

        @Test
        void testClearOld() {
                transactionServiceImpl.clearOld();
        }
}
