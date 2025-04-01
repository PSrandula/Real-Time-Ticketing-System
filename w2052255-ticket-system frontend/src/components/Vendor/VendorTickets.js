import React, { useState, useEffect } from "react";
import axios from "axios";
import "./VendorTickets.css";

function VendorTickets() {
  const [vendorId, setVendorId] = useState("");
  const [vendors, setVendors] = useState([]);
  const [tickets, setTickets] = useState([]);

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

  const handleFetchTickets = () => {
    axios
      .get(`http://localhost:8080/api/vendors/${vendorId}/tickets`)
      .then((response) => {
        setTickets(response.data);
      })
      .catch((error) => {
        console.error("Error fetching tickets:", error);
      });
  };

  return (
    <div className="VendorTickets">
      <h2>Vendor Tickets</h2>
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
      <button onClick={handleFetchTickets}>Fetch Tickets</button>
      <ul>
        {tickets.map((ticket) => (
          <li key={ticket.id}>
            {ticket.ticketName} - {ticket.ticketPrice} - {ticket.ticketCount}
          </li>
        ))}
      </ul>
    </div>
  );
}

export default VendorTickets;
