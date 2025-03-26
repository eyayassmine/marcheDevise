import axios from 'axios';

const apiClient = axios.create({
  baseURL: 'http://localhost:8084/marcheDeviseEyaYassmine',
  headers: {
    'Content-Type': 'application/json',
  },
});

export default apiClient;
