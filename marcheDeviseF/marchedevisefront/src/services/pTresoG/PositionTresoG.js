import apiClient from '../apiClient';
export const getAllPositionTresoGs = async () => {
    try {
      const response = await apiClient.get('/CashPosG/retrieve-all-cashpositionGs');
      return response.data;
    } catch (error) {
      console.error('Error fetching devises:', error);
      throw error;
    }
  };
  
  // Fetch a specific devise by ID
  export const getPositionTresoG = async (id) => {
    try {
      const response = await apiClient.get(`/CashPosG/retrieve-cashpositionG/${id}`);
      return response.data;
    } catch (error) {
      console.error(`Error fetching devise ${id}:`, error);
      throw error;
    }
  };