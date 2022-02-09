import React, {useState} from 'react';
import Layout from "./layout";

function Profile({token}){
    const [user, setUser] = useState({
        name:"",
        age:""
    })
    let tokenEncoded = window.localStorage.getItem("token");

    const details = async (tokenEncoded) => {
        const res = await fetch(`/api/getuseralldata`, {
            method: 'GET',
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded',
                //add token to header?
                'Authorization': tokenEncoded,
            },
        })
        return res.json();
    }

    let userDetails = details(tokenEncoded);
    setUser({...user, name: userDetails.username});





    return (
        <Layout >
          <p>{user.name}</p>

        </Layout>
    );
}
export default Profile;