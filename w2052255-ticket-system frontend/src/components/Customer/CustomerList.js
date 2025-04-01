import React, { useEffect, useState } from "react";
import { getGreeting, submitData } from "../../api";
import axios from "axios";
import "./CustomerList.css";

const CustomerList = () => {
  const [customers, setCustomers] = useState([]);

  useEffect(() => {
    axios
      .get("http://localhost:8080/api/customers")
      .then((response) => setCustomers(response.data))
      .catch((error) => console.error(error));
  }, []);

  return (
    <div className="CustomerList">
      <h2>Customer List</h2>
      <ul>
        {customers.map((customer) => (
          <li key={customer.id}>
            {customer.name} - {customer.email}
          </li>
        ))}
      </ul>
    </div>
  );
};

export default CustomerList;
