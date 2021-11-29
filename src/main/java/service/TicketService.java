package service;

import model.TicketOutlook;

import java.io.IOException;

public interface TicketService {

    public TicketOutlook getAllTickets() throws IOException, InterruptedException;

    public TicketOutlook getTicketById(int id) throws IOException, InterruptedException;

    public TicketOutlook getNextPage(TicketOutlook ticketViewerObj) throws IOException, InterruptedException;

    public TicketOutlook getPreviousPage(TicketOutlook ticketViewerObj) throws IOException, InterruptedException;
}
