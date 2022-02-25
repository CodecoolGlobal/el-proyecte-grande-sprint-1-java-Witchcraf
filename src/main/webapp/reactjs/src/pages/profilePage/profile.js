import React, {useEffect, useState} from 'react';
import Layout from "../../component/layout";
import ServiceProfile from "../profilePage/serviceProfile";
import UserProfile from "../profilePage/userProfile";
import SaveServiceModal from "./saveServiceModal";
import {Outlet} from "@mui/icons-material";


function Profile(){

    const [isAddServiceForm, setIsAddServiceForm] = useState(false);
    const [displayAddServiceModal, setDisplayAddServiceModal] = useState(false);

    const [user, setUser] = useState({
        name:"",
        age:"",
        email:"",
        reg:"",
        pets: [],
        searches: [],
        role: "",
        services:[]
    })
    let tokenEncoded = window.localStorage.getItem("token");

    const [savedSearch, setSavedSearch] = useState([]);

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
                console.log(userDetails);
                setUser({...user, name: userDetails.username,
                    email: userDetails.email,
                    age: userDetails.age,
                    reg: userDetails.registrationTime,
                    role: userDetails.userType,
                    searches: userDetails.savedSearches,
                    services: userDetails.services
                });
            }
            profile(tokenEncoded)
    }, [tokenEncoded])


    return (
        <Layout>
            <div style={{background: "rgb(168, 230, 205)",
                        minWidth: "100%",
                        minHeight: "100vh"}}>

            {
                user.role === "ADMIN" ?
                    <ServiceProfile
                        user={user}
                        cards={user.services}
                        setDisplayAddServiceModal={setDisplayAddServiceModal}
                    /> :
                    <UserProfile
                        user={user}
                        cards={user.searches}
                        setSavedSearch={setSavedSearch}
                        tokenEncoded={tokenEncoded}
                    />
            }
                <Outlet context={{savedSearch}}/>
                <div>
                    {
                        displayAddServiceModal ?
                            <SaveServiceModal
                                setDisplayAddServiceModal={setDisplayAddServiceModal}
                                username={user.name}
                                user={user}
                                setUser={setUser}
                                tokenEncoded={tokenEncoded}
                            /> : null
                    }

                </div>
            </div>
        </Layout>
    );
}
export default Profile;
