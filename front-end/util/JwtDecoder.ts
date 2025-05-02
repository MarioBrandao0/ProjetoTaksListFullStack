"use server"
import { jwtDecode } from "jwt-decode";
import { cookies } from "next/headers";


export async function getToken(){
    const cookieStorage = await cookies();
    const token = cookieStorage.get("token")?.value;
    return token;
}

export async function TokenDecorder<T = any>(): Promise<T | null> {
    const token = await getToken();
    
    if(!token) return null;

    const decode = jwtDecode<T>(token);

    return decode || null;
}