package com.challenge.bank.account.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;


@Component
public class AccountDao {

	private Map<String, List<Double>> transactions;

	public AccountDao() {
		transactions = new HashMap<>();
	}
	
	public void deposit(String accountId, Double amount) {
		System.out.println("deposit: " + amount + " for " + accountId);
		List<Double> list = transactions.getOrDefault(accountId, new ArrayList<>());
		list.add(amount);
		transactions.put(accountId, list);
	}
	
	public List<Double> history(String accountId){
		System.out.println("history: " + accountId);
		return transactions.get(accountId);
	}
}
