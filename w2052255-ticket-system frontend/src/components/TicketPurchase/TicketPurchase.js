import React, { useEffect, useState } from "react";
import axios from "axios";
import "./TicketPurchase.css";

const TicketPurchase = () => {
  const [customers, setCustomers] = useState([]);
  const [tickets, setTickets] = useState([]);
  const [customerId, setCustomerId] = useState("");
  const [ticketId, setTicketId] = useState("");

  useEffect(() => {
    axios
      .get("http://localhost:8080/api/customers")
      .then((response) => setCustomers(response.data))
      .catch((error) => console.error(error));

    axios
      .get("http://localhost:8080/api/tickets")
      .then((response) => setTickets(response.data))
      .catch((error) => console.error(error));
  }, []);

  const handlePurchase = () => {
    axios
      .post(
        `http://localhost:8080/api/customers/${customerId}/tickets/${ticketId}`
      )
      .then(() => alert("Ticket purchased successfully"))
      .catch((error) => console.error(error));
  };

  return (
    <div className="PurchaseTic">
      <h2>Purchase Ticket</h2>
      <select onChange={(e) => setCustomerId(e.target.value)}>
        <option value="">Select Customer</option>
        {customers.map((customer) => (
          <option key={customer.id} value={customer.id}>
            {customer.name}
          </option>
        ))}
      </select>
      <select onChange={(e) => setTicketId(e.target.value)}>
        <option value="">Select Ticket</option>
        {tickets.map((ticket) => (
          <option key={ticket.id} value={ticket.id}>
            {ticket.ticketName}
          </option>
        ))}
      </select>
      <button onClick={handlePurchase}>Buy Ticket</button>
    </div>
  );
};

export default TicketPurchase;
