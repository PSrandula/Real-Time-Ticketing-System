import React, { useState } from "react";
import { getGreeting, submitData } from "../../api";
import axios from "axios";
import "./AddCustomer.css";

const AddCustomer = () => {
  const [name, setName] = useState("");
  const [email, setEmail] = useState("");

  const handleSubmit = (e) => {
    e.preventDefault();
    axios
      .post("http://localhost:8080/api/customers", { name, email })
      .then(() => alert("Customer added successfully"))
      .catch((error) => console.error(error));
  };

  return (
    <form className="customerForm" onSubmit={handleSubmit}>
      <h2>Add Customer</h2>
      <input
        type="text"
        placeholder="Enter Name"
        value={name}
        onChange={(e) => setName(e.target.value)}
      />
      <input
        type="email"
        placeholder="Enter Email"
        value={email}
        onChange={(e) => setEmail(e.target.value)}
      />
      <button type="submit">Add Customer</button>
    </form>
  );
};

export default AddCustomer;
