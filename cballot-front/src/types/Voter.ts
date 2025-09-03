import type { Election } from "./Election";

export interface Voter {
    id:string,
    vote_cast: boolean,
    email: string,
    election: Election
}