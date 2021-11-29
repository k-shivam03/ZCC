import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import model.Ticket;
import model.TicketOutlook;
import service.TicketService;
import service.TicketServiceImplementation;

import org.junit.Test;

import java.io.IOException;
import java.util.Arrays;

public class ZCCTicketHelpTest 
{
    /**
     * Test to check if the tickets returned are less than or equal to 25
     */
    @Test
    public void fetchAllTicketsCountTest() throws IOException, InterruptedException {
        TicketService ts = new TicketServiceImplementation();
        TicketOutlook ticketOutlook = ts.getAllTickets();
        assertTrue("getAllTickets() should return a TicketOutlook object with a ticket array of at most 25 tickets", ticketOutlook.tickets.length <= 25);
    }

    /**
     * Test to validate if the TicketOutlook toString() method works with an empty ticket array
     */
    @Test
    public void ticketOutlookToStringEmptyTicketArrayTest() throws IOException, InterruptedException {
        TicketService ts = new TicketServiceImplementation();
        TicketOutlook emptyTicketOutlook = ts.getAllTickets();
        emptyTicketOutlook.tickets = new Ticket[0];
        String expectedString = "\n!!! TICKET(S) LIST !!!\n";
        expectedString = expectedString + "PAGE: " + emptyTicketOutlook.pageCount + "\n";

        assertEquals("toString() with an empty ticket array should display no ticket information", expectedString, emptyTicketOutlook.toString());
    }

    /**
     * Test to ensure TicketOutlook toString() method works with a ticket array containing one single ticket
     */
    @Test
    public void ticketOutlookToStringSingleRecordTest() throws IOException, InterruptedException {
        TicketService ts = new TicketServiceImplementation();
        TicketOutlook ticketOutlook = ts.getTicketById(11);
        String expectedString = "\n!!! TICKET(S) LIST !!!\n";
        expectedString = expectedString + "Ticket ID: 11" + "\n" + "Subject: " + ticketOutlook.tickets[0].getSubject() + "\n" + 
        "Created On: " + ticketOutlook.tickets[0].getCreated_at() + "\n" + "Requested By: " + ticketOutlook.tickets[0].getRequester_id() + "\n\n";
                
        expectedString = expectedString + "PAGE: " + ticketOutlook.pageCount + "\n";

        assertEquals("toString() with one element ticket array should display information of a single ticket in short", expectedString, ticketOutlook.toString());
    }

    /**
     * Test to ensure TicketOutlook toStringTicket() work with an empty ticket array
     */
    @Test
    public void ticketOutlookToStringTicketEmptyRecordTest() throws IOException, InterruptedException {
        TicketService ts = new TicketServiceImplementation();
        TicketOutlook ticketOutlook = ts.getTicketById(120);
        ticketOutlook.tickets = new Ticket[0];
        String expectedString = "\n!!! TICKET DETAILS !!!\n";
        assertEquals("toStringTicket() with an empty ticket array should display no ticket information", expectedString, ticketOutlook.toStringTicket());
    }

    /**
     * Test to validate TicketOutlook toStringTicket() works
     */
    @Test
    public void ticketOutlookToStringTicketTest() throws IOException, InterruptedException {
        TicketService ts = new TicketServiceImplementation();
        TicketOutlook ticketOutlook = ts.getTicketById(10);
        String expectedString = "\n!!! TICKET DETAILS !!!\n";
        expectedString = expectedString + "Ticket ID: 120"  + "\n" + "URL: "+ ticketOutlook.tickets[0].getUrl() + "\n" + "Subject: " + ticketOutlook.tickets[0].getSubject() + "\n" + 
                "Description: " + ticketOutlook.tickets[0].getDescription() + "\n" + "Requested By: " + ticketOutlook.tickets[0].getRequester_id() + "\n" + 
                "Created On: " + ticketOutlook.tickets[0].getCreated_at() + "\n"  + "Assignee ID: " + ticketOutlook.tickets[0].getAssignee_id() + "\n" +
                "Tags: " + Arrays.toString((ticketOutlook.tickets[0].getTags())) + "\n\n";

        assertEquals("toStringTicket() with one element ticket array should display one single ticket information in details", expectedString, ticketOutlook.toStringTicket());
    }
}
