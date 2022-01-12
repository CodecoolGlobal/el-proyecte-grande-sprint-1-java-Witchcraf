import React, {useState} from 'react';
import { CountryDropdown, RegionDropdown } from 'react-country-region-selector';


function SearchForm ({setResults}) {
    const [search, setSearch] = useState({
        country: "",
        region: "",
        district: "",
        serviceType: "",
        serviceSubType: "",
        petType: ""
    })


    const fetchResults = async (search) => {
        const res = await fetch(`http://localhost:8080/api/sertest`,{
            method: 'POST',
                headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(search)
        })
        const a =  await res.json()
        console.log(a)
        return a;
    }



    const handleSubmit = async (event) => {
        event.preventDefault();
        const getSearchResults = async (search) => {
            const resultFromApi = await fetchResults(search);
            setResults(resultFromApi)
        }
        await getSearchResults(search)
    }


    return (
        <form onSubmit={handleSubmit}>
            <div>
                <CountryDropdown
                    value={search.country}
                    onChange={(val) => setSearch({...search, country: val}) }/>
                <RegionDropdown
                    country={search.country}
                    value={search.region}
                    onChange={(val) => setSearch({...search, region: val})} />
                <label>
                    District:
                    <input type="text" name="district"
                           value={search.district} onChange={(e) => {setSearch({...search, district: e.target.value})}}
                    />
                </label>
                <label>
                    serviceType:
                    <input type="text" name="district"
                           value={search.serviceType} onChange={(e) => {setSearch({...search, serviceType: e.target.value})}}
                    />
                </label>
                <label>
                    serviceSubType:
                    <input type="text" name="district"
                           value={search.serviceSubType} onChange={(e) => {setSearch({...search, serviceSubType: e.target.value})}}
                    />
                </label>
                <label>
                    petType:
                    <input type="text" name="district"
                           value={search.petType} onChange={(e) => {setSearch({...search, petType: e.target.value})}}
                    />
                </label>
            </div>

            <br/>
            <input type="submit" value="Submit"/>
        </form>
    )
}

export default SearchForm;