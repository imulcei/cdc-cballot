import type { Election } from "./Election";
import type { Student } from "./Student";

export interface Pair {
    id: number,
    counter?: number,
    election: Election | null,
    students?: Student[]
}