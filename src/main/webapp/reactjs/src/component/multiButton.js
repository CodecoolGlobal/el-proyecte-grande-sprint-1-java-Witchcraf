import React from 'react';
import {Button} from "react-bootstrap";

function MultiButton({label, bWidth}) {

    return (
        <Button variant="success" type="submit"
                style={{ fontFamily: 'Playfair Display',fontSize:"20px", width: {bWidth} }}>
            {label}
        </Button>
    );

}

export default MultiButton;