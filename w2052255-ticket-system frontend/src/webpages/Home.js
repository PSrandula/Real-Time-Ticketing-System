import React from "react";
import { getGreeting, submitData } from "../api";
import "./Home.css";

function Home() {
  return (
    <div className="home">
      <h1><span>Hello..!</span><br></br> Welcome to the Real Time Event Ticketing System</h1>
      <p>
        Welcome to the Real-Time Event Ticketing System, your ultimate solution 
        for efficient event ticketing. Whether you're a vendor managing your event 
        tickets or a customer excited to secure tickets, our platform is here to 
        provide a streamlined experience. Discover a range of features designed to 
        simplify ticket management, from vendor operations to ticket purchases. With 
        a modern, user-friendly interface, our system ensures you can effortlessly 
        navigate, organize, and enjoy your ticketing journey.
      </p>
    </div>
  );
}

export default Home;
