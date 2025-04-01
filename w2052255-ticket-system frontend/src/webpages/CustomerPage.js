import React, { useState } from "react";
import { getGreeting, submitData } from "../api";
import AddCustomer from "../components/Customer/AddCustomer";
import CustomerList from "../components/Customer/CustomerList";
import CustomerTickets from "../components/Customer/CustomerTickets";
import "./CustomerPage.css";
const CustomerPage = () => {
  const [selectedCustomerId, setSelectedCustomerId] = useState(null);

  const handleCustomerSelect = (id) => {
    setSelectedCustomerId(id);
  };

  return (
    <div className="CustomerDesc">
      <p>
        <h2 className="title">Customer Page</h2> The Customer Page in the Real-Time Ticketing System 
        provides a user-friendly interface for efficient customer management. It enables you to add 
        new customers by entering their name and email and offers a comprehensive view of the existing 
        customer database. This page simplifies customer administration, ensuring quick access to customer 
        information and streamlined management processes.
      </p>

      <AddCustomer />

      <CustomerList onCustomerSelect={handleCustomerSelect} />

      {selectedCustomerId && (
        <CustomerTickets customerId={selectedCustomerId} />
      )}
    </div>
  );
};

export default CustomerPage;
