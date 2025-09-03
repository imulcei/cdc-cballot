import type { Session } from "../types/Session";
import type { Student } from "../types/Student";

const SESSION_API_URL = "http://localhost:8000/api/sessions";
const STUDENT_API_URL = SESSION_API_URL + "/students";

/**
 * FetchStudents
 *
 * @returns students[]
 */
export async function fetchStudents(): Promise<Student[]> {
  try {
    const response = await fetch(SESSION_API_URL);
    if (!response.ok) throw new Error("Erreur HTTP");
    return response.json();
  } catch (error) {
    console.error("Erreur fetchStudents: ", error);
    throw error;
  }
}

/**
 * FetchSessions
 *
 * @returns Sessions[]
 */
export async function fetchSessions(): Promise<Session[]> {
  try {
    const response = await fetch(SESSION_API_URL + "/all");
    if (!response.ok) throw new Error("Erreur HTTP");
    return response.json();
  } catch (error) {
    console.error("Erreur fetchSessions: ", error);
    throw error;
  }
}

/**
 * CreateSession
 *
 * @param session session + students[]
 * @returns Session
 */
export async function createSession(session: Session): Promise<Session> {
  try {
    const response = await fetch(SESSION_API_URL, {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify(session),
    });
    if (!response.ok) throw new Error("Erreur HTTP");
    return response.json();
  } catch (error) {
    console.error("Erreur CreateSession");
    throw error;
  }
}

/**
 * FetchSession
 *
 * @param id idSession
 * @returns session
 */
export async function fetchSession(id: number): Promise<Session> {
  try {
    const response = await fetch(`${SESSION_API_URL}/${id}`);
    if (!response.ok) throw new Error("Erreur HTTP");
    return response.json();
  } catch (error) {
    console.error("Erreur FetchCourse: ", error);
    throw error;
  }
}

/**
 * UpdateSession
 *
 * @param id idSession
 * @param session session + students[]
 * @returns session
 */
export async function updateSession(
  id: number,
  session: Omit<Session, "id">
): Promise<Session> {
  try {
    const response = await fetch(`${SESSION_API_URL}/${id}`, {
      method: "PATCH",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify(session),
    });
    if (!response.ok) throw new Error("Erreur HTTP");
    return response.json();
  } catch (error) {
    console.error("Erreur updateSession: ", error);
    throw error;
  }
}

/**
 * RemoveSession
 *
 * @param id idSession
 */
export async function removeSession(id: number): Promise<void> {
  try {
    const response = await fetch(`${SESSION_API_URL}/${id}`, {
      method: "DELETE",
    });
    if (!response.ok) throw new Error("Erreur HTTP");
  } catch (error) {
    console.error("Erreur removeSession: ", error);
    throw error;
  }
}

// getAllStudents for 1 sessions
/**
 * FetchStudentsFromStudents
 *
 * @returns students[]
 */
export async function fetchStudentsFromSession(id: number): Promise<Student[]> {
  try {
    const response = await fetch(`${SESSION_API_URL}/${id}/students`);
    if (!response.ok) throw new Error("Erreur HTTP");
    return response.json();
  } catch (error) {
    console.error("Erreur fetchStudents: ", error);
    throw error;
  }
}

/**
 * AddStudents
 *
 * @param id idSession
 * @param studentIds list[UUID]
 * @returns session with his students
 */
export async function addStudents(
  id: number,
  studentIds: string[]
): Promise<Session> {
  try {
    const response = await fetch(`${SESSION_API_URL}/${id}/students`, {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify(studentIds),
    });
    if (!response.ok) throw new Error("Erreur HTTP");
    return response.json();
  } catch (error) {
    console.error("Erreur addStudents: ", error);
    throw error;
  }
}

/**
 * RemoveStudentFromSession
 *
 * @param id idSession
 * @param studentIds list string[]
 * @returns update session with his students
 */
export async function removeStudentFromSession(
  id: number,
  studentIds: string[]
): Promise<Session> {
  try {
    const response = await fetch(`${SESSION_API_URL}/${id}/students`, {
      method: "PATCH",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify(studentIds),
    });
    if (!response.ok) throw new Error("Erreur HTTP");
    return response.json();
  } catch (error) {
    console.error("Erreur removeStudentFromSession: ", error);
    throw error;
  }
}

// GetOneStudent
/** */
export async function getOneStudent(id: string): Promise<Student> {
  try {
    const response = await fetch(`${STUDENT_API_URL}/${id}`);
    if (!response.ok) throw new Error("Erreur HTTP");
    return response.json();
  } catch (error) {
    console.error("Erreur getOneSudent: ", error);
    throw error;
  }
}

/**
 * CreateStudent
 *
 * @param student
 * @returns new Student
 */
export async function createStudent(
  student: Omit<Student, "id">
): Promise<Student> {
  try {
    const response = await fetch(STUDENT_API_URL, {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify(student),
    });
    if (!response.ok) throw new Error("Erreur HTTP");
    return response.json();
  } catch (error) {
    console.error("Erreur CreateStudent: ", error);
    throw error;
  }
}

/**
 * UpdateStudent
 *
 * @param id idStudent
 * @param student changeStudent
 * @returns new student
 */
export async function updateStudent(
  id: string,
  student: Student
): Promise<Student> {
  try {
    const response = await fetch(`${STUDENT_API_URL}/${id}`, {
      method: "PATCH",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify(student),
    });
    if (!response.ok) throw new Error("Erreur HTTP");
    return response.json();
  } catch (error) {
    console.error("Erreur updateStudent: ", error);
    throw error;
  }
}

/**
 * DeleteStudent
 *
 * @param id id student
 */
export async function deleteStudent(id: string): Promise<void> {
  try {
    const response = await fetch(`${STUDENT_API_URL}/${id}`, {
      method: "DELETE",
    });
    if (!response.ok) throw new Error("Erreur HTTP");
  } catch (error) {
    console.error("Erreur deleteStudent: ", error);
    throw error;
  }
}
