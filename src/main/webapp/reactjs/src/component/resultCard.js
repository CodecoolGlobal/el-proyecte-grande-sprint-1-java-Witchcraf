import React from "react";
import "bootstrap/dist/css/bootstrap.min.css";
import '../App.css';
import {Avatar} from "@mui/material";
import RestaurantMenuIcon from '@mui/icons-material/RestaurantMenu';
import SpaIcon from '@mui/icons-material/Spa';
import LocalHospitalIcon from '@mui/icons-material/LocalHospital';
import PetsIcon from '@mui/icons-material/Pets';
import {green, purple, red, yellow} from "@mui/material/colors";

function resultCard({result}){
    let currentAvatar = createAvatarBasedOnServiceType(result.serviceType);

    console.log(result)
    return (
        <div className="container-fluid">
            <div className="row">
                <div className="col-12 mt-3">
                    <div className="card">
                        <div className="card-horizontal">
                            <div className="img-square-wrapper">
                                {currentAvatar}
                            </div>
                            <div className="card-body">
                                <h4 className="card-title" style={{ fontFamily: 'Playfair Display',fontSize:"20px"}}>>{result.name}</h4>
                                <p className="card-text" style={{ fontFamily: 'Playfair Display',fontSize:"20px"}}>>
                                    Some quick example text to build on the card title and make up
                                    the bulk of the card's content.
                                </p>
                                <p>
                                    https://codepen.io/SteveJRobertson/pen/POdvgz
                                </p>
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