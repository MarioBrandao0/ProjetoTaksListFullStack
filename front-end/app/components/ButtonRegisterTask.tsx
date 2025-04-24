"use client";
import { useState } from "react";
import "./ButtonRegisterTask.css"
import { BASE_URL, getHeadersJsonANDToken } from "@/config/fetchConfig";

export default function ButtonRegisterTask() {
    const [mostrarModal, setVisiModal] = useState(false);
    const [nomeTask, setNomeTask] = useState("");
    const [descricao, setDescricao] = useState("");


    function mostrarPainel() {
        if (mostrarModal) {
            setVisiModal(false);
            return
        }
        setVisiModal(true)
    }

    const registrarTask = async() => {
        const status = false
        const response = await fetch(`${BASE_URL}/tarefas/register`, {
            method: "POST",
            headers: getHeadersJsonANDToken(),
            body: JSON.stringify({nomeTask, descricao, status})
        })
        const data = await response.text()
        await alert(data)
        
    }

    return (
        <>

            <button className="new-task" onClick={() => mostrarPainel()}>Nova Tarefa</button>

            {mostrarModal && (
                <div className="modal-overlay"></div>
            )}

            {mostrarModal && (
                <div className="modal">
                <h2>Registrar Tarefa</h2>
                <form className="form-new-task">
                  <input type="text" placeholder="Nome da Tarefa" value={nomeTask} onChange={(e) => (setNomeTask(e.target.value))} />
                  <textarea placeholder="Descrição da Tarefa" value={descricao} onChange={(e) => (setDescricao(e.target.value))}/>
                  <button type="submit" className="salvar-btn" onClick={() => (registrarTask())}>Salvar</button>
                  <button className="fechar-btn" type="button" onClick={() => mostrarPainel()}>Fechar</button>
                </form>
              </div>
            )}

        </>
    )

}