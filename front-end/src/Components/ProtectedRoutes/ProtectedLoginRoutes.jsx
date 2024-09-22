import {Outlet, Navigate} from 'react-router-dom';
import AuthService from "../../common/services/AuthService";

const ProtectedLoginRoutes = () => {

    const isAuthenticated = AuthService.isAuthenticated();
    return isAuthenticated ? <Outlet/> : <Navigate to="/auth"/>

}

export default ProtectedLoginRoutes;