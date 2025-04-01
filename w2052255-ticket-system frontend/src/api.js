import axios from "axios";

const API_BASE_URL = "http://localhost:8080/api"; // Your backend URL

export const getGreeting = async () => {
  try {
    const response = await axios.get(`${API_BASE_URL}/greeting`);
    return response.data;
  } catch (error) {
    console.error("Error fetching greeting:", error);
  }
};

export const submitData = async (data) => {
  try {
    const response = await axios.post(`${API_BASE_URL}/submit`, data);
    return response.data;
  } catch (error) {
    console.error("Error submitting data:", error);
  }
};
