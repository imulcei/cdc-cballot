import type { Pair } from "./Pair";
import type { Session } from "./Session";

export interface Student {
    id: string,
    email: string,
    lastname: string,
    firstname: string,
    pair?: Pair,
    session?: Session
}