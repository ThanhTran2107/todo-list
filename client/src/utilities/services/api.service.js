import axios from 'axios';

import { LOCALSTORAGE_KEYS } from '../constant';
import { getLocalStorage } from './storage.service';

const { AUTH_TOKEN } = LOCALSTORAGE_KEYS;

// Create axios instance for Todo API
export const todoApi = axios.create({
  baseURL: '/api',
  headers: { 'Content-Type': 'application/json' },
});

// Add token to Todo API requests
todoApi.interceptors.request.use(config => {
  const token = getLocalStorage(AUTH_TOKEN);

  if (token) config.headers.Authorization = `Bearer ${token}`;

  return config;
});
