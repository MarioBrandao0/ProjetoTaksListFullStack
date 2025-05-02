"use client";
import { useEffect, useState } from "react"
import { BASE_URL } from "@/config/fetchConfig";
import { useRouter } from "next/navigation";



export default function UsersTasks({tasks}: {tasks: any[] }) {
    const router = useRouter()
    const [mostrar, setMostrar] = useState<number[]>([]);
    const [loading, setLoading] = useState(true);

   function mostrarDetalhes(id: number) {
        if(mostrar.includes(id)) {
            setMostrar(mostrar.filter(item => item !== id))
        }
        else {
            setMostrar([...mostrar, id])
        }
   }


    useEffect(() => {
        if(tasks) {
            setLoading(false)
        }
    }, [])

   function excluirTask(taskName: String, idTaks: number) {
        const apagar = async() => {
            try {
                const response = await fetch(`http://localhost:8080/tarefas/deletarTask/${idTaks}`, {
                    method: "DELETE",
                    credentials: "include"
                })
                alert(`Tarefa: ${taskName} excluida com sucesso`)
                await router.refresh()
            }
            catch (err) {
                return "Houve um erro"
            }
            
        }
        apagar();
   }


   const updateTask = async(idTaks: number) => {
        const response = await fetch(`${BASE_URL}/tarefas/update/${idTaks}`, {
            method: "PUT",
            credentials: "include"
        })
        if(!response.ok) {
            const data = await response.text()
            await alert(data)
        }
        await router.refresh()
   }



   
    return (
        <div className="dashboard-background">
            <div className="dashboard-container">
                <section className="task-list">
                    <h2>Minhas tarefas</h2>
                    {loading ? ("") : 
                    tasks.length > 0 ? (
                    <div className="task-cards">
                        {tasks.map((task) => (
                            <div className={`task-card ${task.status ? 'completed' : ''}`} key={task.id}>
                                <h3>{task.nomeTask}</h3>
                                <div className="botoes">
                                    <button className="detalhes-btn" onClick={() => mostrarDetalhes(task.id)}>Detalhes</button>
                                    <button className="excluir" onClick={() => excluirTask(task.nomeTask, task.id)}>Excluir</button>
                                </div>

                                {mostrar.includes(task.id) && (
                                    <div className="detalhes">
                                        <p>{task.descricao}</p>
                                        <span className="status">
                                            {task.status ? '✅ Concluída' : '🕓 Pendente'}
                                        </span>
                                        {task.status ? (
                                            <div className="button-desmarcar">
                                                <button className="desmarcar-btn" onClick={() => updateTask(task.id)}>Desmarcar</button>
                                            </div>
                                        ) : (
                                            <div className="button-concluir">
                                                <button className="concluir-btn" onClick={() => updateTask(task.id)}>Concluir</button>
                                            </div>
                                        )}
                                    </div>
                                )}

                            </div>
                        ))}
                    </div>
                ) : (
                    <div className="sem-tarefas">
                        <h1>Você não tem tarefas, comece adicionando uma</h1>
                    </div>
                )}
                </section>

            </div>
            
        </div>
    )
}