import React from 'react';
import Layout from "../layout";
import { indexImgWithSearchField} from "./data";
import SearchSection from "../../component/searchSection";

function Home(){

    return (
        <Layout title="This is the home page">
            <SearchSection {...indexImgWithSearchField} />
        </Layout>
    );
}
export default Home;