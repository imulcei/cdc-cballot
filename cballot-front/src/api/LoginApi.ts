const LOGIN_API_URL = "http://localhost:8000/api/auth/login";
import type { LoginCredentials } from "../pages/LoginPage/LoginPage";

export async function loginUser(userLogs: LoginCredentials): Promise<any> {
    try {
        const response = await fetch(LOGIN_API_URL, {
            method: "POST",
            headers: {
                "Content-Type": "application/json",
                "Authorization": "Bearer " + localStorage.getItem("jwt")
            },
            body: JSON.stringify(userLogs)
        });
        if (!response.ok) {
            throw new Error("Erreur http");
        };
        return response.json();
    } catch (error) {
        console.error("Erreur loginUser: ", error);
        throw error;
    }
}