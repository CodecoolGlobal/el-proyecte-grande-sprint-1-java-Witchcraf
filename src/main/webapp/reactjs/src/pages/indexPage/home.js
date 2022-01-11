import React, {useEffect, useState} from 'react';
import Layout from "../layout";
import {indexImgWithSearchField} from "./data";
import SearchSection from "../../component/searchSection";
import Content from "../../component/content";

function Home(){
    const [users, setUsers] = useState([]);

    useEffect(() => {
        const getUsers = async () => {
            const usersFromServer = await fetchAllUser();
            setUsers(usersFromServer);

        }
        getUsers()
    }, [])

    // Fetch Users
    const fetchAllUser = async () => {
        const res = await fetch( `http://localhost:8080/api/user/all`);
        return await res.json();
    }

    return (
        <Layout title="This is the home page">
            <SearchSection {...indexImgWithSearchField} />
            <Content users={users} />
        </Layout>
    );
}
export default Home;