import React from "react";
import {Link, useNavigate} from "react-router-dom";

import AuthService from "../../common/services/AuthService";

import logo from '../../assets/logo.svg'
import './header.scss';

export function Header() {

    const isAuthenticated = AuthService.isAuthenticated();
    const navigate = useNavigate();

    const handleLogout = () => {
        AuthService.logout();
        navigate("/");
    }

    return (
        <div className="header-wrapper">
            <header className="d-flex flex-wrap align-items-center justify-content-center justify-content-md-between py-3 mb-4 border-bottom">
                <a href="/" className="d-flex align-items-center col-md-3 mb-2 mb-md-0 text-dark text-decoration-none">
                    <img className="logo" src={logo} alt="Custom Logo" width="40" height="32"/>
                </a>
                <ul className="nav col-12 col-md-auto mb-2 justify-content-center mb-md-0">
                    <li><a href="/" className="nav-link px-2 link-dark">Home</a></li>
                    <li><a href="#" className="nav-link px-2 link-secondary">Test1</a></li>
                    <li><a href="#" className="nav-link px-2 link-secondary">Test2</a></li>
                </ul>
                <div className="col-md-3 text-end">
                    {!isAuthenticated ? (
                        <Link to="/auth">
                            <button type="button" className="btn btn-outline-primary me-2">Sign In</button>
                        </Link>
                    ) : (
                        <button type="button" onClick={handleLogout} className="btn btn-outline-primary">Logout</button>
                    )}
                </div>
            </header>
        </div>
    );

}

export default Header;