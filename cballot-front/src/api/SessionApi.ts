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
 * CreateSession
 *
 * @param session session + students[]
 * @returns Session
 */
export async function createSession(session: Session): Promise<Session> {
    try {
        const response = await fetch(SESSION_API_URL, {
            method: "POST",
            headers: {"Content-Type": "application/json"},
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
export async function updateSession(id: number, session: Omit<Session, "id">): Promise<Session> {
    try {
        const response = await fetch(`${SESSION_API_URL}/${id}`, {
                        method:"PATCH",
            headers: {"Content-Type": "application/json"},
            body: JSON.stringify(session)
        });
        if (!response.ok) throw new Error("Erreur HTTP");
        return response.json();
    } catch (error) {
        console.error("Erreur updateSession: ", error);
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
// AddStudents
// RemoveStudentFromSession
// RemoveSession
// GetOneStudent
// CreateStudent
// UpdateStudent
// DeleteStudent
