import React, {useState} from 'react';
import Layout from "../layout";
import {indexImgWithSearchField} from "./data";
import SearchSection from "../../component/searchSection";
import Content from "../../component/content";

function Home({token}){
    const [results, setResults] = useState([]);

    return (
        <Layout token={token} title="">
            <SearchSection {...indexImgWithSearchField} setResults={setResults} />
            <Content results={results}/>
        </Layout>
    );
}
export default Home;