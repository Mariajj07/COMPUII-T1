package com.example.webapp.servlet;


import com.example.webapp.model.Artist;
import com.example.webapp.services.ArtistService;
import com.google.gson.Gson;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.ws.rs.core.Application;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import java.io.IOException;
import java.util.List;

@WebServlet("/artists")
public class ArtistServlet extends HttpServlet {
    private Gson encoder;
    private ArtistService service;

    public void init() throws ServletException{
        encoder = new Gson();
        ApplicationContext context = WebApplicationContextUtils.getRequiredWebApplicationContext(getServletContext());
        service = context.getBean(ArtistService.class);
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        List<Artist> artists = service.findAll();

        String json = encoder.toJson(artists);


        resp.setContentType("application/json");
        resp.getWriter().println(json);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String action = req.getParameter("action");

        if("create".equals(action)){

            String name = req.getParameter("name");
            String nationaity = req.getParameter("nationality");

            service.addArtist(name,nationaity);

            resp.sendRedirect("artists");
            return;

        } else if ("delete".equals(action)){

            long id = Long.parseLong(req.getParameter("id"));

            service.deleteArtist(id);

            resp.sendRedirect("artists");
            return;

        } else if ("search".equals(action)){
            String name = req.getParameter("name");
            Artist artist = service.findByName(name);



            resp.setContentType("application/json");
            resp.getWriter().println(encoder.toJson(artist));

            return;

        }


    }


}
