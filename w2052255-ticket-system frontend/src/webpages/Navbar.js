import React from "react";
import { getGreeting, submitData } from "../api";
import "./Navbar.css";
import logo from "./download (5).jpeg";

function Navbar() {
  return (
    <nav className="navbar">
      <div className="navbar-logo">
        <img src={logo} alt="Logo" className="logo" />
      </div>
      <h1>Real Time Event Ticketing System</h1>
    </nav>
  );
}

export default Navbar;
