import React, {useState} from "react";
import Cards from "./components/cards";
import ResultCard from "../indexPage/components/resultCard";
import {Form} from "react-bootstrap";
import EditProfileModal from "./components/EditProfileModal"


function UserProfile({user, cards}) {
    const [displayModal, setDisplayModal] = useState(false);
    const [open, setOpen] = useState(true);


    async function submit(event) {
        event.preventDefault();
        setDisplayModal(true);
    }

    return (
        <section className="h-100 gradient-custom-2">
            <div className="container py-5 h-100">
                <div className="row d-flex justify-content-center align-items-center h-100">
                    <div className="col col-lg-9 col-xl-7">
                        <div className="card">
                            <div className="rounded-top text-white d-flex flex-row"
                                 style={{backgroundColor: "#000",  height:"200px"}}>
                                <div className="ms-4 mt-5 d-flex flex-column" style={{height:"150px"}}>
                                    <img
                                        src="https://mdbcdn.b-cdn.net/img/Photos/new-templates/bootstrap-chat/ava1-bg.webp"
                                        alt="Generic placeholder image"
                                        className="img-fluid img-thumbnail mt-4 mb-2"
                                        style={{width: "150px",  zIndex:"1"}}/>
                                    <button type="button" className="btn btn-outline-dark"
                                            data-mdb-ripple-color="dark" style={{zIndex: "1"}} onClick={submit}>
                                        Edit profile
                                    </button>
                                </div>
                                <div className="ms-3" style={{marginTop: "130px"}}>
                                    <h5>{user.name}</h5>
                                </div>
                            </div>
                            <br/><br/><br/>
                            <div className="card-body p-4 text-black">
                                <div className="mb-5">
                                    <p className="lead fw-normal mb-1">About</p>
                                    <div className="p-4" style={{backgroundColor: "#f8f9fa"}}>
                                        <p className="font-italic mb-1">{user.age}</p>
                                        <p className="font-italic mb-1">{user.email}</p>
                                    </div>
                                </div>
                                <div className="d-flex justify-content-between align-items-center mb-4">
                                    <p className="lead fw-normal mb-0">Saved Searches:</p>
                                </div>
                                <div className="row g-2">
                                    {cards.map((details, index) => (
                                        <Cards
                                            user={user}
                                            details={details}
                                        />
                                    ))}
                                </div>
                            </div>
                            <div>
                                {
                                    displayModal ?
                                        <EditProfileModal
                                            user={user}
                                            setUser={setUser}
                                            setDisplayModal={setDisplayModal}
                                            open={open}
                                            setOpen={setOpen}
                                        /> : null
                                }
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>
    );
}

export default UserProfile;