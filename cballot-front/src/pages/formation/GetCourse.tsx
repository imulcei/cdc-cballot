import type React from "react";
import { useEffect, useState } from "react";
import type { Course } from "../../types/Course";
import type { Teacher } from "../../types/Teacher";
import { useNavigate, useSearchParams } from "react-router";
import { fetchCourse, fetchTeachers } from "../../api/FormationApi";

const GetCourse: React.FC = () => {
    const [searchParams] = useSearchParams();
    const [course, setCourse] = useState<Course>();
    const [teachers, setTeachers] = useState<Teacher[] | undefined>([]);
    const [allTeacher, setAllTeacher] = useState<Teacher[]>([]);
    const [teacherIds, setTeacherIds] = useState<String[]>([]);
    const [ error, setError] = useState<String>("");
    let id_course = Number(searchParams.get("id"));
    const navigate = useNavigate();

    const loadCourse = async () => {
        try {
            if (!id_course){
                setCourse(undefined);
                setTeachers([]);
                };
            const data = await fetchCourse(id_course);
            setCourse(data);
            setTeachers(data.teachers);

            const otherData = await fetchTeachers();
            setAllTeacher(otherData);
            setError("");
        } catch (error) {
            setError(" ❗ Erruer lords du chargement de la formation et ses formateurs")
        }
    }

    const handleReturn = async () => {
        navigate("/courses");
    }

    const toggleTeacher = (id: string) => {
        setTeacherIds((prev) => prev.includes(id) ? prev.filter((teacherId) => teacherId !== id) : [...prev, id])
    }

    useEffect(() => {
        loadCourse();
    }, []);

    return (
        <div>
            <>
            <button onClick={()=> {handleReturn()}}>◀️</button>
                <h3>{course?.libelle}</h3>

                <p>
                    Liste des formateurs liée à la formation :
                </p>
                <ul>
                    {allTeacher?.map((teacher) => (
                        <li key={teacher.id}>
                            <label>
                                <input
                                type="checkbox"
                                value={teacher.id}
                                checked={teacherIds.includes(teacher.id)}
                                onChange={() => }
                                />
                            {teacher.lastname} {teacher.firstname}
                            </label>
                        </li>
                    ))}
                </ul>
            </>
        </div>
    );
}

export default GetCourse;