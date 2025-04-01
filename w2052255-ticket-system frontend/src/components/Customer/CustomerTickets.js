import React, { useEffect, useState } from "react";
import { getGreeting, submitData } from "../../api";
import axios from "axios";

const CustomerTickets = ({ customerId }) => {
  const [tickets, setTickets] = useState([]);

  useEffect(() => {
    axios
      .get(`http://localhost:8080/api/customers/${customerId}/tickets`)
      .then((response) => setTickets(response.data))
      .catch((error) => console.error(error));
  }, [customerId]);

  return (
    <div className="CustomerTic">
      <h2>Customer Tickets</h2>
      <ul>
        {tickets.map((ticket) => (
          <li key={ticket.id}>
            {ticket.ticketName} - {ticket.price}
          </li>
        ))}
      </ul>
    </div>
  );
};

export default CustomerTickets;
