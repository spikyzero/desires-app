import './App.css';
import {BrowserRouter, Route, Routes} from "react-router-dom";

import Header from "./Components/Header/Header";
import AuthPage from "./Components/Auth/AuthPage";
import HomePage from "./Components/HomePage/HomePage";
import DesiresPage from "./Components/DesiresPage/DesiresPage";
import DesiresCreatePage from "./Components/DesiresCreatePage/DesiresCreatePage";
import ProtectedLoginRoutes from "./Components/ProtectedRoutes/ProtectedLoginRoutes";
import ProtectedNotLoginRoutes from "./Components/ProtectedRoutes/ProtectedNotLoginRoutes";

function App() {

    return (
        <BrowserRouter>
            <Header/>
            <Routes>
                <Route path="/" element={<HomePage/>}/>
                <Route element={<ProtectedNotLoginRoutes/>}>
                    <Route path="/auth" element={<AuthPage/>}/>
                </Route>
                <Route element={<ProtectedLoginRoutes/>}>
                    <Route path="/desires" element={<DesiresPage/>}/>
                    <Route path="/desire/create" element={<DesiresCreatePage/>}/>
                </Route>
            </Routes>
        </BrowserRouter>
    );

}

export default App;