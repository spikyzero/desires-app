import React from 'react';

function RegisterForm() {

    return (
        <div className="register-form-wrapper">
            <form>
                <div className="mb-3">
                    <label htmlFor="email" className="form-label">Email address</label>
                    <input type="email" className="form-control" id="email" aria-describedby="emailHelp"/>
                    <div id="emailHelp" className="form-text">We'll never share your email with anyone else.</div>
                </div>
                <div className="mb-3">
                    <label htmlFor="username" className="form-label">Username</label>
                    <input type="text" className="form-control" id="username"/>
                </div>
                <div className="mb-3">
                    <label htmlFor="password" className="form-label">Password</label>
                    <input type="password" className="form-control" id="password"/>
                </div>
                <div className="mb-3">
                    <label htmlFor="passwordConfirmation" className="form-label">Confirm Password</label>
                    <input type="password" className="form-control" id="passwordConfirmation"/>
                </div>
                <button type="submit" className="btn btn-primary">Register</button>
            </form>
        </div>
    );

}

export default RegisterForm;