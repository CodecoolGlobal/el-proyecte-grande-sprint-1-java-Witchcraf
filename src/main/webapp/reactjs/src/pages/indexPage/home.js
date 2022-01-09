import React, {useEffect, useState} from 'react';
import Layout from "../layout";
import { indexImgWithSearchField} from "./data";
import SearchSection from "../../component/searchSection";

function Home(){
    const [users, setUsers] = useState([]);

    /*useEffect(() => {
        const getUsers = async () => {
            const usersFromServer = await fetchAllUser();
            setUsers(usersFromServer)

        }
        getUsers()
    }, [users])*/

    // Fetch Users
    const fetchAllUser = async () => {
        const res = await fetch( `/api/getAll`).then(res => console.log(res))
        //console.log(res)
        /*const data = await res.json()
        console.log(data)*/
        //return res;
    }
    fetchAllUser()



    return (
        <Layout title="This is the home page">
            <SearchSection {...indexImgWithSearchField} />
            <>
                {users.map((user, index) => (
                    <p key={index}>{user.name}</p>
                ))}
            </>
        </Layout>
    );
}
export default Home;