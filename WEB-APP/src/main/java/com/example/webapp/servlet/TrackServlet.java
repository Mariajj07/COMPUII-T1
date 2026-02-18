package com.example.webapp.servlet;

import com.example.webapp.model.Artist;
import com.example.webapp.model.Track;
import com.example.webapp.repositories.TrackRepository;
import com.example.webapp.services.ArtistService;
import com.example.webapp.services.TrackService;
import com.example.webapp.views.ArtistView;
import com.example.webapp.views.TrackView;
import com.google.gson.Gson;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import java.io.IOException;
import java.util.List;

@WebServlet("/tracks")
public class TrackServlet extends HttpServlet {
    private TrackService service;
    private Gson encoder;

    public void init() throws ServletException{
        encoder = new Gson();
        ApplicationContext context = WebApplicationContextUtils.getRequiredWebApplicationContext(getServletContext());
        service = context.getBean(TrackService.class);
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {

        List<Track> tracks = service.findAll();

        TrackView view = new TrackView();
        String html = view.listTracks(tracks);

        resp.setContentType("text/html");
        resp.getWriter().println(html);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String action = req.getParameter("action");

        if("create".equals(action)){
            String title = req.getParameter("title");
            String genre = req.getParameter("genre");
            double duration = Double.parseDouble(req.getParameter("duration"));
            String album = req.getParameter("album");

            long idA = Long.parseLong(req.getParameter("idA"));

            service.addTrack(title,genre,duration,album,idA);

            resp.sendRedirect("tracks");
            return;

        } else if ("delete".equals(action)){
            long id = Long.parseLong(req.getParameter("id"));

            service.removeTrack(id);

            resp.sendRedirect("tracks");
            return;
        }


    }
}
