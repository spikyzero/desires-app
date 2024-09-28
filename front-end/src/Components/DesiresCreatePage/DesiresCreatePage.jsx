import React from "react";
import DesireForm from "./DesireForm";

import './desire-create-page.scss';

export function DesiresCreatePage() {

    return (
        <div className="desires-create-page-wrapper">
            <div className="desire-form-wrapper">
                <DesireForm/>
            </div>
        </div>
    );

}

export default DesiresCreatePage;