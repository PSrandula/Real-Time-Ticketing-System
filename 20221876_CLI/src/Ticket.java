import java.math.BigDecimal;

/**
 Represents a Ticket with details such as ID, event name, and ticket price.
 **/
public class Ticket {
    private int ticketId; // Unique identifier for the ticket
    private String eventName; // Name of the event associated with the ticket
    private BigDecimal ticketPrice; // Price of the ticket

    /**
     * Constructs a Ticket with the specified ID, event name, and price.
     *
     * @param ticketId    the unique identifier for the ticket
     * @param eventName   the name of the event
     * @param ticketPrice the price of the ticket
     */
    public Ticket(int ticketId, String eventName, BigDecimal ticketPrice) {
        this.ticketId = ticketId;
        this.eventName = eventName;
        this.ticketPrice = ticketPrice;
    }
    /**
     * Retrieves the ticket ID.
     *
     * @return the unique identifier for the ticket
     */
    public int getTicketId() {
        return ticketId;
    }
    /**
     * Retrieves the event name.
     *
     * @return the name of the event
     */
    public String getEventName() {
        return eventName;
    }
    /**
     * Retrieves the ticket price.
     *
     * @return the price of the ticket
     */
    public BigDecimal getTicketPrice() {
        return ticketPrice;
    }
    /**
     * Returns a string representation of the Ticket object.
     *
     * @return a string containing ticket details
     */
    @Override
    public String toString() {
        return "Ticket{" +
                "ticketId=" + ticketId +
                ", eventName='" + eventName + '\'' +
                ", ticketPrice=" + ticketPrice +
                '}';
    }
}
