import java.math.BigDecimal;

/**
 * Represents a vendor responsible for releasing tickets to the pool at a defined rate.
 * Implements the Runnable interface to allow execution in a separate thread.
 */
public class Vendor implements Runnable {
    private int totalTickets; // Total number of tickets to be released by this vendor
    private int ticketReleaseRate; // Rate at which tickets are released (in seconds)
    private TicketPool ticketPool; // The pool to which tickets will be added
    private String eventName; // Name of the event for which tickets are being sold
    private BigDecimal ticketPrice; // Price of each ticket

    /**
     * Constructs a Vendor with the specified parameters.
     *
     * @param totalTickets      the total number of tickets to be released
     * @param ticketReleaseRate the interval between ticket releases (in seconds)
     * @param ticketPool        the ticket pool to which tickets will be added
     * @param eventName         the name of the event for which tickets are being released
     * @param ticketPrice       the price of each ticket
     */
    public Vendor(int totalTickets, int ticketReleaseRate, TicketPool ticketPool, String eventName, BigDecimal ticketPrice) {
        this.totalTickets = totalTickets;
        this.ticketReleaseRate = ticketReleaseRate;
        this.ticketPool = ticketPool;
        this.eventName = eventName;
        this.ticketPrice = ticketPrice;
    }

    /**
     * Runs the ticket release process in a separate thread. Tickets are released
     * to the ticket pool at the specified rate until the total number of tickets
     * has been reached.
     */
    @Override
    public void run() {
        // Create a new ticket with the current ID, event name, and price
        for (int i = 1; i <= totalTickets; i++) {
            Ticket ticket = new Ticket(i, eventName, ticketPrice);
            // Add the ticket to the ticket pool
            ticketPool.addTicket(ticket);
            // Pause for the defined release rate before releasing the next ticket
            try {
                Thread.sleep(ticketReleaseRate * 1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
