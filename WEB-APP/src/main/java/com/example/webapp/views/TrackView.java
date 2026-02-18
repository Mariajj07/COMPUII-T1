package com.example.webapp.views;

import com.example.webapp.model.Track;

import java.util.List;

public class TrackView {

    public String listTracks(List<Track> tracks){

        StringBuilder b = new StringBuilder();

        b.append("""
        <style>
            table {
                width: 70%;
                border-collapse: collapse;
                margin: 20px auto;
                font-family: Arial;
                background-color: #ffe6f0;
                box-shadow: 0 4px 8px rgba(0,0,0,0.1);
            }

            thead {
                background-color: #ff99cc;
                color: white;
            }

            th, td {
                padding: 12px;
                border: 1px solid #ffb3d9;
                text-align: center;
            }

            tbody tr:nth-child(even) {
                background-color: #fff0f5;
            }

            tbody tr:hover {
                background-color: #ffccdd;
            }
        </style>
        """);

        b.append("<table>");
        b.append("<thead><tr>");
        b.append("<th>ID</th>");
        b.append("<th>Title</th>");
        b.append("<th>Genre</th>");
        b.append("<th>Duration</th>");
        b.append("<th>Artists</th>");
        b.append("</tr></thead>");
        b.append("<tbody>");

        for(Track t : tracks){

            b.append("<tr>");
            b.append("<td>" + t.getId() + "</td>");
            b.append("<td>" + t.getTitle() + "</td>");
            b.append("<td>" + t.getGenre() + "</td>");
            b.append("<td>" + t.getDuration() + "</td>");

            b.append("<td>");
            if(t.getArtists().isEmpty()){
                b.append("No artists");
            } else {
                for(var a : t.getArtists()){
                    b.append(a.getName() + "<br>");
                }
            }
            b.append("</td>");

            b.append("</tr>");
        }

        b.append("</tbody></table>");

        return b.toString();
    }
}
