import { jwtDecode } from "jwt-decode";


export function getToken(){
    const token = sessionStorage.getItem("token");
    return token
}

export function TokenDecorder<T = any>(): T | null {
    const token = getToken(); 
    
    if (!token) return null;

    return jwtDecode<T>(token);
}