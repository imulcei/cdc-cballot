import { useEffect, useState } from "react";
import type { Student } from "../../types/Student";
import { createElection } from "../../api/ElectionApi";
import { useNavigate } from "react-router";
import styles from "./ElectionPage.module.css";
import type { Session } from "../../types/Session";
import type { CreateElection } from "../../types/CreateElection";

const CreateElectionPage: React.FC = () => {
    const [students, setStudents] = useState<Student[]>([]);
    const [sessions, setSessions] = useState<Session[]>([]);
    const [formElection, setFormElection] = useState<CreateElection>({ start_date: new Date(), end_date: new Date(), id_session: 1 });
    const [_error, setError] = useState<string>("");

    const navigate = useNavigate();

    useEffect(() => {
        //TODO: get students by id_session
    }, []);

    const handleCreateElectionSubmit = async (e: React.FormEvent) => {
        e.preventDefault();
        try {
            await createElection(formElection);
            console.log("élection crée.");

            localStorage.getItem("jwt");
            localStorage.getItem("role");

            setError("");
            navigate("/election/manage");
        } catch (error) {
            setError("Une erreur s'est produite lors de la connexion.");
        }
    }

    return (
        <>
            {/* TODO: css */}
            <h4>Créer une élection</h4>
            <form onSubmit={handleCreateElectionSubmit} method="POST" className={styles.create_election_form}>
                <label>Choisir une session</label>
                <select name="sessions" id="sessions_list">
                    {sessions.map((session) => (
                        <option key={session.id} value={session.id}>{session.name}</option>
                    ))}
                </select>
                <label>Date de départ</label>
                <input type="date" name="start_date_election" id="start_date_election"
                    value={formElection.start_date.toISOString().split("T")[0]}
                    onChange={(e) => setFormElection({ ...formElection, start_date: new Date(e.target.value) })} />
                <label>Date de fin</label>
                <input type="date" name="end_date_election" id="end_date_election"
                    value={formElection.end_date.toISOString().split("T")[0]}
                    onChange={(e) => setFormElection({ ...formElection, end_date: new Date(e.target.value) })} />

                <h3>Liste des stagiaires</h3>
                <table>
                    <tbody>
                        {students.map((student) => (
                            <td>
                                {student.firstname} {student.lastname}
                            </td>
                        ))}
                    </tbody>
                </table>
                <button type="submit">Envoyer</button>
            </form >
        </>
    );
}

export default CreateElectionPage;