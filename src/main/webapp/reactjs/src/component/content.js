import React from 'react';
import SearchResult from "./searchResult";
import Services from "./services";
import {
    hospitalImageAndDetails,
    restaurantImageAndDetails, shelterImageAndDetails,
    wellnessImageAndDetails
} from "../pages/indexPage/data";


function Content({results}){
    let content;
    if (results.length === 0) {
        content = <div>
                    <Services {...restaurantImageAndDetails} />
                    <Services {...hospitalImageAndDetails} />
                    <Services {...wellnessImageAndDetails} />
                    <Services {...shelterImageAndDetails} />
                    </div>
    } else {
        content = <SearchResult results={results} />
    }

    return (
        <div>{content}</div>
    );
}
export default Content;