import { useEffect, useState } from "react";
import type { Student } from "../../types/Student";
import { createElection } from "../../api/ElectionApi";
import { useNavigate } from "react-router";
import styles from "./ElectionPage.module.css";
import type { Session } from "../../types/Session";
import type { CreateElection } from "../../types/CreateElection";
import { fetchSession, fetchSessions } from "../../api/SessionApi";

const CreateElectionPage: React.FC = () => {
    const [students, setStudents] = useState<Student[] | undefined>([]);
    const [sessions, setSessions] = useState<Session[]>([]);
    const [election, setFormElection] = useState<CreateElection>({ start_date: new Date(), end_date: new Date(), id_session: 0 });
    const [_error, setError] = useState<string>("");

    const navigate = useNavigate();

    const loadSessions = async () => {
        try {
            const dataSessions = await fetchSessions();
            setSessions(dataSessions);
            setError("");
        } catch (error) {
            setError("Erreur lors du chargement des sessions.");
        }
    }

    const loadStudents = async (session_id: number) => {
        try {
            if (!session_id) { setStudents([]); };
            const dataStudents = await fetchSession(session_id);
            setStudents(dataStudents.students);
            setError("");
        } catch (error) {
            setError("Erreur lors du chargement des étudiants.");
        }
    }

    // au chargement de la page
    useEffect(() => {
        loadSessions();
        if (election.id_session) {
            loadStudents(election.id_session);
        }
    }, []);

    // au changement du select de la liste de sessions
    useEffect(() => {
        if (election.id_session) {
            loadStudents(election.id_session);
        }
    }, [election.id_session]);

    const handleCreateElectionSubmit = async (e: React.FormEvent) => {
        e.preventDefault();
        try {

            let response = await createElection(election);
            let id_election = response.id;
            setError("");
            navigate(`/election/manage?id_election=${id_election}&id_session=${election.id_session}`);

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
                <select name="id_session" id="sessions_list" value={election.id_session}
                    onChange={(e) => setFormElection({ ...election, id_session: Number(e.target.value) })}>
                    <option value="NOT_SELECTED" key="0">Choisir une session</option>
                    {sessions.map((session) => (
                        <option key={session.id} value={session.id}>{session.name}</option>
                    ))}
                </select>
                <label>Date de départ</label>
                <input type="date" name="start_date_election" id="start_date_election"
                    value={election.start_date.toISOString().split("T")[0]}
                    onChange={(e) => setFormElection({ ...election, start_date: new Date(e.target.value) })} />
                <label>Date de fin</label>
                <input type="date" name="end_date_election" id="end_date_election"
                    value={election.end_date.toISOString().split("T")[0]}
                    onChange={(e) => setFormElection({ ...election, end_date: new Date(e.target.value) })} />

                <h3>Liste des stagiaires</h3>
                <table>
                    <tbody>
                        {students?.map((student) => (
                            <tr key={student.id}>
                                <td>{student.firstname} {student.lastname}</td>
                            </tr>
                        ))}
                    </tbody>
                </table>
                <button type="submit">Envoyer</button>
            </form >
        </>
    );
}

export default CreateElectionPage;