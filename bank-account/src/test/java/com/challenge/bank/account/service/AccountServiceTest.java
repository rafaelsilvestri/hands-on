package com.challenge.bank.account.service;

import com.challenge.bank.account.dao.AccountDao;
import com.challenge.bank.account.model.TransactionModel;
import net.bytebuddy.implementation.bind.annotation.RuntimeType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.internal.verification.VerificationModeFactory;
import org.mockito.verification.VerificationMode;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class AccountServiceTest {

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Mock
    private AccountDao accountDaoMock;

    @Test
    void depositShouldCallDepositMethodFromDaoOnce() {
        AccountService service = new AccountService(accountDaoMock);

        TransactionModel model = new TransactionModel();
        model.setId("1");
        model.setValue(10D);

        service.deposit(model);
        verify(accountDaoMock, times(1)).deposit("1", 10D);
    }

    @Test
    void shouldReturnTwoEntriesWhenHistoryIsCalledForAccountId1() {
        List<Double> expected = Arrays.asList(10D, 20D);
        when(accountDaoMock.history("1"))
                .thenReturn(expected);

        AccountService service = new AccountService(accountDaoMock);

        List<Double> result = service.history("1");

        verify(accountDaoMock, times(1)).history("1");
        assertEquals(expected, result);
    }
}