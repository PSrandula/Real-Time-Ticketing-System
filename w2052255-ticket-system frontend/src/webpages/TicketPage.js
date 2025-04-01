import React from "react";
import { getGreeting, submitData } from "../api";
import TicketList from "../components/Ticket/TicketList";
import "./TicketPage.css";

const TicketPage = () => {
  return (
    <div className="TicPage">
      <h2>Ticket Management</h2>
      <p>
      The Ticket Purchase Page in the Real-Time Ticketing System offers an intuitive 
      platform for customers to effortlessly select their profile and preferred tickets. 
      With a simple dropdown menu to choose their name and a clear selection of available 
      tickets, users can finalize their purchase with ease. This streamlined interface enhances 
      the ticket-buying experience, providing a quick and hassle-free process for all users.
      </p>
      <TicketList />
    </div>
  );
};

export default TicketPage;
