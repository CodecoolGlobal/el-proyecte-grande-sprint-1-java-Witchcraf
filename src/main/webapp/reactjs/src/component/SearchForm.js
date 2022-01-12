import React, {useState} from 'react';

function SearchForm () {
    const [country, setCountry] = useState("");
    const [result, setResult] = useState(null);


    //addSearch
    const addSearch = async (country) => {
        const res = await fetch('http://localhost:8080/api/service', {
            method: 'POST',
            headers: {
                'Content-type': 'application/json',
            },
            body: JSON.stringify(country),
        })

        const data = await res.json()
        console.log(res)
        console.log(data)

        setResult([...result, data])
    }


    const handleChange = (event) => {
        setCountry(event.target.value)

    }

    const handleSubmit = (event) => {
        event.preventDefault();
        addSearch(country);
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