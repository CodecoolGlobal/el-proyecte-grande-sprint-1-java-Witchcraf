import React, { useState } from "react";
import styled from "styled-components";
import Layout from "../layout";
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome'
import {faEnvelope, faEye, faEyeSlash, faLock} from '@fortawesome/free-solid-svg-icons'
import {useNavigate} from "react-router-dom";
import {faFacebook, faGoogle} from "@fortawesome/free-brands-svg-icons";
import jwt from 'jwt-decode'
import {Alert} from "@mui/material";

const passwordValidator = /^(?=.*\d)(?=.*[a-z])(?=.*[A-Z])[0-9a-zA-Z]{8,}$/;

function Login({setToken}) {
    const navigate = useNavigate();
    const [eye, seteye] = useState(true);
    const [inpass, setinpass] = useState("password");
    const [isShow, setIsShow] = useState(false);
    const [inputText, setInputText] = useState({
        username: "",
        password: ""
    });

    const [wemail, setWEmail] = useState("");
    const [wPassword, setWPassword] = useState("");

    const handleBlur = (event) => {
        const { name } = event.target;
        validateField(name);
    }

    const validateField = (name) => {
        let isValid = false;
        if (name === "username") isValid = validateName();
        else if (name === "password") isValid = validatePassword();
        return isValid;
    }


    const validateName = () => {
        let nameError = "";
        const value = inputText.username;
        if (value.trim() === "") nameError = "First Name is required";
        setWEmail(nameError)
        return nameError === "";
    }


    const validatePassword = () => {
        let passwordError = "";
        const value = inputText.password;
        if (value.trim() === "") passwordError = "Password is required";
        else if (!passwordValidator.test(value))
            passwordError =
                "Password is wrong format, please try again!";
        setWPassword(passwordError)
        return passwordError === "";

    }

    const Eye = () => {
        if (inpass === "password") {
            setinpass("text");
            seteye(false);
        } else {
            setinpass("password");
            seteye(true);
        }
    }


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
        const formData = new FormData();
        formData.append('username', inputText.username)
        formData.append('password', inputText.password)
        let query = new URLSearchParams();
        for (const item of formData){
            query.append(item[0], item[1])
        }
        let data;
        const res = await fetch(`/login`,{
            method: 'POST',
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded',
                'Authorization': 'Basic '+btoa('username:password'),
            },
            body: query
        }).then(function(response) {
            if (response.status === 401) {
                // do what you need to do here
                setIsShow(true)
                console.log("wrong")
            }
            else{
                data = response.json();
            }

        })
        return await data;
    }

    const checkUserInBackend = async (inputText) => {
        const details = await fetchResults(inputText);
        const access_token = details.access_token;
        //window.localStorage.setItem("token", access_token)
        //console.log(access_token)
        //console.log(details)

        const access = jwt(access_token);
        //const refresh = jwt(refresh_token);
        //window.localStorage.setItem("token", access);
        //window.localStorage.setItem("username", access.sub);

        window.localStorage.setItem("token",access_token);
        console.log(access)

        setToken(access)
        return details;
    }

    const checkIsInputFill = () => {
        let formFields = [
            "username",
            "password",
        ];
        let isValid = true;
        formFields.forEach(field => {
            isValid = validateField(field) && isValid;
        });
        return isValid;
    }



    const submitForm = async (e) => {
        e.preventDefault();
        setIsShow(false)
        const isFill = checkIsInputFill();
        if(isFill){
            const isValidLogin = await checkUserInBackend(inputText);
            if(isValidLogin){
                navigate("/")
            }
        }
    }


    return (
        <Layout >
            <Package.Wrapper>
            <Package.Container>
                <Package.ContainerCard>
                    <Package.ContainerForm>
                        <Package.CardLeft>
                            <Package.CardImg src="images/log3.jpg"/>
                        </Package.CardLeft>
                        <Package.CardRight>
                                <Package.Head>Log in to PawPrint!</Package.Head>
                                <Package.HeadP>Welcome Back! login with your data that you entered during registration.</Package.HeadP>
                            <Package.Social>

                                <Package.SocialSpan><FontAwesomeIcon icon={faGoogle} color="#0669e3" fontSize="13px"/>
                                    Log in with Google</Package.SocialSpan>
                                <Package.SocialSpan><FontAwesomeIcon icon={faFacebook} color="#0669e3" fontSize="13px"/>Log in with Facebook</Package.SocialSpan>
                            </Package.Social>
                            <Package.RightSideHr/>
                            <Package.Or>
                                <Package.ORP>or</Package.ORP>
                            </Package.Or>

                            <form onSubmit={submitForm}>
                                <Package.InputTexts>
                                    <Package.InputLabel>UserName</Package.InputLabel>
                                    <FontAwesomeIcon icon={faEnvelope}/>
                                    <Package.InputText  type="text"
                                                        value={inputText.username}
                                                        onChange={inputEvent} name="username"
                                                        onBlur={handleBlur}
                                                        autoComplete="off"
                                                        />
                                    <br />
                                    {wemail && (
                                        <Package.ErrorMsg>{wemail}</Package.ErrorMsg>
                                    )}
                                </Package.InputTexts>


                                <Package.InputTexts>
                                    <Package.InputLabel>Password</Package.InputLabel>
                                    <FontAwesomeIcon icon={faLock}/>
                                    <Package.InputText type={inpass}
                                                       value={inputText.password}
                                                       onChange={inputEvent}
                                                       name="password"
                                                       onBlur={handleBlur}
                                                       autoComplete="off"
                                    />
                                    <br />
                                    {wPassword && (
                                        <Package.ErrorMsg>{wPassword}</Package.ErrorMsg>
                                    )}
                                    <FontAwesomeIcon onClick={Eye} icon={eye ? faEyeSlash : faEye}/>
                                </Package.InputTexts >


                                <Package.RemPass>
                                        <Package.ForgotPassWord href="#">Forgot your password?</Package.ForgotPassWord>
                                </Package.RemPass>

                                <Package.Button>
                                    <Package.SubmitButton type="submit">Login</Package.SubmitButton>
                                </Package.Button>
                            </form>

                            <Package.Register>
                                <Package.RegisterP>Didn't have an account?
                                    <Package.RegisterA href="/registration"> Register</Package.RegisterA>
                                </Package.RegisterP>
                            </Package.Register>
                            {isShow ? (
                                <>
                                    <Alert  variant="outlined" severity="error" onClose={() => setIsShow(false)}>
                                        Email or password invalid!!</Alert>
                                </>
                            ):(<></>)}


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
       height: 770px;
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
        font-size: 20px;
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
    font-family: 'Playfair Display';
    `,

    ORP: styled.div`
      background-color: #fff;
    padding: 0 4px;
    font-size: 15px;
    font-weight: 700;
    `,

    InputTexts: styled.div`
        position: relative;
        margin-top: 5px;
        width: 100%;

    `,
    InputText: styled.input`
        height: 45px;
        width: 100%;
        border: none;
        border-radius: 7px;
        background-color: #f5f5f5;
        outline: 0;
        padding: 0 5px;
        font-size: 15px;
        padding-left: 30px

    `,

    InputLabel: styled.label`
        position: absolute;
        left: 30px;
        font-size: 15px;
        pointer-events: none;
        transition: all 0.5s;
        font-family: 'Playfair Display';
    `,

    RemPass: styled.div`
        display: flex;
        justify-content: space-between;
        align-items: center;
        margin-top: 20px;
    `,


    ForgotPassWord: styled.a`
       font-size: 15px;
       color: blue;
       text-decoration: none;
       cursor: pointer;
       font-family: 'Playfair Display';
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

}

export default Login;





