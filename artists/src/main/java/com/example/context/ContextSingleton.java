package com.example.context;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import com.example.config.AppConfig;


//Los que hagan xml modifiquen esto
public class ContextSingleton {
    private static ContextSingleton instance;
    private ConfigurableApplicationContext context;
    
    private ContextSingleton() {
        this.context = new AnnotationConfigApplicationContext(AppConfig.class);
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
