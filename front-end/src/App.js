import './App.css';
import {BrowserRouter, Route, Routes} from "react-router-dom";

import HomePage from "./Components/HomePage/HomePage";
import AuthPage from "./Components/Auth/AuthPage";
import Header from "./Components/Header/Header";
import ProtectedRoutes from "./Components/ProtectedRoutes/ProtectedRoutes";

function App() {

    return (
        <BrowserRouter>
            <Header/>
            <Routes>
                <Route path="/" element={<HomePage/>}/>
                <Route element={<ProtectedRoutes/>}>
                    <Route path="/auth" element={<AuthPage/>}/>
                </Route>
            </Routes>
        </BrowserRouter>
    );

}

export default App;