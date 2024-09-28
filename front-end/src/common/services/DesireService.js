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

    async createDesire(formData) {
        try {
            const data = new FormData();
            data.append('userId', formData.userId);
            data.append('name', formData.name);
            data.append('description', formData.description);
            data.append('siteURL', formData.siteURL);
            data.append('price', formData.price);
            data.append('currencyName', formData.currencyName);
            data.append('priorityName', formData.priorityName);
            if (formData.image) {
                data.append('image', formData.image);
            }
            const response = await fetch('http://localhost:8080/api/v1/desires/add', {
                method: 'POST',
                body: data,
            });
            if (response.ok) {
                const data = await response.json();
                return {success: true, data};
            } else {
                return {success: false, error: 'Failed to create desire'};
            }
        } catch (error) {
            return {success: false, error: 'An error occurred while creating the desire'};
        }
    }

}

export default new DesireService();