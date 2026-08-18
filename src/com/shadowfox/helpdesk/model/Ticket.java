package com.shadowfox.helpdesk.model;
import java.util.ArrayList;
import java.util.List;

public class Ticket {

    private int ticketId;
    private String title;
    private String description;
    private String category;
    private String priority;
    private TicketStatus status;
    private String assignedTo;
    private List<String> history;
    public Ticket(int ticketId, String title, String description,
                  String category, String priority) {

        this.ticketId = ticketId;
        this.title = title;
        this.description = description;
        this.category = category;
        this.priority = priority;
        this.status = TicketStatus.OPEN;
        this.assignedTo = "Not Assigned";

        this.history = new ArrayList<>();
        this.history.add("Ticket created");
    }


    public int getTicketId() {
        return ticketId;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getCategory() {
        return category;
    }

    public String getPriority() {
        return priority;
    }

    public TicketStatus getStatus() {
        return status;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public void setStatus(TicketStatus status) {
        this.status = status;
    }

    public String getAssignedTo() {
        return assignedTo;
    }

    public void setAssignedTo(String assignedTo) {
        this.assignedTo = assignedTo;
    }
    public List<String> getHistory() {
        return history;
    }

    public void addHistory(String action) {
        history.add(action);
    }

    public void displayTicket() {

        System.out.println("----------------------------------");
        System.out.println("Ticket ID   : " + ticketId);
        System.out.println("Title       : " + title);
        System.out.println("Description : " + description);
        System.out.println("Category    : " + category);
        System.out.println("Priority    : " + priority);
        System.out.println("Status      : " + status);
        System.out.println("Assigned To : " + assignedTo);
        System.out.println("----------------------------------");
    }
    public void displayHistory() {

        System.out.println();
        System.out.println("=================================");
        System.out.println("        TICKET HISTORY");
        System.out.println("=================================");

        System.out.println("Ticket ID: " + ticketId);

        if (history.isEmpty()) {

            System.out.println("No history available.");

        } else {

            for (int i = 0; i < history.size(); i++) {

                System.out.println((i + 1) + ". " + history.get(i));
            }
        }

        System.out.println("=================================");
    }
    @Override
    public String toString() {
        return "Ticket ID       : " + ticketId +
                "\nTitle           : " + title +
                "\nDescription     : " + description +
                "\nCategory        : " + category +
                "\nPriority        : " + priority +
                "\nStatus          : " + status +
                "\nAssigned To     : " + assignedTo;
    }
}