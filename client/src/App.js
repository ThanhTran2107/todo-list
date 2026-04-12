import { PAGE_PATH, STORAGE_KEYS } from '@/utilities/constants';
import { getCookie } from '@/utilities/services/storage.service';
import { ConfigProvider } from 'antd';
import { useEffect, useState } from 'react';
import { BrowserRouter, Navigate, Route, Routes } from 'react-router-dom';

import { ForgotPasswordPage } from './pages/forgot-password-page/forgot-password-page.component';
import { ForgotPasswordSentPage } from './pages/forgot-password-sent-page/forgot-password-sent-page.component';
import { LoginPage } from './pages/login-page/login-page.component';
import { RegisterPage } from './pages/register-page/register-page.component';
import { ResetPasswordPage } from './pages/reset-password-page/reset-password-page.component';
import { TodoListPage } from './pages/todo-list-page/todo-list-page.component';
import { PrivateRoute } from './routes/private-route.jsx';

const { AUTH_TOKEN } = STORAGE_KEYS;

// Custom hook for authentication check
const useAuth = () => {
  const [isAuthenticated, setIsAuthenticated] = useState(null);

  useEffect(() => {
    const token = getCookie(AUTH_TOKEN);

    setIsAuthenticated(!!token);
  }, []);

  return isAuthenticated;
};

// Generic auth route component
const AuthRoute = ({ authenticatedPath, unauthenticatedComponent }) => {
  const isAuthenticated = useAuth();

  if (isAuthenticated === null) return null;

  return isAuthenticated ? <Navigate to={authenticatedPath} replace /> : unauthenticatedComponent;
};

function App() {
  return (
    <ConfigProvider theme={{ hash: false }}>
      <BrowserRouter>
        <Routes>
          <Route
            path="/"
            element={
              <AuthRoute
                authenticatedPath={PAGE_PATH.TODO_LIST}
                unauthenticatedComponent={<Navigate to={PAGE_PATH.LOGIN} replace />}
              />
            }
          />
          <Route
            path={PAGE_PATH.LOGIN}
            element={<AuthRoute authenticatedPath={PAGE_PATH.TODO_LIST} unauthenticatedComponent={<LoginPage />} />}
          />
          <Route
            path={PAGE_PATH.TODO_LIST}
            element={
              <PrivateRoute>
                <TodoListPage />
              </PrivateRoute>
            }
          />
          <Route
            path={PAGE_PATH.REGISTER}
            element={<AuthRoute authenticatedPath={PAGE_PATH.TODO_LIST} unauthenticatedComponent={<RegisterPage />} />}
          />
          <Route
            path={PAGE_PATH.FORGOT_PASSWORD}
            element={
              <AuthRoute authenticatedPath={PAGE_PATH.TODO_LIST} unauthenticatedComponent={<ForgotPasswordPage />} />
            }
          />
          <Route
            path={PAGE_PATH.FORGOT_PASSWORD_SENT}
            element={
              <AuthRoute authenticatedPath={PAGE_PATH.TODO_LIST} unauthenticatedComponent={<ForgotPasswordSentPage />} />
            }
          />
          <Route
            path={PAGE_PATH.RESET_PASSWORD}
            element={
              <AuthRoute authenticatedPath={PAGE_PATH.TODO_LIST} unauthenticatedComponent={<ResetPasswordPage />} />
            }
          />
        </Routes>
      </BrowserRouter>
    </ConfigProvider>
  );
}

export default App;
