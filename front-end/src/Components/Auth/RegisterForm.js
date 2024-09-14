import React, {useState} from 'react';
import UserService from "../../common/services/UserService";

function RegisterForm() {

    const [formData, setFormData] = useState({
        email: '',
        name: '',
        password: '',
        passwordConfirmation: '',
    });
    const [emailExists, setEmailExists] = useState(false);
    const [passwordsMatch, setPasswordsMatch] = useState(true);

    const handleChange = (e) => {
        const {id, value} = e.target;
        setFormData(prevFormData => {
            const updatedFormData = {
                ...prevFormData,
                [id]: value,
            };
            if (id === 'password' || id === 'passwordConfirmation') {
                setPasswordsMatch(updatedFormData.password === updatedFormData.passwordConfirmation);
            }
            return updatedFormData;
        });
    };

    const handleBlur = async (e) => {
        const {id, value} = e.target;
        if (id === 'email') {
            const result = await UserService.checkExistByEmail(value);
            setEmailExists(result.data);
        }
    };

    const handleSubmit = async (e) => {
        e.preventDefault();
        const result = await UserService.register(formData);
        if (result.success) {
            console.log('User registered:', result.data);
        } else {
            console.error(result.error);
        }

    };

    return (
        <div className="register-form-wrapper">
            <form onSubmit={handleSubmit}>
                <div className="mb-3">
                    <label htmlFor="email" className="form-label">
                        Email address<span className="text-danger"> *</span>
                    </label>
                    <input
                        type="email"
                        className={`form-control ${emailExists ? 'is-invalid' : ''}`}
                        id="email"
                        value={formData.email}
                        required
                        onChange={handleChange}
                        onBlur={handleBlur}
                    />
                    {emailExists && <div className="invalid-feedback">Email already exists</div>}
                </div>
                <div className="mb-3">
                    <label htmlFor="name" className="form-label">
                        Name<span className="text-danger"> *</span>
                    </label>
                    <input
                        type="text"
                        className="form-control"
                        id="name"
                        value={formData.name}
                        required
                        onChange={handleChange}
                    />
                </div>
                <div className="mb-3">
                    <label htmlFor="password" className="form-label">
                        Password<span className="text-danger"> *</span>
                    </label>
                    <input
                        type="password"
                        className="form-control"
                        id="password"
                        value={formData.password}
                        minLength={6}
                        required
                        onChange={handleChange}
                    />
                </div>
                <div className="mb-3">
                    <label htmlFor="passwordConfirmation" className="form-label">
                        Confirm Password<span className="text-danger"> *</span>
                    </label>
                    <input
                        type="password"
                        className={`form-control ${!passwordsMatch ? 'is-invalid' : ''}`}
                        id="passwordConfirmation"
                        value={formData.passwordConfirmation}
                        minLength={6}
                        required
                        onChange={handleChange}
                    />
                    {!passwordsMatch && <div className="invalid-feedback">Passwords do not match</div>}
                </div>
                <button type="submit" className="btn btn-primary">Register</button>
            </form>
        </div>
    );

}

export default RegisterForm;