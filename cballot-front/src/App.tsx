import { BrowserRouter, Routes, Route } from 'react-router'
// import reactLogo from './assets/react.svg'
// import viteLogo from '/vite.svg'
import './App.css'
import LoginPage from "./pages/LoginPage/LoginPage";
import MainElection from './pages/ElectionPage/MainElection';
import CreateElectionPage from "./pages/ElectionPage/CreateElectionPage";
import ManageElectionPage from "./pages/ElectionPage/ManageElectionPage";

function App() {
  // const [count, setCount] = useState(0)

  return (
    <>
      <BrowserRouter>

        <header>
          {/* <nav>
            <Link to="/counter">Compteur</Link>
            <Link to="/tasks">Liste de tâches</Link>
            <Link to="/dommodification">Modification dynamique du DOM</Link>
            <Link to="/randomword">Mots aléatoires</Link>
            <Link to="/clock">Horloge</Link>
            <Link to="/users">Liste d'utilisateurs</Link>
          </nav> */}
        </header>

        <main>
          <Routes>
            <Route path="/login" element={<LoginPage />} />

            {/* Routes Election */}
            <Route path="/election" element={<MainElection />}>
              {/* Sous-routes */}
              <Route path="create" element={<CreateElectionPage />} />
              <Route path="manage" element={<ManageElectionPage />} />
            </Route>

          </Routes>
        </main >
      </BrowserRouter>
    </>
  )
}

export default App
