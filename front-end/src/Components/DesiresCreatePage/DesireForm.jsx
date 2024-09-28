import React, {useState} from "react";
import AuthService from "../../common/services/AuthService";
import DesireService from "../../common/services/DesireService";
import {useNavigate} from "react-router-dom";

const urlPattern = /^(https?:\/\/)?([\da-z.-]+)\.([a-z.]{2,6})([\/\w .-]*)*\/?(\?.*)?$/;
const pricePattern = /^(0|[1-9]\d{0,8})$/;

export function DesireForm() {

    const [formData, setFormData] = useState({
        userId: AuthService.getUserId(),
        name: '',
        description: '',
        siteURL: '',
        price: '',
        currencyName: 'USD',
        priorityName: 'LOW',
        image: null,
    });
    const navigate = useNavigate();

    const handleChange = (e) => {
        const {id, value, files} = e.target;
        if (id === 'image') {
            setFormData(prevFormData => ({
                ...prevFormData,
                image: files[0],
            }));
        } else {
            setFormData(prevFormData => ({
                ...prevFormData,
                [id]: value,
            }));
        }
    };

    const handleSubmit = async (e) => {
        e.preventDefault();
        if (formData.name.length > 50 || formData.description.length > 100) {
            console.log('Invalid form')
            return;
        }
        const result = await DesireService.createDesire(formData);
        if (result.success) {
            console.log('Desire created:', result);
            navigate('/desires');
            alert('Desire created successfully!');
        } else {
            console.error(result.error);
        }
    };

    const getClassName = (field) => {
        if (!formData[field]) return 'form-control'
        if (field === 'name') {
            if (!formData.name || formData.name.length > 50) return 'form-control is-invalid';
        }
        if (field === 'description') {
            if (!formData.description || formData.description.length > 100) return 'form-control is-invalid';
        }
        if (field === 'siteURL') {
            if (!formData.siteURL || !urlPattern.test(formData.siteURL)) return 'form-control is-invalid';
        }
        if (field === 'price') {
            const priceValue = parseInt(formData.price, 10);
            if (priceValue < 0 || priceValue > 100000000 || !pricePattern.test(formData.price)) {
                return 'form-control is-invalid';
            }
        }
        return 'form-control is-valid';
    };

    return (
        <div className="desire-form">
            <form onSubmit={handleSubmit}>
                <div className="mb-3">
                    <label htmlFor="name" className="form-label">
                        Name<span className="text-danger"> *</span>
                    </label>
                    <input
                        type="text"
                        className={getClassName('name')}
                        id="name"
                        value={formData.name}
                        required
                        onChange={handleChange}
                    />
                    <div id="nameHelp" className="form-text">Should be less then 50 symbols.</div>
                </div>
                <div className="mb-3">
                    <label htmlFor="description" className="form-label">
                        Description
                    </label>
                    <input
                        type="text"
                        className={getClassName('description')}
                        id="description"
                        value={formData.description}
                        onChange={handleChange}
                    />
                    <div id="descriptionHelp" className="form-text">Should be less then 100 symbols.</div>
                </div>
                <div className="mb-3">
                    <label htmlFor="name" className="form-label">
                        Link
                    </label>
                    <input
                        type="url"
                        className={getClassName('siteURL')}
                        id="siteURL"
                        value={formData.siteURL}
                        pattern={urlPattern.source}
                        onChange={handleChange}
                    />
                </div>
                <div className="mb-3">
                    <label htmlFor="price" className="form-label">
                        Price<span className="text-danger"> *</span>
                    </label>
                    <div className="input-group">
                        <input
                            type="number"
                            className={getClassName('price')}
                            id="price"
                            value={formData.price}
                            min="0"
                            max="100000000"
                            step="1"
                            required
                            onChange={handleChange}
                            style={{width: "70%"}}
                        />
                        <select
                            className="form-select"
                            id="currencyName"
                            value={formData.currencyName}
                            onChange={handleChange}
                            style={{width: "30%"}}
                        >
                            <option value="USD">USD</option>
                            <option value="EUR">EUR</option>
                            <option value="UAH">UAH</option>
                        </select>
                    </div>
                </div>
                <div className="mb-3">
                    <label htmlFor="priorityName" className="form-label">
                        Priority<span className="text-danger"> *</span>
                    </label>
                    <select
                        className="form-select"
                        id="priorityName"
                        value={formData.priorityName}
                        onChange={handleChange}
                    >
                        <option value="LOW">Low</option>
                        <option value="MEDIUM">Medium</option>
                        <option value="HIGH">High</option>
                    </select>
                </div>
                <div className="mb-3">
                    <label htmlFor="image" className="form-label">Image</label>
                    <input
                        type="file"
                        className="form-control form-control-sm"
                        id="image"
                        accept="image/*"
                        onChange={handleChange}
                    />
                </div>
                <button type="submit" className="btn btn-primary">Add</button>
            </form>
        </div>
    );

}

export default DesireForm;