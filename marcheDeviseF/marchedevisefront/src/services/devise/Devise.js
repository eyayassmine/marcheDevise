import apiClient from '../apiClient';

// Fetch all devises
export const getAllDevises = async () => {
  try {
    const response = await apiClient.get('/Currencies/retrieve-all-currencies');
    return response.data;
  } catch (error) {
    console.error('Error fetching currencies:', error);
    throw error;
  }
};

// Fetch a specific devise by ID
export const getDeviseById = async (id) => {
  try {
    const response = await apiClient.get(`/Currencies/retrieve-currency/${id}`);
    return response.data;
  } catch (error) {
    console.error(`Error fetching currency ${id}:`, error);
    throw error;
  }
};

// Add a new devise
export const addDevise = async (devise) => {
  try {
    const response = await apiClient.post('/Currencies/addCurrency', devise);
    return response.data;
  } catch (error) {
    console.error('Error adding currency:', error);
    throw error;
  }
};

// Update a devise by ID
export const updateDevise = async (id, devise) => {
  try {
    const response = await apiClient.put(`/Currencies/modify-currency/${id}`, devise);
    return response.data;
  } catch (error) {
    console.error(`Error updating currency ${id}:`, error);
    throw error;
  }
};

// Delete a devise by ID
export const deleteDevise = async (id) => {
  try {
    await apiClient.delete(`/Currencies/delete-currency/${id}`);
  } catch (error) {
    console.error(`Error deleting currency ${id}:`, error);
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
