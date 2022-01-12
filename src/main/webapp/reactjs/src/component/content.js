import React from 'react';


function Content({results}){
    let content;
    if (results.length === 0) {
        content = <p>Empty</p>;
    } else {
        content = <p>{results.name}</p>;
    }

    return (
        <div>{content}</div>
    );
}
export default Content;