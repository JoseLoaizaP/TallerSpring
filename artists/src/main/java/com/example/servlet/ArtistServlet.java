package com.example.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Optional;

import org.springframework.context.ApplicationContext;

import com.example.context.ContextSingleton;
import com.example.model.Artist;
import com.example.model.Track;
import com.example.services.IArtistService;
import com.example.services.impl.ArtistService;
import com.example.services.impl.TrackService;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/artists")
public class ArtistServlet extends HttpServlet{

     private ApplicationContext context;

     public void init() {
        this.context = ContextSingleton.getInstance().getContext();
        System.out.println("GamesServlet initialized");

    }

public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

    IArtistService artistService = context.getBean(IArtistService.class);
    List<Artist> artists = artistService.getAll();

    response.setContentType("text/html");
    PrintWriter out = response.getWriter();

    out.println("<html>");
    out.println("<head><title>Artists</title></head>");
    out.println("<body>");

    out.println("<h1>Artists List</h1>");
    out.println("<table border='1'>");
    out.println("<tr><th>ID</th><th>Name</th><th>Nationality</th></tr>");

    for (Artist a : artists) {
        out.println("<tr>");
        out.println("<td>" + a.getId() + "</td>");
        out.println("<td>" + a.getName() + "</td>");
        out.println("<td>" + a.getNationality() + "</td>");
        out.println("</tr>");
    }
    out.println("</table>");
    out.println("<hr>");

    out.println("<h2>Create Artist</h2>");
    out.println("<form method='post' action='artists'>");
    out.println("Name: <input type='text' name='name'><br>");
    out.println("Nationality: <input type='text' name='nationality'><br>");
    out.println("<input type='submit' value='createArtist' name='action'>");
    out.println("</form>");

    out.println("<h2>Search Artist</h2>");
    out.println("<form method='post' action='artists'>");
    out.println("Name: <input type='text' name='name'><br>");
    out.println("<input type='submit' value='searchArtist' name='action'>");
    out.println("</form>");

    out.println("<h2>Delete Artist</h2>");
    out.println("<form method='post' action='artists'>");
    out.println("Id: <input type='number' name='id'><br>");
    out.println("<input type='submit' value='deleteArtist' name='action'>");
    out.println("</form>");

    out.println("<h2>List Tracks</h2>");
    out.println("<form method='post' action='artists'>");
    out.println("<input type='submit' value='listTracks' name='action'>");
    out.println("</form>");

    out.println("<h2>Delete Track</h2>");
    out.println("<form method='post' action='artists'>");
    out.println("Id: <input type='number' name='id'><br>");
    out.println("<input type='submit' value='deleteTrack' name='action'>");
    out.println("</form>");

    out.println("<h2>Create Track</h2>");
    out.println("<form method='post' action='artists'>");
    out.println("Title: <input type='text' name='title'><br>");
    out.println("Album: <input type='text' name='album'><br>");
    out.println("Genre: <input type='text' name='genre'><br>");
    out.println("Duration: <input type='number' name='duration'><br>");
    out.println("<input type='submit' value='createTrack' name='action'>");
    out.println("</form>");

    out.println("<h2>Assign Track to Artist</h2>");
    out.println("<form method='post' action='artists'>");
    out.println("Artist Name: <input type='text' name='artistName'><br>");
    out.println("Track Id: <input type='number' name='trackId'><br>");
    out.println("<input type='submit' value='assignTrack' name='action'>");
    out.println("</form>");

    out.println("</body></html>");
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
            case "deleteArtist":
                doDeleteArtist(request, response);
                break;
            case "listTracks":
                doListTracks(request, response);
                break;
                case "deleteTrack":
                doDeleteTrack(request, response);
                break;
                case "assignTrack":
                doAssignTrack(request, response);
                break;
            case "createTrack":
                doCreateTrack(request, response);
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

        public void doDeleteArtist(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ArtistService artistService = context.getBean(ArtistService.class);

        String idStr = request.getParameter("id");
        if (idStr == null || idStr.isEmpty()) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Id is required");
            return;
        }

        Integer id;
        try {
            id = Integer.parseInt(idStr);
        } catch (NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Id must be a number");
            return;
        }

        boolean deleted = artistService.deleteArtist(id);

        response.setContentType("text/html");
        response.getWriter().println(deleted ? "<p>Artist deleted successfully</p>" : "<p>Artist not found</p>");
        response.getWriter().println("<a href='" + request.getContextPath() + "/artists'>Back</a>");
    }

        public void doListTracks(HttpServletRequest request, HttpServletResponse response) throws IOException {
            TrackService trackService = context.getBean(TrackService.class);
            response.setContentType("text/html");

            var out = response.getWriter();
            var tracks = trackService.getAll();

            out.println("<h2>All Tracks</h2>");
            if (tracks == null || tracks.isEmpty()) { out.println("<p>No tracks registered.</p>"); return; }

            out.println("<ul>");
            for (Track t : tracks) {
                String artists = (t.getArtists() == null || t.getArtists().isEmpty())
                        ? "No artists"
                        : String.join(", ", t.getArtists().stream().map(Artist::getName).toList());

                out.println("<li>" + t.getTitle() + " (#" + t.getId() + ") - " + t.getGenre()
                        + " - " + t.getDuration() + "s - Album: " + t.getAlbumTitle()
                        + " | Artists: " + artists + "</li>");
            }
            out.println("</ul>");
        }

        public void doDeleteTrack(HttpServletRequest request, HttpServletResponse response) throws IOException {
            TrackService trackService = context.getBean(TrackService.class);

            String idStr = request.getParameter("id");
            if (idStr == null || idStr.isEmpty()) {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Id is required");
                return;
            }

            Integer id;
            try {
                id = Integer.parseInt(idStr);
            } catch (NumberFormatException e) {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Id must be a number");
                return;
            }

            boolean deleted = trackService.deleteTrack(id);

            response.setContentType("text/html");
            response.getWriter().println(deleted ? "<p>Track deleted successfully</p>" : "<p>Track not found</p>");
            response.getWriter().println("<a href='" + request.getContextPath() + "/artists'>Back</a>");
        }

        public void doAssignTrack(HttpServletRequest request, HttpServletResponse response) throws IOException {

        ArtistService artistService = context.getBean(ArtistService.class);
        TrackService trackService = context.getBean(TrackService.class);

        String artistName = request.getParameter("artistName");
        String trackIdStr = request.getParameter("trackId");

        response.setContentType("text/html");

        if (artistName == null || artistName.isEmpty() ||
            trackIdStr == null || trackIdStr.isEmpty()) {
            response.getWriter().println("<p>Artist name and Track id are required</p>");
            return;
        }
        Integer trackId;

        try {
            trackId = Integer.parseInt(trackIdStr);
        } catch (NumberFormatException e) {
            response.getWriter().println("<p>Track id must be a number</p>");
            return;
        }

        Optional<Artist> artistOpt = artistService.findByName(artistName);
        Optional<Track> trackOpt = trackService.findById(trackId);

        if (artistOpt.isEmpty()) {
            response.getWriter().println("<p>Artist not found</p>");
            return;
        }

        if (trackOpt.isEmpty()) {
            response.getWriter().println("<p>Track not found</p>");
            return;
        }

        Artist artist = artistOpt.get();
        Track track = trackOpt.get();

        
        if (!artist.getTracks().contains(track)) {
            artist.getTracks().add(track);
            track.getArtists().add(artist);
        }

        response.getWriter().println("<p>Track assigned successfully</p>");
        response.getWriter().println("<a href='" + request.getContextPath() + "/artists'>Back</a>");
    }

        public void doCreateTrack(HttpServletRequest request, HttpServletResponse response) throws IOException {
        TrackService trackService = context.getBean(TrackService.class);
        ArtistService artistService = context.getBean(ArtistService.class);

        String title = request.getParameter("title");
        String albumTitle = request.getParameter("album");
        String genre = request.getParameter("genre");
        String durationStr = request.getParameter("duration");

        if (title == null || title.isEmpty() || albumTitle == null || albumTitle.isEmpty() ||
            genre == null || genre.isEmpty() || durationStr == null || durationStr.isEmpty()) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "All fields are required");
            return;
        }

        int duration;
        try {
            duration = Integer.parseInt(durationStr);
        } catch (NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Duration must be a number");
            return;
        }

        trackService.createTrack(new com.example.model.Track(title, genre, duration, albumTitle));

        response.sendRedirect(request.getContextPath() + "/artists");
    }
        }