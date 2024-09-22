import React, {useEffect, useState} from "react";
import DesireService from "../../common/services/DesireService";
import AuthService from "../../common/services/AuthService";
import imageNotExist from "../../assets/image-not-exists.png";

import './desires-page.scss';

export function DesiresPage() {

    const [desires, setDesires] = useState([]);
    const [error, setError] = useState('');

    useEffect(() => {
        const fetchDesires = async () => {
            const userId = AuthService.getUserId();
            const result = await DesireService.getAllDesires(userId);
            if (result.success) {
                setDesires(result.data);
            } else {
                setError(result.error);
            }
        };

        fetchDesires();
    }, []);

    const priorityStyles = {
        HIGH: {backgroundColor: 'lightcoral'},
        MEDIUM: {backgroundColor: 'gold'},
        LOW: {backgroundColor: 'lightgreen'},
    };

    return (
        <div className="desires-page-wrapper">
            <h1 className="my-4 text-center">Desires Page</h1>
            {error && <p className="text-danger text-center">{error}</p>}
            <div className="cards-wrapper row">
                {desires.map(desire => (
                    <div className="card-wrapper" key={desire.id}>
                        <div key={desire.id} className="card">
                            <input type="hidden" value={desire.id}/>
                            <div className="card-header" style={priorityStyles[desire.priorityName] || {}}>
                                <b>Priority:</b> {desire.priorityName || "---"}
                            </div>
                            <div className="card-image-wrapper border-bottom">
                                <img
                                    src={desire.imageBase64 || imageNotExist}
                                    className="card-img-top"
                                    alt={desire.name || "No image available."}
                                />
                            </div>
                            <div className="card-body">
                                <h5 className="card-title">{desire.name || "---"}</h5>
                                <p className="card-text card-description">
                                    {desire.description || "---"}
                                </p>
                                <p className="card-text">
                                    <b>Price:</b> {desire.price !== undefined ? `${desire.price} ${desire.currencyName}` : "---"}
                                </p>
                                <p className="card-text card-site-link">
                                    {desire.siteURL ? (
                                        <>
                                            <a className="link-info" href={desire.siteURL} target="_blank"
                                               rel="noopener noreferrer">
                                                Click
                                            </a>
                                        </>
                                    ) : (
                                        <span className="card-text"/>
                                    )}
                                </p>
                            </div>
                        </div>
                    </div>
                ))}
            </div>
        </div>
    );
}

export default DesiresPage;