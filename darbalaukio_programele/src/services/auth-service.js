import http from "../services/http-service";
import { setAuthToken } from "./setAuthToken";
import jwt_decode from "jwt-decode";

const login = async (username, password) => {
  return http
    .post("/auth/signin", {
      username,
      password,
    })
    .then((response) => {
      if (response.status === 200) {
        const token = response.data.token;
        localStorage.setItem("token", token);
        localStorage.setItem("user", JSON.stringify(response.data));
      }
    });
};

const logout = () => {
  localStorage.removeItem("token");
  localStorage.removeItem("user");
};

const getCurrentUser = () => {
  return JSON.parse(localStorage.getItem("user"));
};

const AuthService = {
  login,
  logout,
  getCurrentUser,
};
export default AuthService;
