class DesireService {

    async getAllDesires(userId) {
        try {
            const response = await fetch(`http://localhost:8080/api/v1/desires/user/${userId}`);
            if (response.ok) {
                const data = await response.json();
                return {success: true, data};
            } else {
                return {success: false, error: 'Could not fetch desires'};
            }
        } catch (error) {
            return {success: false, error: 'An error occurred while fetching desires'};
        }
    }

}

export default new DesireService();