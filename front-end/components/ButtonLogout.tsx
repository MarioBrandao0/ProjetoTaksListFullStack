"use client";
import { useRouter } from "next/navigation";
import "./styles/ButtonLogout.css";

export default function ButtonLogout() {
    const router = useRouter();
    router.prefetch("/")

    function fazerLogout() {
        sessionStorage.clear()
        router.push("/");
    };

    return (
        <button className="logout-btn" onClick={fazerLogout}>Sair</button>
    );
}