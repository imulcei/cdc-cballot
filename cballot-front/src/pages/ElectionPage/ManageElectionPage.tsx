import { useState } from "react";
import type { Student } from "../../types/Student";

const ManageElectionPage: React.FC = () => {
    const [students, setStudents] = useState<Student[]>([]);

    return (
        <h2>Gérer une élection</h2>
    );
}

export default ManageElectionPage;