import './App.css';
import {BrowserRouter, Route, Routes} from "react-router-dom";

import HomePage from "./Components/HomePage/HomePage";
import AuthPage from "./Components/Auth/AuthPage";
import Header from "./Components/Header/Header";

function App() {

    return (
        <BrowserRouter>
            <Header/>
            <Routes>
                <Route path="/" element={<HomePage/>}/>
                <Route path="/auth" element={<AuthPage/>}/>
            </Routes>
        </BrowserRouter>
    );

}

export default App;