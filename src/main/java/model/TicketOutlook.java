package model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TicketOutlook {

    public Ticket[] tickets;
    public Pagination pagination;
    public PagePointer links;
    public int pageCount;

    public TicketOutlook() {
        super();
    }

    public TicketOutlook(Ticket[] tickets, Pagination pagination, PagePointer links, int pageCount) {
        this.tickets = tickets;
        this.pagination = pagination;
        this.links = links;
        this.pageCount = pageCount;
    }

    public Pagination getMeta() {
        return pagination;
    }

    public void setMeta(Pagination meta) {
        this.pagination = meta;
    }

    public PagePointer getLinks() {
        return links;
    }

    public void setLinks(PagePointer links) {
        this.links = links;
    }

    public Ticket[] getTickets() {
        return tickets;
    }

    public void setTickets(Ticket[] tickets) {
        this.tickets = tickets;
    }

    @Override
    public String toString() {
        String ticketStr = "\n!!! TICKET(S) LIST !!!\n";
        if (tickets != null && tickets.length > 0) {
            for (Ticket ticket : tickets) {
                if (ticket != null) {
                    ticketStr = ticketStr + ticket.toString() + "\n";
                }
            }
        }
        ticketStr = ticketStr + "PAGE: " + pageCount + "\n";
        return ticketStr;
    }

    public String toStringTicket() {
        String ticketStr = "\n!!! TICKET DETAILS !!!\n";
        if (tickets != null && tickets.length > 0) {
            Ticket ticket = tickets[0];
            ticketStr = ticketStr + ticket.ticketDetailsById() + "\n";
        }
        return ticketStr;
    }
}
