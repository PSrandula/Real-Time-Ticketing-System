import React, { useState, useEffect } from "react";
import axios from "axios";
import "./VendorList.css";

function VendorList() {
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

  return (
    <div className="vendor-list">
      <h2>Vendor List</h2>
      <ul>
        {vendors.map((vendor) => (
          <li key={vendor.id}>
            {vendor.vendorName} - {vendor.email}
          </li>
        ))}
      </ul>
    </div>
  );
}

export default VendorList;
