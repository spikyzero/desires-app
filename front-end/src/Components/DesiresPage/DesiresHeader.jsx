import React from "react";
import {Link} from "react-router-dom";

export function DesiresHeader({error}) {

    return (
        <div className="desires-header-wrapper row">
            <div className="desires-header-container"/>
            <div className="desires-header-container">
                {error && <p className="text-danger text-center">{error}</p>}
            </div>
            <div className="desires-header-container">
                <div className="add-desires-button">
                    <Link to="/desire/create">
                        <button className="btn btn-primary btn-lg">
                            +
                        </button>
                    </Link>
                </div>
            </div>
        </div>
    );

}

export default DesiresHeader;