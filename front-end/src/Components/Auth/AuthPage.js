import React, {useState} from 'react';
import SwitchSelector from "react-switch-selector";

import LoginForm from './LoginForm';
import RegisterForm from './RegisterForm';

import './auth-page.scss';


function AuthPage() {
    const [isLogin, setIsLogin] = useState(true);

    const options = [
        {
            label: "Login",
            value: true,
            selectedBackgroundColor: "#007bff",
            fontColor: "#ffffff"
        },
        {
            label: "Register",
            value: false,
            selectedBackgroundColor: "#007bff",
            fontColor: "#ffffff"
        }
    ];

    const onChange = (newValue) => {
        setIsLogin(newValue);
    };

    return (
        <div className="auth-page-wrapper">
            <div className="auth-form-wrapper">
                <div className="auth-switch">
                    <SwitchSelector
                        onChange={onChange}
                        options={options}
                        initialSelectedIndex={0}
                        backgroundColor={"#e0e0e0"}
                        fontColor={"#000000"}
                    />
                </div>
                <div className="auth-form">
                    {isLogin ? <LoginForm/> : <RegisterForm/>}
                </div>
            </div>
        </div>
    );
}

export default AuthPage;