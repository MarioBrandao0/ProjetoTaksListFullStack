"use client";
import { BASE_URL, getHeadersJson } from "@/config/fetchConfig";
import "./StyleRegister.css"
import { useState } from "react";

export default function resgiter() {
    const [email, setEmail] = useState("");
    const [senha, setSenha] = useState("");
    const [confirmSenha, setConfirmSenha] = useState("")
    const [nome, setNome] = useState("");
    const [erro, setErro] = useState("");
    const [mensagem, setMensagem] = useState("")

    const fazerRegistro = async(e: React.FormEvent<HTMLFormElement>) => {
        e.preventDefault();

        if(erro != "") {
            setErro("")
        }
        if(senha === confirmSenha) {
            const response = await fetch(`${BASE_URL}/usuarios/register`, {
                method: "POST",
                headers: getHeadersJson(),
                credentials: "include",
                body: JSON.stringify({nome, email, senha})
            })

            if(!response.ok) {
                const error = await response.text();
                await setErro(error)
                return
            }

            const data = await response.text();
            await setMensagem(data)
            await setTimeout(() => {window.location.href = "/"}, 2000)
            return;

        }
        setErro("Senhas diferentes")
    }
        

    return (
        <>
            <form onSubmit={fazerRegistro}>
                <h1>Register</h1>
                {mensagem && <p>{mensagem}</p>}
                <div>
                    <input type="text" placeholder="Nome" value={nome} onChange={(e) => (setNome(e.target.value))} />
                </div>

                <div>
                    <input placeholder="Email" type="text" value={email} onChange={(e) => setEmail(e.target.value)} />
                </div>

                <div>
                    <input placeholder="Senha" type="password" value={senha} onChange={(e) => setSenha(e.target.value)} />
                </div>

                <div>
                    <input type="text" placeholder="Confimar Senha" value={confirmSenha} onChange={(e) => (setConfirmSenha(e.target.value))} />
                </div>

                <button type="submit">Registrar</button>
                { erro && <p style={{color: "red"}}>{erro}</p> }

            </form>
        </>
    )
}