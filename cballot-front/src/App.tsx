import { BrowserRouter, Routes, Route } from 'react-router'
// import reactLogo from './assets/react.svg'
// import viteLogo from '/vite.svg'
import './App.css'

function App() {
  // const [count, setCount] = useState(0)

  return (
    <>
      <BrowserRouter>

        <header>
          <h1>Titre</h1>

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
            {/* <Route path="/" element={<HomePage />} /> */}
          </Routes>
        </main >
      </BrowserRouter>
    </>
  )
}

export default App
