import React, {useEffect, useState} from 'react';
import { CountryDropdown, RegionDropdown, CountryRegionData } from 'react-country-region-selector';


function SearchForm ({setResults}) {
    const [country, setCountry] = useState("");
    const [city, setCity] = useState("");
    const [district, setDistrict] = useState("");
    const [region, setRegion] = useState("");


    const fetchResults = async (country, city, district) => {
        const res = await fetch(`http://localhost:8080/api/sertest?country=${country}&city=${city}&district=${district}`)
        return await res.json()
    }



    const handleSubmit = async (event) => {
        event.preventDefault();
        const getSearchResults = async (country, city, district) => {
            const resultFromApi = await fetchResults(country, city, district);
            setResults(resultFromApi)
        }
        getSearchResults(country)
    }


    return (
        <form onSubmit={handleSubmit}>
            <div>
                <CountryDropdown
                    value={country}
                    onChange={(val) => setCountry(val)} />
                <RegionDropdown
                    country={country}
                    value={region}
                    onChange={(val) => setRegion(val)} />
                <label>

                    District:
                    <input type="text" name="district"
                           value={district} onChange={(e) => {setDistrict(e.target.value)}}
                    />
                </label>
            </div>

            <br/>
            <input type="submit" value="Submit"/>
        </form>
    )
}

export default SearchForm;