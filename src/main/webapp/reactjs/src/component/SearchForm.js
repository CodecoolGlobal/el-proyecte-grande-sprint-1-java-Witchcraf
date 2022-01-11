import React, {useEffect, useState} from 'react';
import {Container, Form} from 'react-bootstrap';
import {CountryDropdown} from 'react-country-region-selector';

import 'bootstrap/dist/css/bootstrap.css';
import 'react-bootstrap-country-select/dist/react-bootstrap-country-select.css';


function SearchForm() {
    const [country, setCountry] = useState("");
    const [region, setRegion] = useState("");
    const [result, setResult] = useState([]);
    const [isSubmit, setSubmit] = useState(false);

    useEffect(() => {
        console.log("effect")
        const getSearchResults = async () => {
            const resultsFromAPI = await fetchResults();
            setResult(resultsFromAPI);
        }
        getSearchResults()
    }, [isSubmit])

    //FetchResuls
    const fetchResults = async () => {
        const res = await fetch(`http://localhost:8080/api/service/${country}`)
        console.log(res)
        return await res.json()
    }

    const handleSubmit = async (e) => {
        e.preventDefault();
        setSubmit(true)
        console.log(isSubmit)
    }

    console.log(country)


    return (
        <Container>
            <form onSubmit={handleSubmit}>
                <Form.Group controlId="form.country">
                    <CountryDropdown
                        value={country}
                        onChange={(val) => setCountry(val)} />
                </Form.Group>
                <button type="submit">Submit</button>
            </form>
        </Container>
    )


}

export default SearchForm;