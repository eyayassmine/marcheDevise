import apiClient from '../apiClient';

// Fetch all operations
export const getAllOperations = async () => {
  try {
    const response = await apiClient.get('/Operations/retrieve-all-operations');
    return response.data;
  } catch (error) {
    console.error('Error fetching operations:', error);
    throw error;
  }
};

export const calculateMaturity = async (inputData) => {
  try {
    const response = await apiClient.post('/Operations/calculate-maturity', inputData);
    return response.data;
  } catch (error) {
    console.error('Error fetching operations:', error);
    throw error;
  }
};


// Fetch a specific operation by ID
export const getOperationById = async (id) => {
  try {
    const response = await apiClient.get(`/Operations/retrieve-operation/${id}`);
    return response.data;
  } catch (error) {
    console.error(`Error fetching operation ${id}:`, error);
    throw error;
  }
};

// Add a new operation
export const addOperation = async (operation) => {
  try {
    const response = await apiClient.post('/Operations/addOperation', operation);
    return response.data;
  } catch (error) {
    console.error('Error adding operation:', error);
    throw error;
  }
};

// Update a operation by ID
export const updateOperation = async (id, operation) => {
  try {
    const response = await apiClient.put(`/Operations/modify-operation/${id}`, operation);
    return response.data;
  } catch (error) {
    console.error(`Error updating operation ${id}:`, error);
    throw error;
  }
};

// Delete a operation by ID
export const deleteOperation = async (id) => {
  try {
    await apiClient.delete(`/Operations/delete-operation/${id}`);
  } catch (error) {
    console.error(`Error deleting operation ${id}:`, error);
    throw error;
  }
};

