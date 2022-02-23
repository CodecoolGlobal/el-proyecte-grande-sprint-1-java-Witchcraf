import React, {useEffect, useState} from 'react';
import Layout from "../../component/layout";
import ServiceProfile from "../profilePage/serviceProfile";
import UserProfile from "../profilePage/userProfile";
import SaveServiceModal from "./saveServiceModal";


function Profile(){

    const [isAddServiceForm, setIsAddServiceForm] = useState(false);
    const [displayAddServiceModal, setDisplayAddServiceModal] = useState(false);
    const [open, setOpen] = useState(true);

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
            <div style={{background: "radial-gradient(yellow, green)",
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
                    />
            }
                {/*<Outlet context={{user, cards:user.services}}/>*/}
                <div>
                    {
                        displayAddServiceModal ?
                            <SaveServiceModal
                                setDisplayAddServiceModal={setDisplayAddServiceModal}
                                open={open}
                                setOpen={setOpen}
                                username={user.name}
                            /> : null
                    }

                </div>
            </div>
        </Layout>
    );
}
export default Profile;
