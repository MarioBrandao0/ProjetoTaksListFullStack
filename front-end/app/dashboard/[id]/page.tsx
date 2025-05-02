import UsersTasks from "@/components/UsersDashboardTask";
import "../StyleDashboard.css"
//import { TokenDecorder } from "@/util/JwtDecoder";
import { redirect } from "next/navigation";
import { BASE_URL } from "@/config/fetchConfig";

type Params = {
    params: {
        id: number
    }
}

export default async function PageDashboard({params}: Params) {

    const response = await fetch(`${BASE_URL}/tarefas/recuperarTasks`, {
        method: "GET",
        credentials: "include"
    });
    const data = await response.json();




    return (
        <>
            <UsersTasks tasks={data} />
        </>
    )
}