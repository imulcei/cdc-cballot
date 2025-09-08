import type { CreateElection } from "../types/CreateElection";
import type { CreatePair } from "../types/CreatePair";
import type { Election } from "../types/Election";
import type { Pair } from "../types/Pair";
import type { Student } from "../types/Student";

const ELECTION_API_URL = "http://localhost:8000/api/election";

export async function fetchAllPairs(): Promise<Pair[]> {
    try {
        const token = localStorage.getItem("jwt");
        const usersData = await fetch(`${ELECTION_API_URL}/pairs`, {
            method: "GET",
            headers: {
                "Content-Type": "application/json",
                "Authorization": "Bearer " + token
            }
        });
        if (!usersData.ok) { throw new Error("Erreur http"); };
        return usersData.json();
    } catch (error) {
        console.log("Erreur fetchAllPairs: ", error);
        throw error;
    }
}

export async function createElection(election: CreateElection): Promise<Election> {
    try {
        const response = await fetch(`${ELECTION_API_URL}/create`, {
            method: "POST",
            headers: {
                "Content-Type": "application/json",
                "Authorization": "Bearer " + localStorage.getItem("jwt")
            },
            body: JSON.stringify(election)
        });
        if (!response.status) {
            throw new Error("Erreur http");
        };
        return response.json();
    } catch (error) {
        console.error("Erreur createElection: ", error);
        throw error;
    }
}

export async function createPair(pair: CreatePair): Promise<Pair> {
    try {
        const response = await fetch(`${ELECTION_API_URL}/create-pair`, {
            method: "POST",
            headers: {
                "Content-Type": "application/json",
                "Authorization": "Bearer " + localStorage.getItem("jwt")
            },
            body: JSON.stringify(pair)
        });
        if (!response.status) { throw new Error("Erreur http"); }
        return response.json();
    } catch (error) {
        console.error("Erreur createPair: ", error);
        throw error;
    }
}

export async function addStudentToPair(id_pair: number, id_student: string): Promise<Student> {
    try {
        const response = await fetch(`${ELECTION_API_URL}/pair/${id_pair}/add-student`, {
            method: "PATCH",
            headers: {
                "Content-Type": "application/json",
                "Authorization": "Bearer " + localStorage.getItem("jwt")
            },
            body: JSON.stringify(id_student)
        });
        if (!response.status) { throw new Error("Erreur http"); }
        return response.json();
    } catch (error) {
        console.error("Erreur createPair: ", error);
        throw error;
    }
}