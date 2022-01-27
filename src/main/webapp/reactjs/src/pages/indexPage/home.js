import React, {useState} from 'react';
import Layout from "../layout";
import {indexImgWithSearchField} from "./data";
import SearchSection from "../../component/searchSection";
import Content from "../../component/content";

function Home(){
    const [results, setResults] = useState([]);
    const [isResult, setIsResult] = useState(false);

    return (
        <Layout title="">
            <SearchSection {...indexImgWithSearchField} setResults={setResults} setIsResult={setIsResult} />
            <Content results={results} isResult={isResult}/>
        </Layout>
    );
}
export default Home;