import type { Course } from "./Course"
import type { Student } from "./Student"

export interface Session {
    id: number,
    name: string,
    start_date: Date,
    end_date: Date,
    course: Course,
    students?: Student[]
}