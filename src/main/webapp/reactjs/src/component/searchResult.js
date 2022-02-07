import React, {useState} from "react";
import ResultCard from "../component/resultCard";
import {Form} from "react-bootstrap";



function SearchResult({results}){

    const [searches, setSearches] = useState({
        id: null,
        name: "My Search",
        searchedServices: []
    })

    const handleSubmit = async (event) => {
        event.preventDefault();
        // TODO: modal for naming search
        await saveSearchResults(searches);
    }

    const fetchSaveSearch = async (searches) => {
        const res = await fetch(`/api/search/save`,{
            method: 'POST',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(searches)  // user identification?
        })
        // setSearches({       // refact?
        //     id: null,
        //     name: "My Search",
        //     searchedServices: []
        // })
        return await res.json();
    }

    const saveSearchResults = async (search) => {
        await fetchSaveSearch(search);
    }

    return (
        <Form onSubmit={handleSubmit}>
            <div className='results'>
                {results.map((result, index) => (
                    <ResultCard key={index} result={result} setSearches={setSearches}/>
                ))}
                {
                    searches.searchedServices.length !== 0 ?
                        <multiButton label={"Save Search"} bWidth={"100%"}/> : null
                }
            </div>
        </Form>
    );

}
export default SearchResult;