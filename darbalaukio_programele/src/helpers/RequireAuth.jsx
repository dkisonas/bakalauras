import { Navigate } from "react-router-dom";
import { Outlet } from "react-router-dom";

function hasJWT() {
  return localStorage.getItem("token");
}

export default function RequireAuth() {
  const isAuthenticated = hasJWT();
  return isAuthenticated ? <Outlet /> : <Navigate to="/" />;
}
