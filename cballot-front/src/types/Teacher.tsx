import type { Course } from "./Course";

export interface Teacher {
    id: string,
    password: string,
    email: string,
    lastname: string,
    firstname: string,
    courses: Course[]
}