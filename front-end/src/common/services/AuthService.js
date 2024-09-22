class AuthService {

    static async login(email, password) {
        const url = 'http://localhost:8080/api/v1/authentication/authenticate';
        const requestOptions = {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify({email, password}),
        };
        try {
            const response = await fetch(url, requestOptions);

            if (!response.ok) {
                const error = await response.json();
                throw new Error(error.message || 'Login failed');
            }
            const data = await response.json();
            localStorage.setItem('jwt', data.token);
            localStorage.setItem('userId', data.userDTO.id);
            return data;
        } catch (error) {
            console.error('Login error:', error);
            throw error;
        }
    }

    static logout() {
        localStorage.removeItem('jwt');
        localStorage.removeItem('userId');
    }

    static getToken() {
        return localStorage.getItem('jwt');
    }

    static isAuthenticated() {
        return !!localStorage.getItem('jwt');
    }

    static getUserId() {
        return localStorage.getItem('userId');
    }

}

export default AuthService;