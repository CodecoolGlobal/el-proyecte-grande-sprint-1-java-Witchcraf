import React from 'react';
import SearchResult from "./searchResult";
import Services from "./services";
import {
    hospitalImageAndDetails,
    restaurantImageAndDetails, shelterImageAndDetails,
    wellnessImageAndDetails
} from "../data";
import NoResult from "./noResult";


function Content({results, isResult}){
    let content;
    if (!isResult) {
        content = <div>
            <Services {...restaurantImageAndDetails} />
            <Services {...hospitalImageAndDetails} />
            <Services {...wellnessImageAndDetails} />
            <Services {...shelterImageAndDetails} />
        </div>
    } else if (results.length === 0) {
        content = <div>
            <NoResult />
        </div>
    } else {
        content = <SearchResult results={results} />
    }

    return (
        <div>{content}</div>
    );
}
export default Content;