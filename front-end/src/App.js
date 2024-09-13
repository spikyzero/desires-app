import './App.css';
import HomePage from "./Components/HomePage/HomePage";
import {BrowserRouter, Route, Routes} from "react-router-dom";
import Test from "./Components/TestComponent/Test";

function App() {
    return (
        <BrowserRouter>
            <Routes>
                <Route path="/" element={<HomePage/>}/>
                <Route path="/test" element={<Test/>}/>
            </Routes>
        </BrowserRouter>
    );
}

export default App;