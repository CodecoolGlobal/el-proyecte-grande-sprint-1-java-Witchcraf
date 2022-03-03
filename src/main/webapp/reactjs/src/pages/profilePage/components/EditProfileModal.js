import React, {useState} from "react";
import Modal from 'react-bootstrap/Modal';
import {FontAwesomeIcon} from "@fortawesome/react-fontawesome";
import {faEnvelope, faUserCircle} from "@fortawesome/free-solid-svg-icons";
import styled from "styled-components";




function EditProfileModal({user, setUser, setDisplayModal}) {
    const [open, setOpen] = useState(true);

    const [inputTextField, setInputTextField] = useState({
        fullname: user.fullname,
        username: user.username,
        email: user.username,
        // password: "",
    });

    async function updateUserInfo(event){
        event.preventDefault();
        const valami = await fetchUserUpdate();
        const akrmi = await handleClose();
    }

    const handleClose = async () => {
        // setOpen(false);
        console.log("f2")
        // setDisplayModal(false);
    }

    const fetchUserUpdate = async () => {
        console.log("f")
        const res = await fetch(`/api/updateuser`,{
            method: 'POST',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(user)
        })
        return await res.json();
    }

    // const profileInputEvent = (event) => {
    //     console.log(event);
    //     // const name = event.target.value;
    //     // let value;
    //     //
    //     // setInputTextField()
    // }

    return (
        <>
            <Modal
                className = "myModal"
                show={open}
                // onHide={handleClose}
                backdrop="static"
                keyboard={false}
            >
                <Modal.Header closeButton>
                    <Modal.Title>Edit User Data</Modal.Title>
                </Modal.Header>
                <Modal.Body className="modalBody">

                    <form>
                        <Package.InputTexts>
                            <Package.InputLabel>Full Name</Package.InputLabel>
                            <FontAwesomeIcon icon={faUserCircle}/>
                            <Package.InputText type="text" value={inputTextField.fullname} onChange={(e) => {setInputTextField({...setInputTextField, fullname: e.target.value})}} autoComplete="off"/>
                            <br />
                        </Package.InputTexts>

                        <Package.InputTexts>
                            <Package.InputLabel>User Name</Package.InputLabel>
                            <FontAwesomeIcon icon={faUserCircle}/>
                            <Package.InputText type="text" value={inputTextField.name} onChange={(e) => {setInputTextField({...setInputTextField, username: e.target.value})}} autoComplete="off"/>
                            <br />
                        </Package.InputTexts>

                        <Package.InputTexts>
                            <Package.InputLabel>Email</Package.InputLabel>
                            <FontAwesomeIcon icon={faEnvelope}/>
                            <Package.InputText type="text" value={inputTextField.email} onChange={(e) => {setInputTextField({...setInputTextField, email: e.target.value})}} autoComplete="off"/>
                            <br />
                        </Package.InputTexts>

                        <Package.Button className="button">
                            <Package.SubmitButton type="submit" onClick={updateUserInfo}>Save</Package.SubmitButton>
                        </Package.Button>
                    </form>

                </Modal.Body>
                <Modal.Footer>
                    <p> </p>
                </Modal.Footer>
            </Modal>
        </>
    );
}

const Package = {
    Wrapper: styled.main`
     padding: 0;
    margin: 0;
    box-sizing: border-box;
 
  `,

    Container: styled.div`
    min-width: 100%;
    min-height: 100vh;
    display: flex;
    justify-content: center;
    align-items: center;
    background: radial-gradient(yellow, green);
    `,

    ContainerCard: styled.div`
       height: 700px;
        width: 930px;
        background-color: #fff;
        position: relative;
        box-shadow: 0 15px 30px rgba(0, 0, 0, 0.1);
        font-family: 'Poppins', sans-serif
    `,

    ContainerForm: styled.div`
        width: 100%;
        height: 100%;
        display: flex
    `,

    CardLeft: styled.div`
        width: 50%;
        background-color: #fff;
        height: 100%;
        overflow: hidden
    `,
    CardImg: styled.img`
        box-sizing: border-box;
        object-fit: cover;
        height: 100%;
        width: 100%
    `,

    CardRight: styled.div`
        width: 50%;
        background-color: #fff;
        height: 100%;
        padding: 20px
    `,
    Head: styled.h3`
    letter-spacing: 1px;
    font-family: 'Playfair Display';
    `,

    HeadP: styled.p`
        margin-top: 5px;
        font-size: 15px;
        color: #898989;
        font-family: 'Playfair Display';
    `,

    Social: styled.div`
        margin-top: 20px;
        display: flex;
        flex-direction: column;
        gap: 20px
    `,

    SocialSpan: styled.span`
        height: 40px;
        width: 100%;
        border: 1px solid #ccc;
        border-radius: 7px;
        display: flex;
        justify-content: center;
        align-items: center;
        font-size: 15px;
        font-weight: 600;
        cursor: pointer;
        font-family: 'Playfair Display';
    `,
    RightSideHr: styled.hr`
    margin-top: 20px
    `,

    Or: styled.div`
     display: flex;
    justify-content: center;
    align-items: center;
    margin-top: -8px;
    `,

    ORP: styled.p`
      background-color: #fff;
    padding: 0 4px;
    font-size: 15px;
    font-weight: 700;
    font-family: 'Playfair Display';
    `,

    InputTexts: styled.div`
        position: relative;
        margin-top: 10px;
        width: 100%;

    `,

    InputLabel: styled.label`
        position: absolute;
        left: 30px;
        font-size: 15px;
        pointer-events: none;
        transition: all 0.5s;
        font-family: 'Playfair Display';
    `,
    InputText: styled.input`
        height: 30px;
        width: 100%;
        border: none;
        border-radius: 7px;
        background-color: #f5f5f5;
        outline: 0;
        padding: 0 10px;
        font-size: 15px;
        padding-left: 30px

    `,

    InputSelect: styled.select`
        height: 30px;
        width: 100%;
        border: none;
        border-radius: 7px;
        background-color: #f5f5f5;
        outline: 0;
        padding: 0 10px;
        font-size: 15px;
        padding-left: 30px

    `,

    RemPass: styled.div`
        display: flex;
        justify-content: space-between;
        align-items: center;
        margin-top: 20px;
    `,
    Remember: styled.div`
        display: flex;
        align-items: center
    `,

    RememberSpan: styled.span`
        height: 25px;
        width: 25px;
        border-radius: 8px;
        border: 1px solid #ccc;
        display: flex;
        justify-content: center;
        align-items: center;
        color: #fff;
        cursor: pointer;
        transition: all 0.5s
    `,

    RememberP: styled.p`
         font-size: 15px;
        margin-left: 5px;
        font-weight: 700
    `,
    ForgorPassWord: styled.a`
       font-size: 15px;
    color: blue;
    text-decoration: none;
    cursor: pointer
    `,
    Button: styled.div`
        display: flex;
        justify-content: center;
        align-items: center;
        margin-top: 20px;
      
    `,

    SubmitButton: styled.button`
        height: 40px;
        width: 100%;
        background-color: #ff805d;
        border: none;
        border-radius: 8px;
        color: #fff;
        cursor: pointer;
        font-size: 23px;
        transition: all 0.5s;
          &:hover {
        background-color: #e33606
        }
        font-family: 'Playfair Display';
    `,
    Register: styled.div`
        margin-top: 10px;
        display: flex;
        justify-content: center;
    `,
    RegisterP: styled.p`
        font-size: 15px;
        font-weight: 700;
        font-family: 'Playfair Display';
    `,
    RegisterA: styled.a`
        color: blue;
        text-decoration: none;
        cursor: pointer;
        font-family: 'Playfair Display';
    `,

    ErrorMsg: styled.div`
        color: red;
        text-align: center;
        font-size: 15px;
        font-family: 'Playfair Display';
    `,


    CheckBoxInput: styled.input`
        margin-left: 10px;
        float: center;
        width: 15px;
        height: 15px;
    `,

}

export default EditProfileModal;