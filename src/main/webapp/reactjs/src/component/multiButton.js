import React, {useState} from 'react';
import {Button} from "react-bootstrap";

function multiButton({label, bWidth}) {

    return (
        <Button variant="success" type="submit"
                style={{ fontFamily: 'Playfair Display',fontSize:"20px", width: {bWidth} }}>
            {label}
        </Button>
    );

}

export default multiButton;