import Cookies from 'js-cookie';

import { API_ENDPOINTS, PAGE_PATH, STORAGE_KEYS } from '../constant';
import { todoApi } from './api.service';

export const handleUnauthorized = async () => {
  try {
    await todoApi.get(API_ENDPOINTS.LOGOUT);
  } catch (e) {
    console.error(e);
  } finally {
    Cookies.remove(STORAGE_KEYS.AUTH_TOKEN);

    wiNndow.location.replace(PAGE_PATH.LOGIN);
  }
};
