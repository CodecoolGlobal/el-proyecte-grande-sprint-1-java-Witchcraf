import React, {useState} from 'react';
import Layout from "../layout";
import {indexImgWithSearchField} from "./data";
import SearchSection from "../../component/searchSection";
import Content from "../../component/content";

function Home(){
    const [results, setResults] = useState([]);

    return (
        <Layout title="">
            <SearchSection {...indexImgWithSearchField} setResults={setResults} />
            <Content results={results}/>
        </Layout>
    );
}
export default Home;