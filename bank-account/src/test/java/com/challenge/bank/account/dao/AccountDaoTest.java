package com.challenge.bank.account.dao;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AccountDaoTest {

    @Test
    public void shouldDeposit20ForAccount1() {
        AccountDao dao = new AccountDao();
        dao.deposit("1", 20D);

        List<Double> history = dao.history("1");
        assertEquals(1, history.size());
        assertEquals(20D, history.stream().findFirst().orElse(null));
    }

}