import axios from "axios";
import authHeader from "./authHeader";
export default axios.create({
  baseURL: process.env.REACT_APP_API_URL,
  headers: {
    'authorization': authHeader(),
    "Content-type": "application/json"
  }
});