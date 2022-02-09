import React, {useState} from 'react';
import Layout from "./layout";

function Profile({token}){
    const [user, setUser] = useState({
        name:"",
        age:""
    })
    let tokenEncoded = window.localStorage.getItem("token");

    async function profilFetch(tokenEncoded) {
        const res = await fetch(`/api/getuseralldata`, {
            method: 'GET',
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded',
                //add token to header?
                'Authorization': tokenEncoded,
            },
        })
        let userDetails = await res.json();
        setUser({...user, name: userDetails.username});
    }

    profilFetch(tokenEncoded)

    return (
        <Layout >
          <a>{user.name}</a>
        </Layout>
    );
}
export default Profile;