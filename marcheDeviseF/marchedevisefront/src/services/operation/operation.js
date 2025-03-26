import apiClient from '../apiClient';

// Fetch all devises
export const getAllDevises = async () => {
  try {
    const response = await apiClient.get('/Operations/retrieve-all-operations');
    return response.data;
  } catch (error) {
    console.error('Error fetching devises:', error);
    throw error;
  }
};

// Fetch a specific devise by ID
export const getDeviseById = async (id) => {
  try {
    const response = await apiClient.get(`/Operations/retrieve-operation/${id}`);
    return response.data;
  } catch (error) {
    console.error(`Error fetching devise ${id}:`, error);
    throw error;
  }
};

// Add a new devise
export const addDevise = async (devise) => {
  try {
    const response = await apiClient.post('/Operations/addOperation', devise);
    return response.data;
  } catch (error) {
    console.error('Error adding devise:', error);
    throw error;
  }
};

// Update a devise by ID
export const updateDevise = async (id, devise) => {
  try {
    const response = await apiClient.put(`/Operations/modify-operation/${id}`, devise);
    return response.data;
  } catch (error) {
    console.error(`Error updating devise ${id}:`, error);
    throw error;
  }
};

// Delete a devise by ID
export const deleteDevise = async (id) => {
  try {
    await apiClient.delete(`/Operations/delete-operation/${id}`);
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
