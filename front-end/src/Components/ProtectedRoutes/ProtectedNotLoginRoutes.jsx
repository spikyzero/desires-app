import {Outlet, Navigate} from 'react-router-dom';
import AuthService from "../../common/services/AuthService";

const ProtectedNotLoginRoutes = () => {

    const isAuthenticated = AuthService.isAuthenticated();
    return isAuthenticated ? <Navigate to="/"/> : <Outlet/>

}

export default ProtectedNotLoginRoutes;