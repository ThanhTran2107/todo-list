import { message } from '@/components/antd/message.component';
import Cookies from 'js-cookie';

import { API_ENDPOINTS, PAGE_PATH, STORAGE_KEYS } from '../constants';
import { todoApi } from './api.service';

export const handleUnauthorized = async () => {
  try {
    await todoApi.get(API_ENDPOINTS.LOGOUT);
  } catch (e) {
    message.error('Session expired. Please login again.', 1);
  } finally {
    Cookies.remove(STORAGE_KEYS.AUTH_TOKEN);
    window.location.replace(PAGE_PATH.LOGIN);
  }
};
