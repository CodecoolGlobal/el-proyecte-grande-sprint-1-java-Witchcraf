import React from 'react';
import SearchResult from "./searchResult";
import Services from "./services";


function Content({results}){
    let content;
    if (results.length === 0) {
        content = <Services />
    } else {
        content = <SearchResult results={results} />
    }

    return (
        <div>{content}</div>
    );
}
export default Content;