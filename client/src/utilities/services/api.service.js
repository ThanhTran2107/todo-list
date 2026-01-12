import axios from 'axios';

import { STORAGE_KEYS } from '../constants';
import { getCookie } from './storage.service';

const { AUTH_TOKEN } = STORAGE_KEYS;

// Create axios instance for Todo API
export const todoApi = axios.create({
  baseURL: '/api',
  headers: { 'Content-Type': 'application/json' },
});

// Add token to Todo API requests
todoApi.interceptors.request.use(config => {
  const token = getCookie(AUTH_TOKEN);

  if (token) config.headers.Authorization = `Bearer ${token}`;

  return config;
});
