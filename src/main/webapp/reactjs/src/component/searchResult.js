import React, {useState} from "react";


function SearchResult({results}){
    return (
        <p>{results.name}</p>
    );
}
export default SearchResult;