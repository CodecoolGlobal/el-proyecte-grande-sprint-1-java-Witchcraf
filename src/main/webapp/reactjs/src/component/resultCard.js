import React from "react";
import "bootstrap/dist/css/bootstrap.min.css";
import '../App.css';
import {Avatar, Box, Button, Rating, Typography} from "@mui/material";
import RestaurantMenuIcon from '@mui/icons-material/RestaurantMenu';
import SpaIcon from '@mui/icons-material/Spa';
import LocalHospitalIcon from '@mui/icons-material/LocalHospital';
import PetsIcon from '@mui/icons-material/Pets';
import {green, purple, red, yellow} from "@mui/material/colors";
import SendIcon from '@mui/icons-material/Send';


function resultCard({result}){
    let currentAvatar = createAvatarBasedOnServiceType(result.serviceType);

    console.log(result)
    return (
        <div className="container-fluid">
            <div className="row justify-content-center">
                <div className="col-8 mt-5">
                    <div className="card">
                        <div className="card-horizontal">
                            <div className="img-square-wrapper">
                                {currentAvatar}
                            </div>
                            <div className="card-body">
                                <h4 className="card-title" style={{marginBottom: "30px", marginTop:"10px", fontFamily: 'Playfair Display',fontSize:"45px"}}>{result.name}</h4>
                                <p style={{ fontFamily: 'Playfair Display',fontSize:"25px"}}>Rating:
                                    <Typography component="legend" ></Typography>
                                    <Rating style={{marginLeft:"10px"}} name="half-rating-read" defaultValue={result.rating} precision={0.5} readOnly />
                                </p>
                                <p className="card-text" style={{ paddingBottom:"15px", fontFamily: 'Playfair Display',fontSize:"20px"}}>
                                    {result.description}
                                </p>
                                <a href={result.serviceHomePage} target="_blank" style={{textDecoration: "none", fontFamily: 'Playfair Display'}}>
                                    <Button variant="outlined" color="error" endIcon={<SendIcon />}>
                                        Go to webpage
                                    </Button>
                                </a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

    );

    function createAvatarBasedOnServiceType(type){
        let avatar;
        if(type === "RESTAURANT"){
            avatar = <Avatar sx={{ bgcolor: yellow[500],  width: 70, height: 70, margin:10 }}>
                <RestaurantMenuIcon />
            </Avatar>
        }
        if(type === "WELLNESS"){
            avatar = <Avatar sx={{ bgcolor: green[500],  width: 70, height: 70, margin:10 }}>
                <SpaIcon />
            </Avatar>
        }
        if(type === "HOSPITAL"){
            avatar = <Avatar  sx={{ color: red[500],  width: 70, height: 70, margin:10 }}>
                <LocalHospitalIcon />
            </Avatar>
        }
        if(type === "SHELTER"){
            avatar = <Avatar sx={{ color: purple[500],  width: 70, height: 70, margin:10 }}>
                <PetsIcon />
            </Avatar>
        }
        return avatar;
    }
}
export default resultCard;