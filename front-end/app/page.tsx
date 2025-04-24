"use client";
import "./styles/paginaTasks.css";
import { useEffect, useState } from "react";

export default function Home() {
  
  
  const [erro, setErro] = useState<String | null> (null);  
  const [email, setEmail] = useState("");
  const [senha, setSenha] = useState("");

  const fazerLogin = async(e: React.FormEvent<HTMLFormElement>) => {
    e.preventDefault();
    try {
      if(email != '' && senha != '') {
        const response = (await fetch("http://localhost:8080/usuarios/login", {
          method: "POST",
          headers: { 'Content-Type': 'application/json' },
          body: JSON.stringify({email, senha})
        }));
  
        const data = await response.json();
        console.log(data)
        
        if(data.sucess) {
          console.log("Logado com sucesso")
          sessionStorage.setItem("token", data.token)
          setErro(null)
          window.location.href = "dashboard"
          
        }
  
        else {
          setErro("Email ou senha invalidos")
          return;
        }
  
      }

      else {
        setErro("Preencha todos os campos")
        return
      }

    }
    catch (err) {
      console.log(err)
      setErro("Erro ao conectar com o servidor")
    }
};
  
  return (
    <>
    <form onSubmit={fazerLogin}>
      <h1>Login</h1>
      <div>
        <input placeholder="Email" type="text" value={email} onChange={(e) => setEmail(e.target.value)} />
      </div>

      <div>
        <input placeholder="Senha" type="password" value={senha} onChange={(e) => setSenha(e.target.value)} />
      </div>

      <button type="submit">Entrar</button>
      { erro && <p style={{color: "red"}}>{erro}</p> }

    </form>

    </>
);
}
