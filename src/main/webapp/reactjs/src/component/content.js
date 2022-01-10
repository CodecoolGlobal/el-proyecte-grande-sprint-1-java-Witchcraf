import React from 'react';


function Content({users}){
    return (
        <div id="worker-card-container">
            {users.map((user, index) => (
                <p key={index}>{user.userName} </p>))}
        </div>

    );
}
export default Content;