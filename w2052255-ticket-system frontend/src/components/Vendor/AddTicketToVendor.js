import React, { useState, useEffect } from "react";
import axios from "axios";
import "./AddTicketToVendor.css";

function AddTicketToVendor() {
  const [vendorId, setVendorId] = useState("");
  const [ticketName, setTicketName] = useState("");
  const [ticketPrice, setTicketPrice] = useState("");
  const [ticketCount, setTicketCount] = useState("");
  const [vendors, setVendors] = useState([]);

  useEffect(() => {
    axios
      .get("http://localhost:8080/api/vendors")
      .then((response) => {
        setVendors(response.data);
      })
      .catch((error) => {
        console.error("Error fetching vendors:", error);
      });
  }, []);

  const handleSubmit = (e) => {
    e.preventDefault();

    const newTicket = { ticketName, ticketPrice, ticketCount };

    axios
      .post(`http://localhost:8080/api/vendors/${vendorId}/tickets`, newTicket)
      .then((response) => {
        alert("Ticket added successfully!");
        setTicketName("");
        setTicketPrice("");
        setTicketCount("");
      })
      .catch((error) => {
        console.error("Error adding ticket:", error);
      });
  };

  return (
    <div className="addTicket">
      <form onSubmit={handleSubmit}>
        <h2>Add Ticket to Vendor</h2>
        <select
          value={vendorId}
          onChange={(e) => setVendorId(e.target.value)}
          required
        >
          <option value="">Select Vendor</option>
          {vendors.map((vendor) => (
            <option key={vendor.id} value={vendor.id}>
              {vendor.vendorName}
            </option>
          ))}
        </select>
        <input
          type="text"
          placeholder="Ticket Name"
          value={ticketName}
          onChange={(e) => setTicketName(e.target.value)}
          required
        />
        <input
          type="number"
          placeholder="Ticket Price"
          value={ticketPrice}
          onChange={(e) => setTicketPrice(e.target.value)}
          required
        />
        <input
          type="number"
          placeholder="Ticket Count"
          value={ticketCount}
          onChange={(e) => setTicketCount(e.target.value)}
          required
        />
        <button type="submit">Add Ticket</button>
      </form>
    </div>
  );
}

export default AddTicketToVendor;
