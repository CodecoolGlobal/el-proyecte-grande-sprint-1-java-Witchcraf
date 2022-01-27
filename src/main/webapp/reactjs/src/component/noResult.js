import React from 'react';

function NoResult () {

    return (
        <div className="container-fluid">
            <div className="row justify-content-center">
                <div className="col-8 mt-5">
                    <div className="card">
                        <div className="card-horizontal">
                            <div className="card-body">
                                <h5 className="card-title" style={{marginBottom: "30px", marginTop:"10px", fontFamily: 'Playfair Display',fontSize:"45px"}}>No results found</h5>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    );
}

export default NoResult;