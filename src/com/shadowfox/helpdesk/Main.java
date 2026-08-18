import com.shadowfox.helpdesk.model.TicketStatus;
import com.shadowfox.helpdesk.service.TicketService;
import com.shadowfox.helpdesk.util.InputValidator;

import java.util.Scanner;
public class Main{

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        TicketService ticketService = new TicketService();

        boolean running = true;

        while (running) {

            System.out.println();
            System.out.println("=================================");
            System.out.println("      STUDENT HELPDESK SYSTEM");
            System.out.println("=================================");
            System.out.println("1. Create Ticket");
            System.out.println("2. View All Tickets");
            System.out.println("3. Search Ticket");
            System.out.println("4. Update Ticket");
            System.out.println("5. Update Ticket Status");
            System.out.println("6. Resolve Ticket");
            System.out.println("7. Delete Ticket");
            System.out.println("8. Filter Tickets by Status");
            System.out.println("9. Ticket Statistics");
            System.out.println("10. View Tickets by Priority");
            System.out.println("11. Assign Ticket");
            System.out.println("12. View Ticket History");
            System.out.println("13. Helpdesk Report");
            System.out.println("14. Exit");
            System.out.println("=================================");

            System.out.print("Enter your choice: ");

            String choice = scanner.nextLine();

            switch (choice) {

                // CREATE TICKET
                case "1":

                    System.out.println();
                    System.out.println("===== CREATE TICKET =====");

                    System.out.print("Enter title: ");
                    String title = scanner.nextLine();

                    if (InputValidator.isEmpty(title)) {
                        System.out.println("Title cannot be empty.");
                        break;
                    }

                    System.out.print("Enter description: ");
                    String description = scanner.nextLine();

                    System.out.print(
                            "Enter category (Technical/Academic/General): "
                    );

                    String category = scanner.nextLine();

                    if (!InputValidator.isValidCategory(category)) {
                        System.out.println("Invalid category.");
                        break;
                    }

                    System.out.print(
                            "Enter priority (Low/Medium/High): "
                    );

                    String priority = scanner.nextLine();

                    if (!InputValidator.isValidPriority(priority)) {
                        System.out.println("Invalid priority.");
                        break;
                    }

                    ticketService.createTicket(
                            title,
                            description,
                            category,
                            priority
                    );

                    break;


                // VIEW ALL TICKETS
                case "2":

                    ticketService.viewAllTickets();

                    break;


                // SEARCH TICKET
                case "3":

                    System.out.println("\n===== SEARCH TICKET =====");

                    System.out.println("1. Search by Ticket ID");
                    System.out.println("2. Search by Keyword");

                    System.out.print("Choose search option: ");

                    String searchChoice = scanner.nextLine();

                    switch (searchChoice) {

                        case "1":

                            int searchId = InputValidator.readInteger(
                                    scanner,
                                    "Enter Ticket ID: "
                            );

                            ticketService.searchTicket(searchId);

                            break;


                        case "2":

                            System.out.print(
                                    "Enter title, category, or priority: "
                            );

                            String keyword = scanner.nextLine();

                            ticketService.searchTicketsByKeyword(keyword);

                            break;


                        default:

                            System.out.println(
                                    "Invalid search option."
                            );
                    }

                    break;

                // UPDATE TICKET
                case "4":

                    int updateId = InputValidator.readInteger(
                            scanner,
                            "Enter Ticket ID to update: "
                    );

                    // Check whether ticket exists
                    if (!ticketService.ticketExists(updateId)) {

                        System.out.println("Ticket not found.");

                        break;
                    }

                    System.out.println(
                            "\nTicket found. Enter new details."
                    );

                    System.out.print("Enter new title: ");

                    String newTitle = scanner.nextLine();

                    if (InputValidator.isEmpty(newTitle)) {

                        System.out.println("Title cannot be empty.");

                        break;
                    }

                    System.out.print("Enter new description: ");

                    String newDescription = scanner.nextLine();

                    System.out.print("Enter new category: ");

                    String newCategory = scanner.nextLine();

                    if (!InputValidator.isValidCategory(newCategory)) {

                        System.out.println("Invalid category.");

                        break;
                    }

                    System.out.print("Enter new priority: ");

                    String newPriority = scanner.nextLine();

                    if (!InputValidator.isValidPriority(newPriority)) {

                        System.out.println("Invalid priority.");

                        break;
                    }

                    ticketService.updateTicket(
                            updateId,
                            newTitle,
                            newDescription,
                            newCategory,
                            newPriority
                    );

                    break;

                case "5":

                    int statusId = InputValidator.readInteger(
                            scanner,
                            "Enter Ticket ID: "
                    );

                    // Check whether ticket exists
                    if (!ticketService.ticketExists(statusId)) {

                        System.out.println("Ticket not found.");

                        break;
                    }

                    System.out.println("\n===== UPDATE TICKET STATUS =====");

                    System.out.println("1. OPEN → IN_PROGRESS");
                    System.out.println("2. IN_PROGRESS → RESOLVED");
                    System.out.println("3. RESOLVED → CLOSED");

                    System.out.print("Choose status transition: ");

                    String statusChoice = scanner.nextLine();

                    switch (statusChoice) {

                        case "1":

                            ticketService.updateStatus(
                                    statusId,
                                    TicketStatus.IN_PROGRESS
                            );

                            break;

                        case "2":

                            ticketService.updateStatus(
                                    statusId,
                                    TicketStatus.RESOLVED
                            );

                            break;

                        case "3":

                            ticketService.updateStatus(
                                    statusId,
                                    TicketStatus.CLOSED
                            );

                            break;

                        default:

                            System.out.println(
                                    "Invalid status option."
                            );
                    }

                    break;

                // RESOLVE TICKET
                case "6":

                    int resolveId = InputValidator.readInteger(
                            scanner,
                            "Enter Ticket ID to resolve: "
                    );

                    ticketService.resolveTicket(resolveId);

                    break;
                case "7":

                    int deleteId = InputValidator.readInteger(
                            scanner,
                            "Enter Ticket ID to delete: "
                    );

                    // Check whether ticket exists
                    if (!ticketService.ticketExists(deleteId)) {

                        System.out.println("Ticket not found.");

                        break;
                    }

                    System.out.print(
                            "Are you sure you want to delete this ticket? (yes/no): "
                    );

                    String confirmation = scanner.nextLine();

                    if (confirmation.equalsIgnoreCase("yes")) {

                        ticketService.deleteTicket(deleteId);

                    } else {

                        System.out.println(
                                "Ticket deletion cancelled."
                        );
                    }

                    break;

                case "8":

                    System.out.println("\n===== FILTER TICKETS =====");

                    System.out.println("1. OPEN");
                    System.out.println("2. IN_PROGRESS");
                    System.out.println("3. RESOLVED");
                    System.out.println("4. CLOSED");

                    System.out.print("Choose status: ");

                    String filterChoice = scanner.nextLine();

                    switch (filterChoice) {

                        case "1":

                            ticketService.viewTicketsByStatus(
                                    TicketStatus.OPEN
                            );

                            break;

                        case "2":

                            ticketService.viewTicketsByStatus(
                                    TicketStatus.IN_PROGRESS
                            );

                            break;

                        case "3":

                            ticketService.viewTicketsByStatus(
                                    TicketStatus.RESOLVED
                            );

                            break;

                        case "4":

                            ticketService.viewTicketsByStatus(
                                    TicketStatus.CLOSED
                            );

                            break;

                        default:

                            System.out.println(
                                    "Invalid status option."
                            );
                    }

                    break;

                // TICKET STATISTICS
                case "9":

                    ticketService.showTicketStatistics();

                    break;


                // VIEW TICKETS BY PRIORITY
                case "10":

                    ticketService.viewTicketsByPriority();

                    break;


// ASSIGN TICKET
                case "11":

                    int assignId = InputValidator.readInteger(
                            scanner,
                            "Enter Ticket ID to assign: "
                    );

                    // Check whether ticket exists
                    if (!ticketService.ticketExists(assignId)) {

                        System.out.println("Ticket not found.");

                        break;
                    }

                    System.out.print("Enter support staff name: ");

                    String staffName = scanner.nextLine();

                    if (InputValidator.isEmpty(staffName)) {

                        System.out.println("Staff name cannot be empty.");

                        break;
                    }

                    ticketService.assignTicket(assignId, staffName);

                    break;
// VIEW TICKET HISTORY
                case "12":

                    int historyId = InputValidator.readInteger(
                            scanner,
                            "Enter Ticket ID to view history: "
                    );

                    // Check whether ticket exists
                    if (!ticketService.ticketExists(historyId)) {

                        System.out.println("Ticket not found.");

                        break;
                    }

                    ticketService.viewTicketHistory(historyId);

                    break;

                // HELPDESK REPORT
                case "13":

                    ticketService.showHelpdeskReport();

                    break;


                // EXIT
                case "14":

                    running = false;

                    System.out.println();
                    System.out.println(
                            "Thank you for using Student Helpdesk System!"
                    );

                    break;


                // INVALID MENU OPTION
                default:

                    System.out.println(
                            "Invalid menu choice. Please try again."
                    );
            }
        }

        scanner.close();
    }
}