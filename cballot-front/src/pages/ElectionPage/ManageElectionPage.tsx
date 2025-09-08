import { useEffect, useState } from "react";
import type { Student } from "../../types/Student";
import type { Pair } from "../../types/Pair";
import { useSearchParams } from "react-router";
import { fetchSession } from "../../api/SessionApi";
import { addStudentToPair, createPair, fetchAllPairs } from "../../api/ElectionApi";
import type { CreatePair } from "../../types/CreatePair";

const ManageElectionPage: React.FC = () => {
    const [students, setStudents] = useState<Student[] | undefined>([]);
    const [pairs, setPairs] = useState<Pair[]>([]);
    // const [pairsStudents, setPairsStudents] = useState<Student[] | undefined>([]);
    const [selectedStudents, setSelectedStudents] = useState<string[]>([]); // étudiants sélectionnés
    const [studentsInPairs] = useState<string[]>([]); // étudiants dans un binôme
    const [_error, setError] = useState<string>("");

    let [searchParams] = useSearchParams();
    let id_election: number = Number(searchParams.get("id_election"));
    let id_session: number = Number(searchParams.get("id_session"));
    const [pair] = useState<CreatePair>({ counter: 0, id_election: id_election });

    const loadStudents = async () => {
        try {
            if (!id_election) { setStudents([]); };
            const dataStudents = await fetchSession(id_session);
            setStudents(dataStudents.students);
            setError("");
        } catch (error) {
            setError("Erreur lors du chargement des étudiants.");
        }
    };

    const loadPairs = async () => {
        try {
            const dataPairs = await fetchAllPairs();
            setPairs(dataPairs);
            // dataPairs.forEach(pair => {
            //     setPairsStudents(pair.students);
            // });
            setError("");
        } catch (error) {
            setError("Erreur lors du chargement des binômes.");
        }
    }

    useEffect(() => {
        loadStudents();
        loadPairs();
    }, []);

    const toggleSelectStudent = (id: string) => {
        if (selectedStudents.includes(id)) {
            setSelectedStudents(selectedStudents.filter(sid => sid !== id));
        } else {
            if (selectedStudents.length < 2) { setSelectedStudents([...selectedStudents, id]); }
        }
    }

    const handleCreatePairSubmit = async (e: React.FormEvent) => {
        e.preventDefault();
        if (selectedStudents.length !== 2) { setError("Il ne faut sélectionner que 2 étudiants."); return; }
        try {
            const newPair = await createPair(pair); // créé le binôme
            const id_pair = newPair.id;

            for (const id_student of selectedStudents) { await addStudentToPair(id_pair, id_student); } // ajoute les 2 étudiants au binôme

            await loadPairs(); // recharge les binômes
            setSelectedStudents([]); // vide les étudiants sélectionnés
            setError("");

            // navigate(`/election/manage?id=${idSession}`);
        } catch (error) {
            setError("Une erreur s'est produite lors de la création du binôme.");
        }
    }

    return (
        <>
            {/* TODO: css */}
            <h4>Gérer les binômes d'une élection</h4>
            <p>{id_election}</p>
            <form onSubmit={handleCreatePairSubmit} method="POST">
                <table>
                    <tbody>
                        {students?.map((student) => (
                            <tr key={student.id}>
                                <td>{student.firstname} {student.lastname}</td>
                                <td>
                                    <input type="checkbox" name="checkbox_pairs" id="checkbox_pairs"
                                        checked={selectedStudents.includes(student.id)}
                                        onChange={() => toggleSelectStudent(student.id)}
                                        disabled={studentsInPairs.includes(student.id)} />
                                </td>
                            </tr>
                        ))}
                    </tbody>
                </table>
                <button type="submit">Créer un binôme</button>
            </form>
            <h4>Liste des binômes</h4>
            <table>
                <tbody>
                    {pairs?.map((pair) => (
                        <tr key={pair.id}>
                            {pair.students?.map((student) => (
                                <>
                                    <td key={student.id}>{student.firstname} {student.lastname}</td>
                                </>
                            ))}
                        </tr>
                    ))}
                </tbody>
            </table>
        </>
    );
}

export default ManageElectionPage;
