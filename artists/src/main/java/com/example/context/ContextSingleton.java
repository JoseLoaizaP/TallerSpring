package com.example.context;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;

//Los que hagan xml modifiquen esto
public class ContextSingleton {
    private static ContextSingleton instance;
    private ConfigurableApplicationContext context;
    
    private ContextSingleton() {
        this.context = new ClassPathXmlApplicationContext("beans.xml");
    }
    
    public static synchronized ContextSingleton getInstance() {
        if (instance == null) {
            instance = new ContextSingleton();
        }
        return instance;
    }
    
    public ApplicationContext getContext() {
        return context;
    }
    
    public void closeContext() {
        if (context instanceof ConfigurableApplicationContext) {
            ((ConfigurableApplicationContext) context).close();
        }
    }
}
