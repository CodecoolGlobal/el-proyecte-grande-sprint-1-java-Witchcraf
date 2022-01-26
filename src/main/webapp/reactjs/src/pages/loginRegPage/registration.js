import React, {useState} from "react";
import styled from "styled-components";
import Layout from "../layout";
import {FontAwesomeIcon} from '@fortawesome/react-fontawesome'
import {faEnvelope, faEye, faEyeSlash, faLock, faUserCircle} from '@fortawesome/free-solid-svg-icons'
import {useNavigate} from "react-router-dom";

const emailValidator = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
const passwordValidator = /^(?=.*\d)(?=.*[a-z])(?=.*[A-Z])[0-9a-zA-Z]{8,}$/;

function Registration() {
    const navigate = useNavigate();
    const [inpass, setinpass] = useState("password");
    const [eye, seteye] = useState(true);


    const [inputText, setInputText] = useState({
        fullname:"",
        email: "",
        password: ""
    });

    const [wemail, setWEmail] = useState("");
    const [wPassword, setWPassword] = useState("");
    const [wName, setWName] = useState("");

    const handleBlur = (event) => {
        const { name } = event.target;
        validateField(name);
    }

    const validateField = (name) => {
        let isValid = false;
        if (name === "email") isValid = validateEmailAddress();
        else if (name === "password") isValid = validatePassword();
        else if (name === "fullname") isValid = validateName();
        return isValid;
    }


    const validateName = () => {
        let nameError = "";
        const value = inputText.fullname;
        if (value.trim() === "") nameError = "First Name is required";
        setWName(nameError)
        return nameError === "";
    }

    const validateEmailAddress = () => {
        let emailAddressError = "";
        const value = inputText.email;
        if (value.trim === "") emailAddressError = "Email Address is required";
        else if (!emailValidator.test(value))
            emailAddressError = "Email is not valid";
        setWEmail(emailAddressError)
        return emailAddressError === "";

    }

    const validatePassword = () => {
        let passwordError = "";
        const value = inputText.password;
        if (value.trim === "") passwordError = "Password is required";
        else if (!passwordValidator.test(value))
            passwordError =
                "Password must contain at least 8 characters, 1 number, 1 upper and 1 lowercase!";
        setWPassword(passwordError)
        return passwordError === "";
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

    const fetchCheckPreviousReg = async (inputText) => {
        const res = await fetch(`http://localhost:8080/api/checkPreviousReg`,{
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

    const checkPreviousRegistration = async (inputText) => {
        return await fetchCheckPreviousReg(inputText);
    }


    const submitForm = async (e) => {
        e.preventDefault();

        let formFileds = [
            "fullname",
            "email",
            "password",
        ];
        let isValid = true;
        formFileds.forEach(field => {
            isValid = validateField(field) && isValid;
        });

        if (isValid) {
            const isAlreadyRegistered = await checkPreviousRegistration(inputText);
            if(isAlreadyRegistered){
                alert("You have already registered pls Login!")
            }
            else{
                await registerUser(inputText);
                navigate("/login")
            }
        }
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
                                        <Package.InputText type="text"  value={inputText.fullname} onChange={inputEvent} name="fullname"  onBlur={handleBlur}  autoComplete="off"/>
                                        <br />
                                        {wName && (
                                            <Package.ErrorMsg>{wName}</Package.ErrorMsg>
                                        )}
                                    </Package.InputTexts>

                                    <Package.InputTexts>
                                        <Package.InputLabel>Email</Package.InputLabel>
                                        <FontAwesomeIcon icon={faEnvelope}/>

                                        <Package.InputText type="text" value={inputText.email} onChange={inputEvent} name="email"  onBlur={handleBlur}  autoComplete="off"/>
                                        <br />
                                        {wemail && (
                                            <Package.ErrorMsg>{wemail}</Package.ErrorMsg>
                                        )}


                                    </Package.InputTexts>

                                    <Package.InputTexts>
                                        <Package.InputLabel>Password</Package.InputLabel>
                                        <FontAwesomeIcon icon={faLock}/>

                                        <Package.InputText type={inpass}
                                               value={inputText.password} onChange={inputEvent} name="password" autocomplete="current-password"  onBlur={handleBlur}
                                               autoComplete="off" />
                                        <br />
                                        {wPassword && (
                                            <Package.ErrorMsg>{wPassword}</Package.ErrorMsg>
                                        )}
                                        <FontAwesomeIcon onClick={Eye} icon={eye ? faEyeSlash : faEye}/>

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
    letter-spacing: 1px;
    font-family: 'Playfair Display';
    `,

    HeadP: styled.p`
        margin-top: 5px;
        font-size: 12px;
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
        font-size: 12px;
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
    font-size: 10px;
    font-weight: 700;
    font-family: 'Playfair Display';
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
        transition: all 0.5s;
        font-family: 'Playfair Display';
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
        font-size: 20px;
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
        font-size: 12px;
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
        font-size: 12px;
        font-family: 'Playfair Display';
    `,


}

export default Registration;





