import type React from "react";
import { useEffect, useState } from "react";
import { fetchCourses } from "../../api/FormationApi";
import type { CoursesAndTeachers } from "../../types/CoursesAndTeachers";

const CourseList: React.FC = () => {
    const [coursesAndTeachers, setCoursesAndTeachers]  = useState<CoursesAndTeachers | null>(null);
    const [error, setError] = useState<string>("");

    const loadCourses = async () => {
        try {
            const data = await fetchCourses();
            setCoursesAndTeachers(data);
            setError("");
        } catch (error) {
            setError("❗Erreur lors du chargement des formations.")
        }
    };


    useEffect(() => {
        loadCourses();
    }, []);

    return (
      <div>
        <h2>Gestion des formations</h2>
        <br />
        {coursesAndTeachers && (
          <>
            <h3>Formations</h3>
            <ul>
              {coursesAndTeachers.courses.map((course) => (
                <li key={course.id}>
                  <strong>{course.libelle}</strong>
                </li>
              ))}
            </ul>
            <br />
            <h3>Formateurs</h3>
            <ul>
              {coursesAndTeachers.teachers.map((teacher) => (
                <li key={teacher.id}>
                  {teacher.lastname} {teacher.firstname}
                </li>
              ))}
            </ul>
          </>
        )}
      </div>
    );
};

export default CourseList;