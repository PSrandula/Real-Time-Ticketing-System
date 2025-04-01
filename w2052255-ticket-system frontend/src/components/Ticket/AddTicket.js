import React, { useState } from "react";
import axios from "axios";
import "./AddTicket.css";

const AddTicket = () => {
  const [ticketName, setTicketName] = useState("");
  const [price, setPrice] = useState("");
  const [ticketCount, setTicketCount] = useState("");

  const handleSubmit = (e) => {
    e.preventDefault();
    axios
      .post("http://localhost:8080/api/tickets", {
        ticketName,
        price,
        ticketCount,
      })
      .then(() => alert("Ticket added successfully"))
      .catch((error) => console.error(error));
  };

  return (
    <form onSubmit={handleSubmit}>
      <h2>Add Ticket</h2>
      <input
        type="text"
        placeholder="Ticket Name"
        value={ticketName}
        onChange={(e) => setTicketName(e.target.value)}
      />
      <input
        type="number"
        placeholder="Price"
        value={price}
        onChange={(e) => setPrice(e.target.value)}
      />
      <input
        type="number"
        placeholder="Ticket Count"
        value={ticketCount}
        onChange={(e) => setTicketCount(e.target.value)}
      />
      <button type="submit">Add Ticket</button>
    </form>
  );
};

export default AddTicket;
