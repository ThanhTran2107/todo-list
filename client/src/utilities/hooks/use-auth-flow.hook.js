import { message } from '@/antd-components/message.component';
import { API_ENDPOINTS, PAGE_PATH, STORAGE_KEYS } from '@/utilities/constants';
import { todoApi } from '@/utilities/services/api.service';
import { setCookie } from '@/utilities/services/storage.service';
import { useState } from 'react';
import { useNavigate } from 'react-router-dom';

const { AUTH_TOKEN } = STORAGE_KEYS;

export const useAuthFlow = () => {
  const [isLoading, setIsLoading] = useState(false);
  const navigate = useNavigate();

  const performLogin = async (endpoint, credentials) => {
    setIsLoading(true);

    try {
      const response = await todoApi.post(endpoint, credentials);

      message.success('Login successfully!', 1);
      setCookie(AUTH_TOKEN, response.data.token);
      navigate(PAGE_PATH.TODO_LIST, { replace: true });
    } catch (error) {
      message.error(error.response?.data?.error ?? 'Login failed. Please try again.', 1);
    } finally {
      setIsLoading(false);
    }
  };

  const loginWithEmailPassword = values =>
    performLogin(API_ENDPOINTS.LOGIN, {
      email: values.email,
      password: values.password,
    });

  const loginWithGoogle = async googleResponse => {
    const accessToken = googleResponse?.access_token;

    if (!accessToken) return message.error('Google authentication failed: missing token', 1);

    performLogin(API_ENDPOINTS.GOOGLE_LOGIN, { accessToken });
  };

  const loginWithFacebook = async facebookResponse => {
    const { accessToken, userID: userId } = facebookResponse;

    if (!accessToken || !userId) return message.error('Facebook authentication failed.', 1);

    performLogin(API_ENDPOINTS.FACEBOOK_LOGIN, {
      accessToken,
      userId,
    });
  };

  return {
    isLoading,
    loginWithEmailPassword,
    loginWithGoogle,
    loginWithFacebook,
  };
};
