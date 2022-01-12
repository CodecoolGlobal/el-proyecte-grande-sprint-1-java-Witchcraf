import React from "react";


function SearchResult({results}){
    console.log(results)
    return (
        <p>{results.name}</p>
    );
}
export default SearchResult;