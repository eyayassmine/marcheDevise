import { Link } from "react-router-dom";
import React, { useState } from "react";  // ✅ Import useState
import "./Navbar.css";
import BFIlogo from '../../assets/BFIlogowob.png';



const Navbar = () => {
    const [menuOpen, setMenuOpen] = useState(false);  // ✅ Define state for menu


  return (
    <nav className="navbar">
        
            {/* Right Section (Language, Buttons) */}
            <div className="right-section">
                {/* Search Button */}
                <button className="search-button">🔍</button>
                
                {/* Filter Button (Select Products) */}
                <select className="filter-button">
                    <option value="all">All Products</option>
                    <option value="category1">Category 1</option>
                    <option value="category2">Category 2</option>
                    <option value="category3">Category 3</option>
                </select>
                {/* Notification Box */}
                <div className="notification-box">
                    🔔 <span className="notification-count">3</span> {/* Example notification count */}
                </div>
                {/* Sign Up & Sign Out Buttons */}
                <button className="auth-button">Sign Up</button>
                <button className="auth-button">Sign Out</button>
                {/* Support Button */}
                <button className="support-button">Support</button>

                <div className="language-toggle">
                    <span>🌍</span> {/* Replace with language icon */}
                </div>
                {/* Menu Button */}
                {/* Menu Button */}
                <div className="menu-container">
                        <button className="menu-button" onClick={() => setMenuOpen(!menuOpen)}>☰ </button>
                        
                        {/* Dropdown Menu */}
                        {menuOpen && (
                            <div className="dropdown-menu">
                                <button className="menu-item">Sign Up</button>
                                <button className="menu-item">Sign Out</button>
                                <button className="menu-item">⚙️ Settings</button>
                            </div>
                        )}
                </div>
            </div>
        
        {/* Logo & School Name */}
        <div className="left-section">
        <div className="logo-section">
        <img src={BFIlogo} alt="BFI Logo" className="logo" />
        </div>   
        <div className="application name ">
        <span className="app-name">BFI Trading</span>
        </div>
        </div>
              
        <div className="navbar-content">
            {/* Navbar Links */}
            <ul className="nav-links">
            <li><a href="#trading">Trading</a></li>
            <li><a href="#research">Research</a></li>
            <li><a href="#multichart">MultiChart</a></li>
            <li><a href="#account">Account</a></li>
            </ul>
        </div>

    </nav>
  );
};

export default Navbar;
