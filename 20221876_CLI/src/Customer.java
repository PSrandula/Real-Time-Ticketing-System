/**
 * Represents a customer attempting to purchase tickets from the ticket pool.
 * Implements the Runnable interface to allow execution in a separate thread.
 */
public class Customer implements Runnable {
    private TicketPool ticketPool; // The ticket pool from which tickets are retrieved
    private int customerRetrievalRate; // Rate at which tickets are purchased (in seconds)
    private int quantity; // Number of tickets the customer wants to purchase

    /**
     * Constructs a Customer with the specified parameters.
     *
     * @param ticketPool           the ticket pool from which tickets are retrieved
     * @param customerRetrievalRate the interval between ticket purchases (in seconds)
     * @param quantity             the total number of tickets the customer intends to buy
     */
    public Customer(TicketPool ticketPool, int customerRetrievalRate, int quantity) {
        this.ticketPool = ticketPool;
        this.customerRetrievalRate = customerRetrievalRate;
        this.quantity = quantity;
    }

    /**
     * Runs the ticket purchasing process in a separate thread. The customer
     * attempts to purchase the specified number of tickets at the defined rate.
     */
    @Override
    public void run() {
        for (int i = 0; i < quantity; i++) {
            // Attempt to purchase a ticket from the pool
            Ticket ticket = ticketPool.buyTicket();
            // Log the purchased ticket details and the customer thread name
            System.out.println("Ticket is - " + ticket + " - Customer name is - " + Thread.currentThread().getName());
            // Pause for the defined retrieval rate before the next purchase
            try {
                Thread.sleep(customerRetrievalRate * 1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
