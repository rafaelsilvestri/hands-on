package com.ilegra.training.netty;

import java.util.Set;
import static java.util.Collections.singleton;

public class Application extends javax.ws.rs.core.Application {

    @Override
    public Set<Class<?>> getClasses() {
        return singleton(SumResourceV1.class);
    }
}
