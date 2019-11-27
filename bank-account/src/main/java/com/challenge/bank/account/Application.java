package com.challenge.bank.account;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.challenge.bank.account.server.Server;

public class Application {

	public static void main(String[] args) throws InterruptedException {  
		ApplicationContext ctx = new AnnotationConfigApplicationContext(ModuleConfig.class);
		Server server = ctx.getBean(Server.class);
		server.run(9020);
	}
}
