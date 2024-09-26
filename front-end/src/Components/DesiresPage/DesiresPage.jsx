import React, {useEffect, useState} from "react";
import DesireService from "../../common/services/DesireService";
import AuthService from "../../common/services/AuthService";
import DesiresFilter from "./DesiresFilter";
import DesiresCards from "./DesiresCards";

import './desires-page.scss';

export function DesiresPage() {

    const [desires, setDesires] = useState([]);
    const [filteredDesires, setFilteredDesires] = useState([]);
    const [error, setError] = useState('');
    const [sortType, setSortType] = useState('');
    const [filterPriority, setFilterPriority] = useState({
        HIGH: false,
        MEDIUM: false,
        LOW: false
    });

    useEffect(() => {
        const fetchDesires = async () => {
            const userId = AuthService.getUserId();
            const result = await DesireService.getAllDesires(userId);
            if (result.success) {
                setDesires(result.data);
                setFilteredDesires(result.data);
            } else {
                setError(result.error);
            }
        };
        fetchDesires();
    }, []);

    useEffect(() => {
        let filtered = [...desires];

        const selectedPriorities = Object.keys(filterPriority).filter(key => filterPriority[key]);
        if (selectedPriorities.length > 0) {
            filtered = filtered.filter(desire => selectedPriorities.includes(desire.priorityName));
        }

        if (sortType === 'name-asc') {
            filtered.sort((a, b) => a.name.localeCompare(b.name));
        } else if (sortType === 'name-desc') {
            filtered.sort((a, b) => b.name.localeCompare(a.name));
        } else if (sortType === 'price-asc') {
            filtered.sort((a, b) => a.price - b.price);
        } else if (sortType === 'price-desc') {
            filtered.sort((a, b) => b.price - a.price);
        }

        setFilteredDesires(filtered);
    }, [filterPriority, sortType, desires]);

    const handlePriorityChange = (priority) => {
        setFilterPriority(prev => ({
            ...prev,
            [priority]: !prev[priority]
        }));
    };

    return (
        <div className="desires-page-wrapper">
            <div className="header-wrapper">
                {error && <p className="text-danger text-center">{error}</p>}
            </div>
            <div className="row">
                <DesiresFilter
                    filterPriority={filterPriority}
                    sortType={sortType}
                    handlePriorityChange={handlePriorityChange}
                    setSortType={setSortType}
                    setFilterPriority={setFilterPriority}
                />
                <DesiresCards desires={filteredDesires}/>
            </div>
            <div className="header-wrapper">
            </div>
        </div>
    );

}

export default DesiresPage;