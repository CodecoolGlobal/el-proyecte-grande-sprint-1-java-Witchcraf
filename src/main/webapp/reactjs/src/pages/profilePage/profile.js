import React, {useEffect, useState} from 'react';
import Layout from "../layout";
import ServiceProfile from "../profilePage/serviceProfile";
import UserProfile from "../profilePage/userProfile";

function Profile(){
    //edit + info
    const [user, setUser] = useState({
        name:"",
        age:"",
        email:"",
        reg:"",
        pets: [],
        searches: [],
        role: ""
    })
    let tokenEncoded = window.localStorage.getItem("token");
    console.log(tokenEncoded)

    useEffect(()=>{
            const profile = async (tokenEncoded) => {
                const res = await fetch(`/api/getuseralldata`, {
                    method: 'GET',
                    headers: {
                        'Content-Type': 'application/x-www-form-urlencoded',
                        //add token to header?
                        'Authorization': tokenEncoded,
                    },
                })
                let userDetails = await res.json();
                console.log(userDetails)
                setUser({...user, name: userDetails.username,
                    email: userDetails.email,
                    age: userDetails.age,
                    reg: userDetails.registrationTime,
                    role: userDetails.userType
                });
            }
            profile(tokenEncoded)
    }, [tokenEncoded])


    return (
        <Layout>
            {user.role === "ADMIN" ?
                <ServiceProfile /> :
                <UserProfile user={user}/>
            }
        </Layout>
    );
}
export default Profile;