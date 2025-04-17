import apiClient from '../apiClient';
export const getAllPositionTresoDs = async () => {
    try {
      const response = await apiClient.get('/CashPosD/retrieve-all-cashpositionDs');
      return response.data;
    } catch (error) {
      console.error('Error fetching devises:', error);
      throw error;
    }
  };
  
  // Fetch a specific devise by ID
  export const getPositionTresoD = async (id) => {
    try {
      const response = await apiClient.get(`/CashPosD/retrieve-cashpositionD/${id}`);
      return response.data;
    } catch (error) {
      console.error(`Error fetching devise ${id}:`, error);
      throw error;
    }
  };