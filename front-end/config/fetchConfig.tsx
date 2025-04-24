import { getToken } from "@/util/JwtDecoder";

//Fazer isso aqui
export const BASE_URL = "http://localhost:8080"

export const getHeadersJsonANDToken = () => (
    {'Content-Type': 'application/json', 'Authorization': `Bearer ${getToken()}`}
);

export const getHeadersTOKEN = () => (
    {'Authorization': `Bearer ${getToken()}`}
)