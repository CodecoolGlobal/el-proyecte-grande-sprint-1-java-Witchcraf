import {useOutletContext} from "react-router-dom";
import ShowSearchForm from "./showSearchForm";


function ShowSearchPage() {

    const context = useOutletContext();

    console.log("rendering show search page");
    return (
        <ShowSearchForm savedSearch={context.savedSearch}/>
    )
}

export default ShowSearchPage;
