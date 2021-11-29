package service;

import model.TicketOutlook;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.io.IOException;
import java.net.ConnectException;

import com.fasterxml.jackson.databind.ObjectMapper;

import utils.Finals;
import utils.Utils;

public class TicketServiceImplementation implements TicketService {

    public TicketOutlook getAllTickets() throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        String encodedCredential = Utils.getEncodedCredential();

        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .header("Authorization", "Basic " + encodedCredential)
                .header("Accept", "application/json")
                .uri(URI.create(Finals.API_URL + ".json?page[size]=" + Finals.PAGE_LENGTH))
                .build();

        HttpResponse<String> response;
        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (ConnectException e) {
            throw new ConnectException();
        }

        ObjectMapper mapper = new ObjectMapper();
        TicketOutlook ticketOutlook = mapper.readValue(response.body(), TicketOutlook.class);
        ticketOutlook.pageCount = 1;
        return ticketOutlook;
    }

    public TicketOutlook getTicketById(int id) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        String encodedCredential = Utils.getEncodedCredential();

        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .header("Authorization", "Basic " + encodedCredential)
                .header("Accept", "application/json")
                .uri(URI.create(Finals.API_URL + "/show_many.json?ids=" + id))
                .build();

        HttpResponse<String> response;
        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (ConnectException e) {
            throw new ConnectException();
        }

        ObjectMapper mapper = new ObjectMapper();
        TicketOutlook ticketOutlook = mapper.readValue(response.body(), TicketOutlook.class);
        ticketOutlook.pageCount = 1;

        if (ticketOutlook.tickets.length == 0) {
            throw new IOException();
        }

        return ticketOutlook;

    }

    public TicketOutlook getNextPage(TicketOutlook ticketOutlookObj) throws IOException, InterruptedException {
        if (ticketOutlookObj.pagination.has_more) {
            HttpClient client = HttpClient.newHttpClient();
            String encodedCredential = Utils.getEncodedCredential();

            HttpRequest request = HttpRequest.newBuilder()
                    .GET()
                    .header("Authorization", "Basic " + encodedCredential)
                    .header("Accept", "application/json")
                    .uri(URI.create(Finals.API_URL + ".json?page[size]=" + Finals.PAGE_LENGTH + "&page[after]=" + ticketOutlookObj.pagination.after_cursor))
                    .build();

            HttpResponse<String> response;
            try {
                response = client.send(request, HttpResponse.BodyHandlers.ofString());
            } catch (ConnectException e) {
                throw new ConnectException();
            }

            ObjectMapper mapper = new ObjectMapper();
            TicketOutlook ticketOutlook = mapper.readValue(response.body(), TicketOutlook.class);

            if (ticketOutlook.tickets.length == 0) {
                return ticketOutlookObj;
            } else {
                ticketOutlook.pageCount = ticketOutlookObj.pageCount + 1;
                return ticketOutlook;
            }
        }
        return ticketOutlookObj;
    }

    public TicketOutlook getPreviousPage(TicketOutlook ticketOutlookObj) throws IOException, InterruptedException {
        if (ticketOutlookObj.pageCount > 1) {
            HttpClient client = HttpClient.newHttpClient();
            String encodedCredential = Utils.getEncodedCredential();

            HttpRequest request = HttpRequest.newBuilder()
                    .GET()
                    .header("Authorization", "Basic " + encodedCredential)
                    .header("Accept", "application/json")
                    .uri(URI.create(Finals.API_URL + ".json?page[size]=" + Finals.PAGE_LENGTH + "&page[before]=" + ticketOutlookObj.pagination.before_cursor))
                    .build();

            HttpResponse<String> response;
            try {
                response = client.send(request, HttpResponse.BodyHandlers.ofString());
            } catch (ConnectException e) {
                throw new ConnectException();
            }

            ObjectMapper mapper = new ObjectMapper();
            TicketOutlook ticketOutlook = mapper.readValue(response.body(), TicketOutlook.class);
            ticketOutlook.pageCount = ticketOutlookObj.pageCount - 1;
            return ticketOutlook;
        }
        return ticketOutlookObj;
    }
}
