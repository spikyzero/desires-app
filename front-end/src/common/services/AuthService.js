class AuthService {

    static async login(email, password) {
        try {
            const response = await fetch('http://localhost:8080/api/v1/authentication/authenticate', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify({email, password}),
            });
            if (response.ok) {
                const data = await response.json();
                localStorage.setItem('jwt', data.token);
                return data;
            } else {
                console.error('Login error');
            }
        } catch (error) {
            console.error('An error occurred:', error);
            throw error;
        }
    }

    static logout() {
        localStorage.removeItem('jwt');
    }

    static getToken() {
        return localStorage.getItem('jwt');
    }

    static isAuthenticated() {
        return !!localStorage.getItem('jwt');
    }

}

export default AuthService;