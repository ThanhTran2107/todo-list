import { LOCALSTORAGE_KEYS } from '@/utilities/constant';
import { getLocalStorage } from '@/utilities/services/storage.service';
import { useEffect, useState } from 'react';
import { Navigate } from 'react-router-dom';

const { AUTH_TOKEN } = LOCALSTORAGE_KEYS;

export const PrivateRoute = ({ children }) => {
  const [loading, setLoading] = useState(true);
  const [isAuthenticated, setIsAuthenticated] = useState(false);

  useEffect(() => {
    const token = getLocalStorage(AUTH_TOKEN);

    if (token) setIsAuthenticated(true);

    setLoading(false);
  }, []);

  if (loading) return null;

  return isAuthenticated ? children : <Navigate to="/login" replace />;
};
