class UserService {

    async register(formData) {
        try {
            const response = await fetch('http://localhost:8080/api/v1/users/register', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify(formData),
            });

            if (response.ok) {
                const data = await response.json();
                return {success: true, data};
            } else {
                return {success: false, error: 'Could not register user'};
            }

        } catch (error) {
            return {success: false, error: 'An error occurred'};
        }
    }

    async checkExistByEmail(email) {
        try {
            const response = await fetch(`http://localhost:8080/api/v1/users/exists/email?email=${email}`);
            if (response.ok) {
                const data = await response.json();
                return {data};
            }
        } catch (error) {
            return false;
        }
    }

}

export default new UserService();