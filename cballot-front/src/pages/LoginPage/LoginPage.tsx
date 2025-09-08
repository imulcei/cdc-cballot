import { useState } from "react";
import styles from "./LoginPage.module.css";
import { useNavigate } from "react-router";
import { loginUser } from "../../api/LoginApi";

export type LoginCredentials = { email: string, password: string };

const LoginPage = () => {

    const [formData, setFormData] = useState<LoginCredentials>({ email: "", password: "" });
    const [_error, setError] = useState<string>("");

    const navigate = useNavigate(); // pour rediriger après une connexion

    // soumission du formulaire
    const handleLoginSubmit = async (e: React.FormEvent) => {
        e.preventDefault(); // empêche le rechargement de la page
        try {
            let jsonResponse = await loginUser(formData);
            console.log("Connexion réussie");

            localStorage.setItem("jwt", jsonResponse.jwt);
            localStorage.setItem("role", jsonResponse.role);
            let roleUser = localStorage.getItem("role");

            setError("");
            if (roleUser === "ROLE_ADMIN") {
                navigate("/courses"); // redirige vers formations
            }

            if (roleUser === "ROLE_TEACHER") {
                navigate("/sessions"); // redirige vers session
            }
        } catch (error) {
            setError("Une erreur s'est produite lors de la connexion.");
        }
    }

    return (
        <>
            <h2>Connexion</h2>
            <form onSubmit={handleLoginSubmit} method="POST" className={styles.login_form}>
                <label>adresse mail</label>
                <input type="email" name="email" id="login_email" placeholder="entrez l'adresse mail" value={formData.email} onChange={(e) => setFormData({ ...formData, email: e.target.value })} />
                <label>mot de passe</label>
                <input type="password" name="password" id="login_password" placeholder="entrez le mot de passe" value={formData.password} onChange={(e) => setFormData({ ...formData, password: e.target.value })} />
                <button type="submit">Entrer</button>
            </form>
        </>
    );
}

export default LoginPage;