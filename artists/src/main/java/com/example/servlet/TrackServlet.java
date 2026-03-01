package com.example.servlet;

import java.io.IOException;

import org.springframework.context.ApplicationContext;

import com.example.context.ContextSingleton;
import com.example.services.ITrackService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "trackServlet", value = "/tracks")
public class TrackServlet extends HttpServlet{
        private ApplicationContext context;

    @Override
    public void init() throws ServletException {

        // Conectamos con el Singleton al iniciar el Servlet
        this.context = ContextSingleton.getInstance().getContext();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        ITrackService trackService = context.getBean(ITrackService.class);
    
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
    }
}
