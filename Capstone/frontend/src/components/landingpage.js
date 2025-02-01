import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import "./landingpage.css";

function LandingPage() {
  const [isLoggedIn, setIsLoggedIn] = useState(false);
  const [userRole, setUserRole] = useState(""); // "user" or "admin"
  const [showDropdown, setShowDropdown] = useState(false);
  const navigate = useNavigate();

  // Simulate login
  const handleLogin = (role) => {
    setIsLoggedIn(true);
    setUserRole(role);
    navigate(role === "admin" ? "/admin" : "/user");
  };

  // Simulate logout
  const handleLogout = () => {
    setIsLoggedIn(false);
    setUserRole("");
    setShowDropdown(false);
    navigate("/");
  };

  // Handle feature click
  const handleFeatureClick = () => {
    if (!isLoggedIn) {
      alert("Please login or create an account to access this feature.");
    } else {
      alert(`Welcome, ${userRole}! You can now access this feature.`);
    }
  };

  return (
    <div className="landing-page">
      {/* Navbar */}
      <nav className="navbar">
        <div className="navbar-left">One Piece Mobile Service</div>
        <div className="navbar-right">
          {isLoggedIn ? (
            <div className="dropdown">
              <button
                className="dropdown-button"
                onClick={() => setShowDropdown(!showDropdown)}
              >
                {userRole === "admin" ? "Admin" : "User"} &#9660;
              </button>
              {showDropdown && (
                <div className="dropdown-content">
                  <button onClick={() => navigate("/profile")}>Profile</button>
                  <button onClick={handleLogout}>Logout</button>
                </div>
              )}
            </div>
          ) : null}
        </div>
      </nav>

      {/* Main Content */}
      <div className="content">
        <h1>Welcome to One Piece Mobile Service</h1>
        <p>Explore our amazing features!</p>

        {/* Features */}
        <div className="features">
          <button className="feature-button" onClick={handleFeatureClick}>
            Unlimited Talktime
          </button>
          <button className="feature-button" onClick={handleFeatureClick}>
            Unlimited Entertainment
          </button>
          <button className="feature-button" onClick={handleFeatureClick}>
            Gear 5
          </button>
        </div>

        {/* User and Admin Buttons */}
        {!isLoggedIn && (
          <div className="user-admin-buttons">
            <button
              className="user-button"
              onClick={() => navigate("/user")}
            >
              User
            </button>
            <button
              className="admin-button"
              onClick={() => navigate("/admin")}
            >
              Admin
            </button>
          </div>
        )}
      </div>
    </div>
  );
}

export default LandingPage;