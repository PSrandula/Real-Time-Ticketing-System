import java.util.LinkedList;
import java.util.Queue;

/**
 * Represents a pool of tickets with a fixed maximum capacity. This class
 * supports adding and purchasing tickets while ensuring thread-safe operations
 * using synchronization.
 */
public class TicketPool {
    private int maxTicketCapacity; // Maximum number of tickets the pool can hold
    private Queue<Ticket> ticketQueue; // Queue to hold tickets in the pool

    /**
     * Constructs a TicketPool with the specified maximum capacity.
     *
     * @param maxTicketCapacity the maximum number of tickets the pool can hold
     */
    public TicketPool(int maxTicketCapacity) {
        this.maxTicketCapacity = maxTicketCapacity;
        this.ticketQueue = new LinkedList<>();
    }

    /**
     * Adds a ticket to the pool. If the pool is full, the thread waits until a spot is available.
     *
     * @param ticket the ticket to be added to the pool
     */
    public synchronized void addTicket(Ticket ticket) {
        while (ticketQueue.size() >= maxTicketCapacity) {
            try {
                wait();  // Wait until there is space in the queue
            } catch (InterruptedException e) {
                throw new RuntimeException(e.getMessage());
            }
        }
        ticketQueue.add(ticket);
        notifyAll(); // Notify waiting threads that a ticket has been added
        System.out.println("Ticket added by - " + Thread.currentThread().getName() + " - current size is - " + ticketQueue.size());
    }

    /**
     * Purchases a ticket from the pool. If the pool is empty, the thread waits until a ticket is available.
     *
     * @return the purchased ticket
     */
    public synchronized Ticket buyTicket() {
        while (ticketQueue.isEmpty()) {
            try {
                wait();  // Wait until there is a ticket in the queue
            } catch (InterruptedException e) {
                throw new RuntimeException(e.getMessage());
            }
        }
        Ticket ticket = ticketQueue.poll(); // Remove the ticket from the queue
        notifyAll();  // Notify waiting threads that a ticket has been removed
        System.out.println("Ticket purchased by - " + Thread.currentThread().getName() + " - current size is - " + ticketQueue.size() + " - Ticket is - " + ticket);
        return ticket;
    }
}
