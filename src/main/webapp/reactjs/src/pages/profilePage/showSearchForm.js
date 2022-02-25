import React from "react";
import SearchCard from "./components/searchCard";


function ShowSearchForm({savedSearch}) {

    return (
        <>
            <div className='results'>
                <h2>{savedSearch.name}</h2>
                {savedSearch.searchedServices.map((result, index) => (
                    <SearchCard
                        key={index}
                        result={result}
                    />
                ))}
            </div>
        </>
    );
}

export default ShowSearchForm;
