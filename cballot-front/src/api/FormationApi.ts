import type { Teacher } from "../types/Teacher";
import type { Course } from "../types/Course";
import type { CoursesAndTeachers } from "../types/CoursesAndTeachers";

// Initialisation de l'URL pour les endpopints "FormationController"
const COURSE_API_URL = "http://localhost:8000/api/courses";
const TEACHER_API_URL = COURSE_API_URL + "/teachers";
const jwt = localStorage.getItem("jwt");
const token = `Bearer ${jwt}`;


/**
 * FetchCourses
 *
 * @returns CoursesAndTeachers
 * 
 * Renvoie une liste de formation et une liste de formateur
 */
export async function fetchCourses(): Promise<CoursesAndTeachers> {
  try {
    const response = await fetch(COURSE_API_URL, {
      method: "GET",
      headers: {
        "Content-type": "application/json",
        "Authorization": token,
      }
    });
    if (!response.ok) throw new Error("Erreur http");
    return response.json();
  } catch (error) {
    console.error("Erreur fetchCoursesAndTeachers", error);
    throw error;
  }
}

/**
 * FetchCourse
 *
 * @param id idCourse
 * @returns Course
 * 
 * Retourne 1 formation (bien évidemment avec ses formateurs)
 */
export async function fetchCourse(id: number): Promise<Course> {
  try {
    const response = await fetch(`${COURSE_API_URL}/${id}`, {
      method: "GET",
      headers: {
        "Content-type": "application/json",
        "Authorization": token,
      }
    });
    if (!response.ok) throw new Error("Erreur HTTP");
    return response.json();
  } catch (error) {
    console.error("Erreur fetchCourse", error);
    throw error;
  }
}

/**
 * CreateCourse
 *
 * @param course Course
 * @returns
 */
export async function createCourse(
  course: Omit<Course, "id">
): Promise<Course> {
  try {
    const response = await fetch(COURSE_API_URL, {
      method: "POST",
      headers: { 
        "Content-Type": "application/json",
        "Authorization": token
      },
      body: JSON.stringify(course),
    });
    if (!response.ok) throw new Error("Erreur Http");
    return response.json();
  } catch (error) {
    console.error("Erreur createCourse: ", error);
    throw error;
  }
}

/**
 * UpdateCourse
 *
 * @param id idCourse
 * @param course course
 * @returns
 */
export async function updateCourse(
  id: number,
  course: Omit<Course, "id">
): Promise<Course> {
  try {
    const response = await fetch(`${COURSE_API_URL}/${id}`, {
      method: "PATCH",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify(course),
    });
    if (!response.ok) throw new Error("Erreur HTTP");
    return response.json();
  } catch (error) {
    console.error("Erreur updateCourse: ", error);
    throw error;
  }
}

/**
 * DeletCourse
 *
 * @param id idCourse
 */
export async function deleteCourse(id: number): Promise<void> {
  try {
    const response = await fetch(`${COURSE_API_URL}/${id}`, {
      method: "DELETE",
    });
    if (!response.ok) throw new Error("Erreur HTTP");
  } catch (error) {
    console.error("Erreur deleteCourse: ", error);
    throw error;
  }
}

/**
 * FetchTeachers
 *
 * @returns Teachers[]
 */
export async function fetchTeachers(): Promise<Teacher[]> {
  try {
    const response = await fetch(TEACHER_API_URL, {
      method: "GET",
      headers: {
        "Content-Type": "application/json",
        "Authorization": token
      }
    });
    if (!response.ok) throw new Error("Erreur HTTP");
    return response.json();
  } catch (error) {
    console.error("Erreur fetchTeachers: ", error);
    throw error;
  }
}

export async function fetchTeacher(id: string): Promise<Teacher> {
  try {
    const response = await fetch(`${TEACHER_API_URL}/${id}`, {
      method: "GET",
      headers: {
        "Content-Type": "application/json",
        "Authorization": token
      }
    });
    if (!response.ok) throw new Error("Erreur HTTP");
    return response.json();
  } catch (error) {
    console.error("Erreur fetchTeacher: ", error);
    throw error;
  }
}

/**
 * CreateTeacher
 *
 * @param teacher
 * @returns Teacher
 */
export async function createTeacher(
  teacher: Omit<Teacher, "id">
): Promise<Teacher> {
  try {
    const response = await fetch(TEACHER_API_URL, {
      method: "POST",
      headers: { "Content-Type": "application/json",
        "Authorization": token
       },
      body: JSON.stringify(teacher),
    });
    if (!response.ok) throw new Error("Erreur HTTP");
    return response.json();
  } catch (error) {
    console.error("Erreur createTeacher: ", error);
    throw error;
  }
}

/**
 * UpdateTeacher
 *
 * @param id idTeacher
 * @param teacher teacher
 * @returns
 */
export async function updateTeacher(
  id: string,
  teacher: Omit<Teacher, "id">
): Promise<Teacher> {
  try {
    const response = await fetch(`${TEACHER_API_URL}/${id}`, {
      method: "PATCH",
      headers: { "Content-Type": "application/json",
        "Authorization": token
       },
      body: JSON.stringify(teacher),
    });
    if (!response.ok) throw new Error("Erreur HTTP");
    return response.json();
  } catch (error) {
    console.error("Erreur updateTeacher: ", error);
    throw error;
  }
}

/**
 * DeleteTeacher
 *
 * @param id idTeacher
 */
export async function deleteTeacher(id: string): Promise<void> {
  try {
    const response = await fetch(`${TEACHER_API_URL}/${id}`, {
      method: "DELETE",
      headers: {
        "Content-Type": "application/json",
        "Authorization": token
      }
    });
    if (!response.ok) throw new Error("Erreur HTTP");
  } catch (error) {
    console.error("Erreur deleteTeacher: ", error);
    throw error;
  }
}

/**
 * AddTeacherToCourse
 *
 * @param id idCourse
 * @param teachers arrayTeacherIds
 * @returns list Teachers linked to this course
 */
export async function addTeacherToCourse(
  id: number,
  teacherIds: string[]
): Promise<Course> {
  try {
    const response = await fetch(`${COURSE_API_URL}/${id}/teachers`, {
      method: "POST",
      headers: { "Content-Type": "application/json",
        "Authorization": token
       },
      body: JSON.stringify(teacherIds),
    });
    if (!response.ok) {
      throw new Error("Erreur HTTP");
    }

    return await response.json();

    // return await fetchCourse(id);
  } catch (error) {
    console.error("Erreur AddTEacherToCourse: ", error);
    throw error;
  }
}

//RemoveTeacherFromCourse
/**
 *
 * @param id idCourse
 * @param teacherIds arrayTeacherIds
 * @returns
 */
export async function removeTeachersFromCourse(
  id: number,
  teacherIds: string[]
): Promise<Course> {
  try {
    const response = await fetch(`${COURSE_API_URL}/${id}/teachers`, {
      method: "PATCH",
      headers: { "Content-Type": "application/json",
        "Authorization": token
       },
      body: JSON.stringify(teacherIds),
    });
    if (!response.ok) throw new Error("Erreur HTTP");
    return await fetchCourse(id);
  } catch (error) {
    console.error("Erreur RemoveTeachersFromCourse: ", error);
    throw error;
  }
}
