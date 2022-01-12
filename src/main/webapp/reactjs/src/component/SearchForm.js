import React, {useEffect, useState} from 'react';

function SearchForm ({setResults}) {
    const [country, setCountry] = useState("");


    const fetchResults = async (country) => {
        const res = await fetch(`http://localhost:8080/api/ser?country=${country}`)
        return await res.json()
    }



    const handleChange = (event) => {
        setCountry(event.target.value)
    }

    const handleSubmit = async (event) => {
        event.preventDefault();
        const getSearchResults = async (country) => {
            const resultFromApi = await fetchResults(country);
            setResults(resultFromApi)
        }
        getSearchResults(country)
    }


    return (
        <form onSubmit={handleSubmit}>

            <label>
                Country:
                <input type="text" name="country"
                       value={country} onChange={handleChange}
                />
            </label>
            <input type="submit" value="Submit"/>
        </form>
    )
}

export default SearchForm;