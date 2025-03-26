import apiClient from '../apiClient';
export const getAllPositionTresoDs = async () => {
    try {
      const response = await apiClient.get('/PTresoD/retrieve-all-ptresoDs');
      return response.data;
    } catch (error) {
      console.error('Error fetching devises:', error);
      throw error;
    }
  };
  
  // Fetch a specific devise by ID
  export const getPositionTresoD = async (id) => {
    try {
      const response = await apiClient.get(`/PTresoD/retrieve-ptresoD/${id}`);
      return response.data;
    } catch (error) {
      console.error(`Error fetching devise ${id}:`, error);
      throw error;
    }
  };