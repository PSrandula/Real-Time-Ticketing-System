import React, { useState } from "react";
import axios from "axios";
import "./AddVendor.css";

function AddVendor() {
  const [vendorName, setVendorName] = useState("");
  const [email, setEmail] = useState("");

  const handleSubmit = (e) => {
    e.preventDefault();

    const newVendor = { vendorName, email };

    axios
      .post("http://localhost:8080/api/vendors", newVendor)
      .then((response) => {
        alert("Vendor added successfully!");
        setVendorName("");
        setEmail("");
      })
      .catch((error) => {
        console.error("Error adding vendor:", error);
      });
  };

  return (
    <div className="addVendor">
      <form onSubmit={handleSubmit}>
        <h2>Add Vendor</h2>
        <input
          type="text"
          placeholder="Vendor Name"
          value={vendorName}
          onChange={(e) => setVendorName(e.target.value)}
          required
        />
        <input
          type="email"
          placeholder="Vendor Email"
          value={email}
          onChange={(e) => setEmail(e.target.value)}
          required
        />
        <button type="submit">Add Vendor</button>
      </form>
    </div>
  );
}

export default AddVendor;
