import React from 'react';
import styled from "styled-components";
import SearchForm from './searchForm';


function SearchSection({alt,topLine,heading,setResults}){
    return (<Package.Wrapper
            className="container-fluid px-0"
            style={{ backgroundColor: "#a8e6cd" }}
        >
            <div className="row">
                <Package.Box1 className="col-md-6" alt={alt}>
                </Package.Box1>
                <Package.Box2 className="col-md-6" alt={alt}>
                    <Package.Box2Content>
                        <h2 className="mt-5" style={{ fontStyle: "italic", fontFamily: 'Playfair Display', fontSize: "35px"}}>
                            {topLine}
                        </h2>
                        <h1 className="display-3" style={{ fontFamily: 'Playfair Display'}}>{heading}</h1>
                        <br/>
                        <br/>
                        <SearchForm setResults={setResults}/>
                    </Package.Box2Content>
                </Package.Box2>
            </div>
        </Package.Wrapper>
    );
}

const Package = {
    Wrapper: styled.main`
    overflow-x: hidden;
    z-index: 1;
  `,

    Box1: styled.div`
    background-image: url("images/background.jpg");
    background-size: cover;
    background-repeat: no-repeat;
    background-position: top center;
    min-height: 550px;

    @media only screen and (max-width: 40em) {
      order: 2;
      min-height: 300px;
    }
  `,

    Box2: styled.div`
    @media only screen and (max-width: 40em) {
      padding: 50px;
    }
  `,

    Box2Content: styled.div`
    @media only screen and (min-width: 40em) {
      order: 1;
      padding: 50px 115px 0px 50px;
    }
  `
};
export default SearchSection;