import model.TicketOutlook;
import service.TicketService;
import service.TicketServiceImplementation;

import java.io.IOException;
import java.net.ConnectException;
import java.util.*;

public class ZCCTicketHelp
{
    public static void main( String[] args ) throws IOException, InterruptedException {

        Scanner scanner = new Scanner(System.in);
        String option;
        TicketOutlook ticketOutlook;
        TicketService ticketService;
        while (true) {
            System.out.println();
            System.out.println();
            System.out.println("ZCC Ticket Help");
            System.out.println("Select an option to continue:");
            System.out.println("1. Display All Tickets");
            System.out.println("2. Display ticket by Id");
            System.out.println("3. Exit");
            System.out.print("Enter here: ");
            option = scanner.nextLine();
            System.out.println();
            System.out.println();
            if (option.equals("1")) {
                try {
                    ticketService = new TicketServiceImplementation();
                    ticketOutlook = ticketService.getAllTickets();
                    System.out.println(ticketOutlook);

                    while (true) {
                        System.out.println();
                        System.out.println();
                        System.out.println("Enter an option number to continue");
                        System.out.println("1. Go back to main menu");
                        System.out.println("2. Previous Records");
                        System.out.println("3. Next Records");
                        System.out.print("Enter here: ");
                        option = scanner.nextLine();
                        System.out.println();
                        System.out.println();

                        if (option.equals("1")) {
                            break;
                        } else if (option.equals("2")) {
                            ticketOutlook = ticketService.getPreviousPage(ticketOutlook);
                            System.out.println(ticketOutlook);

                        } else if (option.equals("3")) {
                            ticketOutlook = ticketService.getNextPage(ticketOutlook);
                            System.out.println(ticketOutlook);
                        } else {
                            System.out.println("Invalid choice! Please select again!");
                        }
                    }
                } catch (ConnectException e) {
                    System.out.println("API not available!");
                }

            } else if (option.equals("2")) {
                try {
                    System.out.print("Enter ticket id: ");
                    option = scanner.nextLine();
                    ticketService = new TicketServiceImplementation();
                    ticketOutlook = ticketService.getTicketById(Integer.parseInt(option));
                    System.out.println(ticketOutlook.toStringTicket());
                } catch (NumberFormatException e) {
                    System.out.println("Invalid Id! Please enter again!");
                } catch (IOException e) {
                    System.out.println("Ticket not Found!");
                }
            } else if (option.equals("3")) break; 
            else System.out.println("Invalid choice! Please select again!");
        }
        scanner.close();
    }
}
