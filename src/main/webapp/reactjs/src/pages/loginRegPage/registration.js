import React, {useState} from "react";
import styled from "styled-components";
import Layout from "../layout";
import {FontAwesomeIcon} from '@fortawesome/react-fontawesome'
import {faEnvelope, faLock, faUserCircle} from '@fortawesome/free-solid-svg-icons'
import {useNavigate} from "react-router-dom";


function Registration() {
    const navigate = useNavigate();
    const [user, setUser] = useState([]);
    const [eye, seteye] = useState(true);
    const [inpass, setinpass] = useState("password");
    const [warning, setwarning] = useState(false);
    const [tick, settick] = useState(false);

    const [inputText, setInputText] = useState({
        fullname:"",
        email: "",
        password: ""
    });

    const [wemail, setwemail] = useState(false);
    const [wpassword, setwpassword] = useState(false);


    const inputEvent = (event) => {
        const name = event.target.name;
        const value = event.target.value;
        setInputText((lastValue) => {
            return {
                ...lastValue,
                [name]: value
            }
        });

    }

    const fetchResults = async (inputText) => {
        const res = await fetch(`http://localhost:8080/api/registerUser`,{
            method: 'POST',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(inputText)
        })
        return await res.json();
    }

    const registerUser = async (inputText) => {
        return await fetchResults(inputText);
    }

    const submitForm = async (e) => {
        e.preventDefault();
        setwemail(false);
        setwpassword(false);
        if (inputText.email == "") {
            setwemail(true);
        } else if (inputText.password == "")
            setwpassword(true);
        else {
            await registerUser(inputText);
            navigate("/login")
        }
    }


    return (
        <Layout >
            <Package.Wrapper>
                <Package.Container>
                    <Package.ContainerCard>
                        <Package.ContainerForm >
                            <Package.CardLeft>
                                <Package.CardImg src="images/reg3.jpg"/>
                            </Package.CardLeft>
                            <Package.CardRight>
                                <Package.Head>Registration</Package.Head>
                                <Package.HeadP>Welcome to PawPrint!</Package.HeadP>

                                <Package.RightSideHr/>

                                <form onSubmit={submitForm}>
                                    <Package.InputTexts>
                                        <Package.InputLabel>FullName</Package.InputLabel>
                                        <FontAwesomeIcon icon={faUserCircle}/>
                                        <Package.InputText type="text" className={`${wemail ? "text-warning" : ""}`}
                                               value={inputText.fullname} onChange={inputEvent} name="fullname"/>

                                    </Package.InputTexts>

                                    <Package.InputTexts>
                                        <Package.InputLabel>Email</Package.InputLabel>
                                        <FontAwesomeIcon icon={faEnvelope}/>

                                        <Package.InputText type="text" className={`${wemail ? "text-warning" : ""}`}
                                               value={inputText.email} onChange={inputEvent} name="email"/>


                                    </Package.InputTexts>

                                    <Package.InputTexts>
                                        <Package.InputLabel>Password</Package.InputLabel>
                                        <FontAwesomeIcon icon={faLock}/>

                                        <Package.InputText type={inpass}
                                               className={` ${warning ? "warning" : ""} ${wpassword ? "text-warning" : ""}`}
                                               value={inputText.password} onChange={inputEvent} name="password" autocomplete="current-password"
                                        />

                                    </Package.InputTexts >


                                    <Package.Button className="button">
                                        <Package.SubmitButton type="submit">Register</Package.SubmitButton>
                                    </Package.Button>
                                </form>

                                <Package.Register className="register">
                                    <Package.RegisterP>Have you already an account?
                                        <Package.RegisterA href="/login"> Login</Package.RegisterA>
                                    </Package.RegisterP>
                                </Package.Register>


                            </Package.CardRight>
                        </Package.ContainerForm>
                    </Package.ContainerCard>
                </Package.Container>
            </Package.Wrapper>

        </Layout>
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
        height: 610px;
        width: 800px;
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
    letter-spacing: 1px
    `,

    HeadP: styled.p`
        margin-top: 5px;
        font-size: 12px;
        color: #898989
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
        font-size: 12px;
        font-weight: 600;
        cursor: pointer;
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

    ORP: styled.div`
      background-color: #fff;
    padding: 0 4px;
    font-size: 10px;
    font-weight: 700;
    `,

    InputTexts: styled.div`
        position: relative;
        margin-top: 30px;
        width: 100%;

    `,

    InputLabel: styled.label`
        position: absolute;
        left: 30px;
        font-size: 12px;
        pointer-events: none;
        transition: all 0.5s
    `,
    InputText: styled.input`
          height: 45px;
    width: 100%;
    border: none;
    border-radius: 7px;
    background-color: #f5f5f5;
    outline: 0;
    padding: 0 10px;
    font-size: 13px;
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
         font-size: 12px;
        margin-left: 5px;
        font-weight: 700
    `,
    ForgorPassWord: styled.a`
       font-size: 12px;
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
        font-size: 12px;
        transition: all 0.5s;
          &:hover {
        background-color: #e33606
        }
    `,
    Register: styled.div`
        margin-top: 10px;
        display: flex;
        justify-content: center;
    `,
    RegisterP: styled.p`
        font-size: 10px;
        font-weight: 700
    `,
    RegisterA: styled.a`
        color: blue;
        text-decoration: none;
        cursor: pointer;
    `,

    WarningStyle: styled.a`
      border: 1px solid red !important
    `,




    /*
    .warning {
        border: 1px solid red !important
    }

    .green {
        background-color: green !important
    }

    .text-warning {
        border: 1px solid red !important
    }

    @media (max-width:750px) {
    .container .card {
            max-width: 350px;
            height: auto
        }

    .container .card .right-side {
            width: 100%
        }

    .container .card .left-side {
            display: none
        }
    }*/

}

export default Registration;





