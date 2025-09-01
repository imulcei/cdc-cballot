import type { Session } from "./Session"
import type { Teacher } from "./Teacher"

export interface Course {
    id : number,
    libelle: string,
    teachers: Teacher[],
    sessions: Session[]
}