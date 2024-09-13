import React from "react";

export function HomePage() {

    return (
        <div className="home-page-wrapper">
            <div>
                <b>Home Page</b>
            </div>
            <div className="btn-group" role="group" aria-label="Basic mixed styles example">
                <button type="button" className="btn btn-danger">Left</button>
                <button type="button" className="btn btn-warning">Middle</button>
                <button type="button" className="btn btn-success">Right</button>
            </div>
        </div>
    );
}

export default HomePage;