import type React from "react";
import { useEffect, useState } from "react";
import type { Teacher } from "../../types/Teacher";
import { Link, useNavigate } from "react-router";
import {
  createTeacher,
  deleteTeacher,
  fetchTeacher,
  fetchTeachers,
  updateTeacher,
} from "../../api/FormationApi";

const GetTeachers: React.FC = () => {
  const [teachers, setTeachers] = useState<Teacher[]>([]);
  const [error, setError] = useState<string>("");
  const navigate = useNavigate();
  const [formData, setFormData] = useState<Teacher>({
    id: "",
    firstname: "",
    lastname: "",
    email: "",
    password: "",
  });

  const loadPage = async () => {
    try {
      const data = await fetchTeachers();
      setTeachers(data);
      setError("");
    } catch (error) {
      setError("❗Erreur Lors du chargement des formateurs.");
    }
  };

  const handleReturn = async () => {
    navigate("/courses");
  };

  const handleSubmit = async (e: React.FormEvent) => {
    e.preventDefault();
    try {
      if (formData.id) {
        await updateTeacher(formData.id, formData);
      } else {
        await createTeacher(formData);
      }
      setError("");
      setFormData({
        id: "",
        firstname: "",
        lastname: "",
        email: "",
        password: "",
      });
    } catch (error) {
      setError("❗Une erreur est survenu lors de l'enregistrement.");
    }
    loadPage();
  };

  const handleChange = async (id: string) => {
    try {
      const data = await fetchTeacher(id);
      setFormData(data);
      loadPage();
    } catch (error) {
      setError("❗Erreur de retour d'un formateur.");
    }
  };

  const handleDelete = async (id: string) => {
    try {
      await deleteTeacher(id);
      loadPage();
    } catch (error) {
      setError("❗Erreur lors de la suppression du formateur.");
    }
  };

  useEffect(() => {
    loadPage();
  }, []);

  return (
    <div>
      <button
        onClick={() => {
          handleReturn();
        }}
      >
        ◀️
      </button>
      <h3>Formateurs</h3>
      <form onSubmit={handleSubmit}>
        <input
          type="hidden"
          value={formData.id}
          onChange={(e) => {
            setFormData({ ...formData, id: e.target.value });
          }}
        />
        <label>Prénom :</label>
        <input
          type="text"
          placeholder="Prénom"
          value={formData.firstname}
          onChange={(e) =>
            setFormData({ ...formData, firstname: e.target.value })
          }
          required
        />
        <br />
        <label>Nom :</label>
        <input
          type="text"
          placeholder="Nom"
          value={formData.lastname}
          onChange={(e) =>
            setFormData({ ...formData, lastname: e.target.value })
          }
          required
        />
        <br />
        <label>Email :</label>
        <input
          type="email"
          placeholder="email"
          value={formData.email}
          onChange={(e) => setFormData({ ...formData, email: e.target.value })}
          required
        />
        <br />
        <label>Mot de passe :</label>
        <input
          type="password"
          placeholder="Mot de passe"
          value={formData.password}
          onChange={(e) =>
            setFormData({ ...formData, password: e.target.value })
          }
          required
        />
        <br />
        <button>💾 Enregistrer</button>
      </form>

      <h4>Liste :</h4>
      <ul>
        {teachers.map((teacher) => (
          <li>
            {teacher.firstname} {teacher.lastname} | ✉️ {teacher.email}
            {" | "}
            <button
              onClick={() => {
                handleChange(teacher.id);
              }}
            >
              🖊️ Modifier
            </button>
            {" | "}
            <button onClick={() => handleDelete(teacher.id)}>
              🗑️ Supprimer
            </button>
          </li>
        ))}
      </ul>
    </div>
  );
};

export default GetTeachers;
