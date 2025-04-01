import React from "react";
import { getGreeting, submitData } from "./api";
import { BrowserRouter as Router, Routes, Route, Link } from "react-router-dom";
import VendorPage from "./webpages/VendorPage";
import CustomerPage from "./webpages/CustomerPage";
import TicketPage from "./webpages/TicketPage";
import TicketPurchasePage from "./webpages/TicketPurchasePage";
import Navbar from "./webpages/Navbar";
import Home from "./webpages/Home";
import "./App.css";

function App() {
  return (
    <Router>
      <Navbar />
      <div className="App">
        <div className="home-sections">
          <div className="section">
            <Link to="/vendors">
              <button class="btn-add">Vendors</button>
            </Link>
          </div>
          <div className="section">
            <Link to="/customers">
              <button class="btn-add">Customers</button>
            </Link>
          </div>
          <div className="section">
            <Link to="/tickets">
              <button class="btn-add">Tickets</button>
            </Link>
          </div>
          <div className="section">
            <Link to="/ticket-purchase">
              <button class="btn-add">Ticket Purchase</button>
            </Link>
          </div>
        </div>

        {/* Routes */}
        <Routes>
          <Route path="/" element={<Home />} />
          <Route path="/vendors" element={<VendorPage />} />
          <Route path="/customers" element={<CustomerPage />} />
          <Route path="/tickets" element={<TicketPage />} />
          <Route path="/ticket-purchase" element={<TicketPurchasePage />} />
        </Routes>
      </div>
    </Router>
  );
}

export default App;
