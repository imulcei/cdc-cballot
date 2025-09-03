import { Outlet } from "react-router";

function MainElection() {

    return (
        <>

            <header>
                <h2>Election</h2>

                {/* <nav>
                    <Link></Link>
                </nav> */}
            </header>

            <main>
                <Outlet />
            </main>

        </>
    );
}

export default MainElection;