import apiClient from '../apiClient';

// Fetch all devises
export const getAllDevises = async () => {
  try {
    const response = await apiClient.get('/Devises/retrieve-all-devises');
    return response.data;
  } catch (error) {
    console.error('Error fetching devises:', error);
    throw error;
  }
};

// Fetch a specific devise by ID
export const getDeviseById = async (id) => {
  try {
    const response = await apiClient.get(`/Devises/retrieve-devise/${id}`);
    return response.data;
  } catch (error) {
    console.error(`Error fetching devise ${id}:`, error);
    throw error;
  }
};

// Add a new devise
export const addDevise = async (devise) => {
  try {
    const response = await apiClient.post('/Devises/addDevise', devise);
    return response.data;
  } catch (error) {
    console.error('Error adding devise:', error);
    throw error;
  }
};

// Update a devise by ID
export const updateDevise = async (id, devise) => {
  try {
    const response = await apiClient.put(`/Devises/modify-devise/${id}`, devise);
    return response.data;
  } catch (error) {
    console.error(`Error updating devise ${id}:`, error);
    throw error;
  }
};

// Delete a devise by ID
export const deleteDevise = async (id) => {
  try {
    await apiClient.delete(`/Devises/delete-devise/${id}`);
  } catch (error) {
    console.error(`Error deleting devise ${id}:`, error);
    throw error;
  }
};

// // Simulate currency rates
// export const simulateRates = async () => {
//   try {
//     await apiClient.post('/Devises/simulate');
//   } catch (error) {
//     console.error('Error simulating rates:', error);
//     throw error;
//   }
// };
