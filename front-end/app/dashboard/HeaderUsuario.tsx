"use client";
import { useEffect, useState } from "react";
import ButtonLogout from "../components/ButtonLogout";
import { TokenDecorder } from "@/util/JwtDecoder";
import ButtonRegisterTask from "../components/ButtonRegisterTask";


export default function HeaderUsuario() {
    const [nome, setNome] = useState<String| null>(null);
    const [loading, setLoading] = useState(true);

    useEffect(() => {
        const nomeUser = TokenDecorder()
        
        setNome(nomeUser.nomeUser)
        setLoading(false)
    })

    return (
        <>
            {!loading && (
                <div className="conteiner">
                    <div className="user-info">
                        <h2>Ola {nome}</h2>
                    </div>
                    <div className="buttons">
                        <ButtonRegisterTask/>
                        <ButtonLogout/>
                    </div>
                </div>
            )}
        </>
    )
}