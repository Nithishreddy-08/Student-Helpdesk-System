package com.shadowfox.helpdesk.service;

import com.shadowfox.helpdesk.model.Ticket;

import com.shadowfox.helpdesk.model.TicketStatus;

import java.util.ArrayList;
public class TicketService {

    private ArrayList<Ticket> tickets = new ArrayList<>();

    private int nextTicketId = 1001;


    // CREATE TICKET
    public void createTicket(String title,
                             String description,
                             String category,
                             String priority) {

        if (title == null || title.trim().isEmpty()) {
            System.out.println("Ticket title cannot be empty.");
            return;
        }

        Ticket ticket = new Ticket(
                nextTicketId,
                title,
                description,
                category,
                priority
        );

        tickets.add(ticket);

        System.out.println("\nTicket created successfully!");
        System.out.println("Ticket ID: " + nextTicketId);

        nextTicketId++;
    }


    // VIEW ALL TICKETS
    public void viewAllTickets() {

        if (tickets.isEmpty()) {
            System.out.println("\nNo tickets available.");
            return;
        }

        System.out.println("\n===== ALL TICKETS =====");

        for (Ticket ticket : tickets) {
            ticket.displayTicket();
        }
    }


    // SEARCH TICKET
    public void searchTicket(int ticketId) {

        for (Ticket ticket : tickets) {

            if (ticket.getTicketId() == ticketId) {

                System.out.println("\nTicket Found!");

                ticket.displayTicket();

                return;
            }
        }


        System.out.println("\nTicket not found.");
    }

    // SEARCH TICKETS BY KEYWORD
    public void searchTicketsByKeyword(String keyword) {

        boolean found = false;

        if (keyword == null || keyword.trim().isEmpty()) {

            System.out.println("Search keyword cannot be empty.");

            return;
        }

        keyword = keyword.trim().toLowerCase();

        System.out.println("\n===== SEARCH RESULTS =====");

        for (Ticket ticket : tickets) {

            if (ticket.getTitle().toLowerCase().contains(keyword)
                    || ticket.getCategory().toLowerCase().contains(keyword)
                    || ticket.getPriority().toLowerCase().contains(keyword)) {

                ticket.displayTicket();

                found = true;
            }
        }

        if (!found) {

            System.out.println(
                    "No tickets found matching: " + keyword
            );
        }
    }

    // UPDATE TICKET
    public void updateTicket(int ticketId,
                             String title,
                             String description,
                             String category,
                             String priority) {

        for (Ticket ticket : tickets) {

            if (ticket.getTicketId() == ticketId) {

                ticket.setTitle(title);
                ticket.setDescription(description);
                ticket.setCategory(category);
                ticket.setPriority(priority);

                ticket.addHistory("Ticket details updated");

                System.out.println();
                System.out.println("Ticket updated successfully.");

                return;
            }
        }

        System.out.println("Ticket not found.");
    }


    // RESOLVE TICKET
    public void resolveTicket(int ticketId) {

        for (Ticket ticket : tickets) {

            if (ticket.getTicketId() == ticketId) {

                if (ticket.getStatus() == TicketStatus.RESOLVED) {

                    System.out.println("Ticket is already resolved.");

                    return;
                }

                if (ticket.getStatus() == TicketStatus.CLOSED) {

                    System.out.println("Ticket is already closed.");

                    return;
                }

                ticket.setStatus(TicketStatus.RESOLVED);

                ticket.addHistory("Ticket resolved");

                System.out.println("Ticket resolved successfully.");
                return;
            }
        }

        System.out.println("Ticket not found.");
    }

    public void updateStatus(int ticketId, TicketStatus newStatus) {

        for (Ticket ticket : tickets) {

            if (ticket.getTicketId() == ticketId) {

                TicketStatus currentStatus = ticket.getStatus();

                if (currentStatus == TicketStatus.CLOSED) {

                    System.out.println(
                            "Closed tickets cannot be updated."
                    );

                    return;
                }

                if (currentStatus == TicketStatus.OPEN
                        && newStatus == TicketStatus.IN_PROGRESS) {

                    ticket.setStatus(newStatus);
                    ticket.addHistory(
                            "Status changed to " + newStatus
                    );

                    System.out.println(
                            "Ticket status updated to IN_PROGRESS."
                    );

                    return;
                }

                if (currentStatus == TicketStatus.IN_PROGRESS
                        && newStatus == TicketStatus.RESOLVED) {

                    ticket.setStatus(newStatus);
                    ticket.addHistory(
                            "Status changed to " + newStatus
                    );

                    System.out.println(
                            "Ticket status updated to RESOLVED."
                    );

                    return;
                }

                if (currentStatus == TicketStatus.RESOLVED
                        && newStatus == TicketStatus.CLOSED) {

                    ticket.setStatus(newStatus);
                    ticket.addHistory(
                            "Status changed to " + newStatus
                    );

                    System.out.println(
                            "Ticket status updated to CLOSED."
                    );

                    return;
                }

                System.out.println(
                        "Invalid status transition."
                );

                return;
            }
        }

        System.out.println("Ticket not found.");
    }

    public boolean ticketExists(int ticketId) {

        for (Ticket ticket : tickets) {

            if (ticket.getTicketId() == ticketId) {
                return true;
            }
        }

        return false;
    }

    // DELETE TICKET
    public void deleteTicket(int ticketId) {

        for (Ticket ticket : tickets) {

            if (ticket.getTicketId() == ticketId) {

                if (ticket.getStatus() == TicketStatus.CLOSED) {

                    System.out.println(
                            "Closed tickets cannot be deleted."
                    );

                    return;
                }

                tickets.removeIf(
                        t -> t.getTicketId() == ticketId
                );

                System.out.println(
                        "Ticket deleted successfully."
                );

                return;
            }
        }

        System.out.println("Ticket not found.");
    }

    // FILTER TICKETS BY STATUS
    public void viewTicketsByStatus(TicketStatus status) {

        boolean found = false;

        System.out.println("\n===== " + status + " TICKETS =====");

        for (Ticket ticket : tickets) {

            if (ticket.getStatus() == status) {

                ticket.displayTicket();

                found = true;
            }
        }

        if (!found) {

            System.out.println(
                    "No tickets found with status: " + status
            );
        }
    }

    public void showTicketStatistics() {

        int totalTickets = tickets.size();

        int openTickets = 0;
        int inProgressTickets = 0;
        int resolvedTickets = 0;
        int closedTickets = 0;

        int lowPriority = 0;
        int mediumPriority = 0;
        int highPriority = 0;

        for (Ticket ticket : tickets) {

            // Count ticket status
            switch (ticket.getStatus()) {

                case OPEN:
                    openTickets++;
                    break;

                case IN_PROGRESS:
                    inProgressTickets++;
                    break;

                case RESOLVED:
                    resolvedTickets++;
                    break;

                case CLOSED:
                    closedTickets++;
                    break;
            }

            // Count ticket priority
            switch (ticket.getPriority().toLowerCase()) {

                case "low":
                    lowPriority++;
                    break;

                case "medium":
                    mediumPriority++;
                    break;

                case "high":
                    highPriority++;
                    break;
            }
        }

        System.out.println();
        System.out.println("=================================");
        System.out.println("       TICKET STATISTICS");
        System.out.println("=================================");

        System.out.println("Total Tickets     : " + totalTickets);

        System.out.println();

        System.out.println("OPEN              : " + openTickets);
        System.out.println("IN_PROGRESS       : " + inProgressTickets);
        System.out.println("RESOLVED          : " + resolvedTickets);
        System.out.println("CLOSED            : " + closedTickets);

        System.out.println();

        System.out.println("LOW PRIORITY      : " + lowPriority);
        System.out.println("MEDIUM PRIORITY   : " + mediumPriority);
        System.out.println("HIGH PRIORITY     : " + highPriority);

        System.out.println("=================================");
    }

    public void viewTicketsByPriority() {

        System.out.println();
        System.out.println("=================================");
        System.out.println("       TICKETS BY PRIORITY");
        System.out.println("=================================");

        boolean found = false;

        // HIGH PRIORITY
        System.out.println("\n--- HIGH PRIORITY ---");

        for (Ticket ticket : tickets) {

            if (ticket.getPriority().equalsIgnoreCase("High")) {

                System.out.println(ticket);
                found = true;
            }
        }

        // MEDIUM PRIORITY
        System.out.println("\n--- MEDIUM PRIORITY ---");

        for (Ticket ticket : tickets) {

            if (ticket.getPriority().equalsIgnoreCase("Medium")) {

                System.out.println(ticket);
                found = true;
            }
        }

        // LOW PRIORITY
        System.out.println("\n--- LOW PRIORITY ---");

        for (Ticket ticket : tickets) {

            if (ticket.getPriority().equalsIgnoreCase("Low")) {

                System.out.println(ticket);
                found = true;
            }
        }

        if (!found) {

            System.out.println("\nNo tickets available.");
        }

        System.out.println("=================================");
    }

    public void assignTicket(int ticketId, String staffName) {

        for (Ticket ticket : tickets) {

            if (ticket.getTicketId() == ticketId) {

                ticket.setAssignedTo(staffName);

                ticket.addHistory(
                        "Ticket assigned to " + staffName
                );

                System.out.println();
                System.out.println("Ticket assigned successfully!");
                System.out.println("Ticket ID  : " + ticketId);
                System.out.println("Assigned To: " + staffName);

                return;
            }
        }

        System.out.println("Ticket not found.");
    }

    public void viewTicketHistory(int ticketId) {

        for (Ticket ticket : tickets) {

            if (ticket.getTicketId() == ticketId) {

                ticket.displayHistory();

                return;
            }
        }

        System.out.println("Ticket not found.");
    }

    public void showHelpdeskReport() {

        int totalTickets = tickets.size();

        int openTickets = 0;
        int inProgressTickets = 0;
        int resolvedTickets = 0;
        int closedTickets = 0;

        int highPriority = 0;
        int mediumPriority = 0;
        int lowPriority = 0;

        int assignedTickets = 0;
        int unassignedTickets = 0;

        for (Ticket ticket : tickets) {

            // Count status
            switch (ticket.getStatus()) {

                case OPEN:
                    openTickets++;
                    break;

                case IN_PROGRESS:
                    inProgressTickets++;
                    break;

                case RESOLVED:
                    resolvedTickets++;
                    break;

                case CLOSED:
                    closedTickets++;
                    break;
            }

            // Count priority
            switch (ticket.getPriority().toLowerCase()) {

                case "high":
                    highPriority++;
                    break;

                case "medium":
                    mediumPriority++;
                    break;

                case "low":
                    lowPriority++;
                    break;
            }

            // Count assignment
            if (ticket.getAssignedTo().equalsIgnoreCase("Not Assigned")) {
                unassignedTickets++;
            } else {
                assignedTickets++;
            }
        }

        System.out.println();
        System.out.println("========================================");
        System.out.println("           HELPDESK REPORT");
        System.out.println("========================================");

        System.out.println();
        System.out.println("Total Tickets       : " + totalTickets);

        System.out.println();
        System.out.println("----- STATUS -----");

        System.out.println("OPEN                : " + openTickets);
        System.out.println("IN_PROGRESS         : " + inProgressTickets);
        System.out.println("RESOLVED            : " + resolvedTickets);
        System.out.println("CLOSED              : " + closedTickets);

        System.out.println();
        System.out.println("----- PRIORITY -----");

        System.out.println("HIGH                : " + highPriority);
        System.out.println("MEDIUM              : " + mediumPriority);
        System.out.println("LOW                 : " + lowPriority);

        System.out.println();
        System.out.println("----- ASSIGNMENT -----");

        System.out.println("Assigned Tickets    : " + assignedTickets);
        System.out.println("Unassigned Tickets  : " + unassignedTickets);

        System.out.println();
        System.out.println("========================================");
    }
}