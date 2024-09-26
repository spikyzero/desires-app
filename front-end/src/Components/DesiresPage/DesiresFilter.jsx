import React from 'react';

export function DesiresFilter({filterPriority, sortType, handlePriorityChange, setSortType, setFilterPriority}) {

    return (
        <div className="filter-wrapper">
            <div className="filter">
                <div>
                    <h5>Filter by:</h5>
                    <div className="form-check">
                        <input
                            className="form-check-input"
                            id="sortTypePriorityHigh"
                            type="checkbox"
                            checked={filterPriority.HIGH}
                            onChange={() => handlePriorityChange('HIGH')}
                        />
                        <label className="form-check-label" htmlFor="sortTypePriorityHigh">
                            High
                        </label>
                    </div>
                    <div className="form-check">
                        <input
                            className="form-check-input"
                            id="sortTypePriorityMedium"
                            type="checkbox"
                            checked={filterPriority.MEDIUM}
                            onChange={() => handlePriorityChange('MEDIUM')}
                        />
                        <label className="form-check-label" htmlFor="sortTypePriorityMedium">
                            Medium
                        </label>
                    </div>
                    <div className="form-check">
                        <input
                            className="form-check-input"
                            id="sortTypePriorityLow"
                            type="checkbox"
                            checked={filterPriority.LOW}
                            onChange={() => handlePriorityChange('LOW')}
                        />
                        <label className="form-check-label" htmlFor="sortTypePriorityLow">
                            Low
                        </label>
                    </div>
                </div>
                <div>
                    <h5>Sort by:</h5>
                    <div className="form-check">
                        <input
                            className="form-check-input"
                            id="sortTypeNameAsc"
                            type="radio"
                            name="sortType"
                            value="name-asc"
                            checked={sortType === 'name-asc'}
                            onChange={(e) => setSortType(e.target.value)}
                        />
                        <label className="form-check-label" htmlFor="sortTypeNameAsc">
                            Name (A-Z)
                        </label>
                    </div>
                    <div className="form-check">
                        <input
                            className="form-check-input"
                            id="sortTypeNameDesc"
                            type="radio"
                            name="sortType"
                            value="name-desc"
                            checked={sortType === 'name-desc'}
                            onChange={(e) => setSortType(e.target.value)}
                        />
                        <label className="form-check-label" htmlFor="sortTypeNameDesc">
                            Name (Z-A)
                        </label>
                    </div>
                    <div className="form-check">
                        <input
                            className="form-check-input"
                            id="sortTypePriceDesc"
                            type="radio"
                            name="sortType"
                            value="price-desc"
                            checked={sortType === 'price-desc'}
                            onChange={(e) => setSortType(e.target.value)}
                        />
                        <label className="form-check-label" htmlFor="sortTypePriceDesc">
                            Price (High to Low)
                        </label>
                    </div>
                    <div className="form-check">
                        <input
                            className="form-check-input"
                            id="sortTypePriceAsc"
                            type="radio"
                            name="sortType"
                            value="price-asc"
                            checked={sortType === 'price-asc'}
                            onChange={(e) => setSortType(e.target.value)}
                        />
                        <label className="form-check-label" htmlFor="sortTypePriceAsc">
                            Price (Low to High)
                        </label>
                    </div>
                </div>
                <div className="reset-button">
                    <button
                        className="btn btn-secondary"
                        onClick={() => {
                            setFilterPriority({
                                HIGH: false,
                                MEDIUM: false,
                                LOW: false
                            });
                            setSortType('');
                        }}
                    >
                        Reset
                    </button>
                </div>
            </div>
        </div>
    );

}

export default DesiresFilter;