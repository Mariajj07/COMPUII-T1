package com.example.webapp.views;

import com.example.webapp.model.Artist;

import java.util.List;

public class ArtistView {

    public String listArtists(List<Artist> artists){

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
        b.append("<th>Name</th>");
        b.append("<th>Nationality</th>");
        b.append("<th>Tracks</th>");
        b.append("</tr></thead>");
        b.append("<tbody>");

        for(Artist a : artists){

            b.append("<tr>");
            b.append("<td>" + a.getId() + "</td>");
            b.append("<td>" + a.getName() + "</td>");
            b.append("<td>" + a.getNationality() + "</td>");

            b.append("<td>");
            if(a.getTracks().isEmpty()){
                b.append("No tracks");
            } else {
                for(var t : a.getTracks()){
                    b.append(t.getTitle() + "<br>");
                }
            }
            b.append("</td>");

            b.append("</tr>");
        }

        b.append("</tbody></table>");

        return b.toString();
    }
}
