import React, {useState} from "react";
import ResultCard from "./resultCard";
import {Form} from "react-bootstrap";
import MultiButton from "./multiButton";
import SaveSearchModal from "./saveSearchModal";

function SearchResult({results}){
    const username = window.localStorage.getItem("username");
    console.log(username);

    const [displayModal, setDisplayModal] = useState(false);
    const [clearCheckbox, setClearCheckbox] = useState(false);
    const [searches, setSearches] = useState({
        username: username,
        // username: "Hokedli",    // change in saveSearchModal setSearches too!
        searchName: "My Search",
        description: "",
        searchedServices: []
    })

    const handleSubmit = async (event) => {
        event.preventDefault();
        // TODO: modal for naming search
        setDisplayModal(true);
          //
    }

    return (
        <Form onSubmit={handleSubmit}>
            <div className='results'>
                {results.map((result, index) => (
                    <ResultCard
                        key={index}
                        result={result}
                        searches={searches}
                        setSearches={setSearches}
                        clearCheckbox={clearCheckbox}
                    />
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
                <div>
                    {
                        displayModal ?
                            <SaveSearchModal
                                searches={searches}
                                setSearches={setSearches}
                                setDisplayModal={setDisplayModal}
                                setClearCheckbox={setClearCheckbox}
                                username={username}
                            /> : null
                    }

                </div>
            </div>
        </Form>
    );

}
export default SearchResult;
