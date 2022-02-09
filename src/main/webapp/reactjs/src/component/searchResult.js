import React, {useState} from "react";
import ResultCard from "../component/resultCard";
import {Form} from "react-bootstrap";
import MultiButton from "./multiButton";



function SearchResult({results}){

    const username = window.localStorage.getItem("username");

    const [searches, setSearches] = useState({
        username: username,
        // username: "Hokedli",
        searchName: "My Search",
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
        const resultFromApi = await fetchSaveSearch(search);
    }

    return (
        <Form onSubmit={handleSubmit}>
            <div className='results'>
                {results.map((result, index) => (
                    <ResultCard key={index} result={result} searches={searches} setSearches={setSearches}/>
                ))}
                <div className="container-fluid">
                    <div className="row justify-content-center">
                        <div className="col-8 mt-5">
                            <div className="card">
                                {
                                    searches.searchedServices.length !== 0 ?
                                            <MultiButton label={"Save Search"} bWidth={"100%"}/> : null
                                }
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </Form>
    );

}
export default SearchResult;