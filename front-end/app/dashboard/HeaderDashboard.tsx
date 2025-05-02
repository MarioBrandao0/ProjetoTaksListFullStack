"use client";
import { useEffect, useState } from "react";
import ButtonLogout from "../../components/ButtonLogout";

import ButtonRegisterTask from "../../components/ButtonRegisterTask";
import { getNomeUser } from "@/lib/functionLogin";


export default function HeaderUsuario() {
    const [nome, setNome] = useState<String| null>(null);
    const [loading, setLoading] = useState(true);

    useEffect(() => {
        const buscarNome = async() => {
            const nome = await getNomeUser()
            setNome(nome)
            setLoading(false)    
        }
        
        buscarNome()
    }, [])

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