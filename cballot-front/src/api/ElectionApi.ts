import type { CreateElection } from "../types/CreateElection";
import type { Election } from "../types/Election";
import type { Pair } from "../types/Pair";

const ELECTION_API_URL = "http://localhost:8000/api/election";

export async function fetchAllPairs(): Promise<Pair[]> {
    try {
        const usersData = await fetch(`${ELECTION_API_URL}/pairs`);
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