import React, { useEffect, useState } from "react";
import axios from "axios";


const TicketList = () => {
  const [tickets, setTickets] = useState([]);

  useEffect(() => {
    axios
      .get("http://localhost:8080/api/tickets")
      .then((response) => setTickets(response.data))
      .catch((error) => console.error(error));
  }, []);

  const handleDelete = (id) => {
    axios
      .delete(`http://localhost:8080/api/tickets/${id}`)
      .then(() => setTickets(tickets.filter((ticket) => ticket.id !== id)))
      .catch((error) => console.error(error));
  };

  return (
    <div>
      <h2>Ticket List</h2>
      <ul>
        {tickets.map((ticket) => (
          <li key={ticket.id}>
            {ticket.ticketName} - {ticket.price} - {ticket.ticketCount} left
            <button onClick={() => handleDelete(ticket.id)}>Delete</button>
          </li>
        ))}
      </ul>
    </div>
  );
};

export default TicketList;
