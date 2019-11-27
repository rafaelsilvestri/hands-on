package com.challenge.bank.account.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.challenge.bank.account.dao.AccountDao;
import com.challenge.bank.account.model.TransactionModel;

@Component
public class AccountService {

	private AccountDao accountDao;

	@Autowired
	public AccountService(AccountDao accountDao) {
		this.accountDao = accountDao;
	}

	public void deposit(TransactionModel transactionModel) {
		accountDao.deposit(transactionModel.getId(), transactionModel.getValue());
	}
	
	public List<Double> history(String accountId){
		return accountDao.history(accountId);
	}
	
}
