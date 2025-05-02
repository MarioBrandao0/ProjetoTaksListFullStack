"use server";
import { TokenDecorder } from "@/util/JwtDecoder";
;

export const getIdUser = async() => {
    const token = await TokenDecorder();
    return token.iduser
}

export const getNomeUser = async() => {
    const token = await TokenDecorder()
    return token.nomeUser
}