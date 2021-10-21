package com.github.rafaelsilvestri.spring;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

//@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@Component
public class FooSingleton {

    private String hello;

    public FooSingleton() {
        this.hello = "";
    }

    /**
     * Spring default scope is Singleton, that means every time hello() is invoked
     * a new ' Hello' will be concat to the hello var.
     */
    public String hello() {
        hello += " Hello";
        return hello;
    }
}
