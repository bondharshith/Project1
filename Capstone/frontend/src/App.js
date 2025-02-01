import logo from "./logo.svg";
import "./App.css";
import Register from "./components/register";
import Login from "./components/login";
import Admin from "./components/admin";
import Menu from "./components/menu";
import User from "./components/user";
import { BrowserRouter, Routes, Route } from "react-router-dom";
import LandingPage from "./components/landingpage";
import UserMain from "./components/usermain";

function App() {
  return (
    <div className="App">
      <BrowserRouter>
        <Routes>
          <Route path="/" element={<LandingPage />} />
          <Route path="/usermain" element={<UserMain/>} />
          {/* <Route path="/register" element={<Register />} />
          <Route path="/admin" element={<Admin />} />
          <Route path="/login" element={<Login />} />
          <Route path="/user" element={<User />} /> */}
        </Routes>
      </BrowserRouter>
    </div>
  );
}

export default App;