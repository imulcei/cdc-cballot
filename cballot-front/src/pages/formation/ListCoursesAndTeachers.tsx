import type React from "react";
import { useEffect, useState } from "react";
import { createCourse, fetchCourses } from "../../api/FormationApi";
import type { Course } from "../../types/Course";
import type { Teacher } from "../../types/Teacher";
import { useNavigate } from "react-router";

const ListCoursesAndTeachers: React.FC = () => {
  const [courses, setCourses] = useState<Course[]>([]);
  const [teachers, setTeachers] = useState<Teacher[]>([]);
  const [error, setError] = useState<string>("");
  const [course, setCourse] = useState<Omit<Course, "id">>({ libelle: "" });
  const navigate = useNavigate();

  const loadCourses = async () => {
    try {
      const data = await fetchCourses();
      setCourses(data.courses);
      setTeachers(data.teachers);
      setError("");
    } catch (error) {
      setError("❗Erreur lors du chargement des formations et des formateurs.");
    }
  };

  useEffect(() => {
    loadCourses();
  }, []);

  const handleCourse = async (id: number | string) => {
    navigate(`/courses/manage?id=${id}`);
  };

  const handleTeacher = async () => {
    navigate("/courses/teachers");
  };

  const handleSubmit = async (e: React.FormEvent) => {
    e.preventDefault();
    try {
      await createCourse(course);
      loadCourses();
    } catch (error) {
      setError("❗Erreur lors de la création de la formation.");
    }
  };

  return (
    <div>
      <>
        <form onSubmit={handleSubmit}>
          <label htmlFor="libelle">Formation :</label>
          <input
            type="text"
            name="libelle"
            id="libelle"
            value={course.libelle}
            onChange={(e) => setCourse({ ...course, libelle: e.target.value })}
          />
          <button typeof="submit">Créer</button>
        </form>

        <h3>Formations</h3>
        <ul>
          {courses.map((course) => (
            <li key={course.id}>
              <button onClick={() => handleCourse(course.id)}>
                <strong>{course.libelle}</strong>
              </button>
            </li>
          ))}
        </ul>
        <br />
        <h3>Formateurs</h3>
        <button onClick={() => handleTeacher()}>🧑‍🏫 Liste</button>
        <ul>
          {teachers.map((teacher) => (
            <li key={teacher.id}>
              {teacher.lastname} {teacher.firstname}
            </li>
          ))}
        </ul>
      </>
    </div>
  );
};

export default ListCoursesAndTeachers;
