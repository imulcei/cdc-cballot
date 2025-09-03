import type { Course } from "./Course";
import type { Teacher } from "./Teacher";

export interface CoursesAndTeachers {
    courses: Course[],
    teachers: Teacher[]
}