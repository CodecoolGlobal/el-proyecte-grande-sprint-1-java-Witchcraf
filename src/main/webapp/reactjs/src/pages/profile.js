import React, {useState} from 'react';
import Layout from "./layout";

function Profile({token}){
    const [user, setUser] = useState("Tamás")



    const details = async (token) => {
        const res = await fetch(`/api/getalluserdata`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded',
                //add token to header?
                'Authorization': 'Basic ' + btoa('username:password'),
            },
        })
        return res;
    }

    console.log(details(token))





    return (
        <Layout >
            <p>Hello {user}</p>
        </Layout>
    );
}
export default Profile;