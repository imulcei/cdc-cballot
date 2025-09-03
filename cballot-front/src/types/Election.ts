import type { Pair } from "./Pair";
import type { Session } from "./Session";
import type { Voter } from "./Voter";

export interface Election {
    id: number,
    start_date: Date,
    end_date: Date,
    pairs?: Pair[],
    voters?: Voter[],
    session: Session
}