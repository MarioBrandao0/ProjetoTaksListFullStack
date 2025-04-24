import "./ButtonLogout.css"

export default function ButtonLogout() {

    function fazerLogout() {
        sessionStorage.clear()
        window.location.href = "/"
    }



    return (
        <button className="logout-btn" onClick={fazerLogout}>Sair</button>
    )
}