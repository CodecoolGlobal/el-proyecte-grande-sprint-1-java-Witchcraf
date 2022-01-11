import React, {useEffect, useState} from 'react';
import Layout from "../layout";
import {indexImgWithSearchField} from "./data";
import SearchSection from "../../component/searchSection";
import Content from "../../component/content";

function Home(){

    return (
        <Layout title="">
            <SearchSection {...indexImgWithSearchField} />
            <Content />
        </Layout>
    );
}
export default Home;