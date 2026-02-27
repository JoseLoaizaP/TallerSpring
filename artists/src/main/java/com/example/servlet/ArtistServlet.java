package com.example.servlet;

import java.io.IOException;
import java.util.List;

import org.springframework.context.ApplicationContext;

import com.example.context.ContextSingleton;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import com.example.services.impl.ArtistService;
import com.example.model.Artist;
import com.example.model.Track;

public class ArtistServlet extends HttpServlet{

     private ApplicationContext context;

     public void init() {
        this.context = ContextSingleton.getInstance().getContext();
        System.out.println("GamesServlet initialized");

    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        response.setContentType("text/html");
        response.getWriter().println("<h2>Create Artist</h2>");
        response.getWriter().println("<form method='post' action='artists'>");
        response.getWriter().println("Name: <input type='text' name='name'><br>");
        response.getWriter().println("Nationality: <input type='text' name='nationality'><br>");
        response.getWriter().println("<input type='submit' value='createArtist' name='action'>");
        response.getWriter().println("</form>");
        response.getWriter().println("<h2>Busca un Artista</h2>");
        response.getWriter().println("<form method='post' action='artists'>");
        response.getWriter().println("Name: <input type='text' name='name'><br>");
        response.getWriter().println("<input type='submit' value='searchArtist' name='action'>");
        response.getWriter().println("</form>");
        response.getWriter().println("</body></html>");
        
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String action = request.getParameter("action");

        if (action == null) {
        response.sendError(HttpServletResponse.SC_BAD_REQUEST);
        return;
        }

        switch(action){
            case "createArtist":
                doCreateArtist(request, response);
                break;
            case "searchArtist":
                doSearchArtist(request, response);
                break;
            default:
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Unknown action: " + action);
        }
    }

    public void doCreateArtist(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ArtistService artistService = context.getBean(ArtistService.class);

        String name = request.getParameter("name");
        String nationality = request.getParameter("nationality");
        if (name == null || name.isEmpty() || nationality == null ||  nationality.isEmpty()) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Name and nationality are required");
            return;
        }
        artistService.createArtist(new com.example.model.Artist(name, nationality));
        response.getWriter().println("Artist created successfully");
        response.sendRedirect(request.getContextPath() + "/artists");
    }

    public void doSearchArtist(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ArtistService artistService = context.getBean(ArtistService.class);

        String name = request.getParameter("name");
        response.setContentType("text/html");
        if (name == null || name.isEmpty()) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Name is required");
            return;
        }
        Artist artist= artistService.findByName(name).orElse(null);
        if(artist == null){
            response.getWriter().println("Artist not found");
            response.sendRedirect(request.getContextPath() + "/artists");
            return;
        }
        response.getWriter().println("<h2>" + artist.getName() + "</h2>");
        response.getWriter().println("<p>Nationality: " + artist.getNationality() + "</p>");
        List<Track> tracks = artistService.getArtistsTracks(name);
        if(tracks.isEmpty()){
            response.getWriter().println("<p>No tracks found for this artist</p>");
        } else {
            response.getWriter().println("<h3>Tracks:</h3><ul>");
            for(Track track : tracks){
                response.getWriter().println("<li>" + track.getTitle() + "</li>");
            }
            response.getWriter().println("</ul>");
        }
        response.getWriter().println("</body></html>");
    
    }
    
}
