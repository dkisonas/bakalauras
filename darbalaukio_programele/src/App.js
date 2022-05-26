import { BrowserRouter } from "react-router-dom";

import { setAuthToken } from "./services/setAuthToken";
import { Routes, Route } from "react-router-dom";
import Dashboard from "./pages/Dashboard";
import Departure from "./pages/Departure";
import RequireAuth from "./helpers/RequireAuth";
import Login from "./pages/Login";

function App() {
  const { ipcRenderer } = window.require("electron");
  const token = localStorage.getItem("token");
  if (token) {
    setAuthToken(token);
  }
  return (
    <BrowserRouter>
      <Routes>
        <Route path="/" element={<Login />} />
        <Route path="/" element={<RequireAuth />}>
          <Route path="dashboard" element={<Dashboard ipcRenderer={ipcRenderer}/>} />
          <Route path="departure" element={<Departure  ipcRenderer={ipcRenderer}/>} />
          <Route path="test" element={<></>}/>
        </Route>
      </Routes>
    </BrowserRouter>
  );
}

export default App;
