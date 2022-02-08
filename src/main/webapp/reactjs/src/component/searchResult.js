import React from "react";
import ResultCard from "../component/resultCard";


function SearchResult({results}){
    return (
            <div className='results'>
                {results.map((result, index) => (
                    <ResultCard key={index} result={result} />
                ))}
            </div>
    );

}
export default SearchResult;