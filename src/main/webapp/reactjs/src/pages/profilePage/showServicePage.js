import ShowServiceForm from "./showServiceForm";
import {useOutletContext} from "react-router-dom";


function ShowServicePage() {

    const context = useOutletContext();

    return (
        <ShowServiceForm user={context.user} cards={context.cards}/>
    )
}

export default ShowServicePage;
