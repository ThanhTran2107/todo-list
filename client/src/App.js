import { PAGE_PATH } from '@/utilities/constant';
import { LOCALSTORAGE_KEYS } from '@/utilities/constant';
import { getCookie } from '@/utilities/services/storage.service';
import { ConfigProvider } from 'antd';
import { useEffect, useState } from 'react';
import { BrowserRouter, Navigate, Route, Routes } from 'react-router-dom';

import { LoginPage } from './page/login-page/login-page.component';
import { TodoListPage } from './page/todo-list-page.component/todo-list-page.component';
import { PrivateRoute } from './routes/private-route';

const { AUTH_TOKEN } = LOCALSTORAGE_KEYS;

// Custom hook for authentication check
const useAuth = () => {
  const [isAuthenticated, setIsAuthenticated] = useState(null);

  useEffect(() => {
    const token = getCookie(AUTH_TOKEN);

    setIsAuthenticated(!!token);
  }, []);

  return isAuthenticated;
};

// Combined auth component for root route
const AuthCheck = () => {
  const isAuthenticated = useAuth();

  if (isAuthenticated === null) return null;

  return isAuthenticated ? <Navigate to={PAGE_PATH.TODO_LIST} replace /> : <Navigate to={PAGE_PATH.LOGIN} replace />;
};

// Combined auth component for login route
const LoginRoute = () => {
  const isAuthenticated = useAuth();

  if (isAuthenticated === null) return <div>Loading...</div>;

  return isAuthenticated ? <Navigate to={PAGE_PATH.TODO_LIST} replace /> : <LoginPage />;
};

function App() {
  return (
    <ConfigProvider theme={{ hash: false }}>
      <BrowserRouter>
        <Routes>
          <Route path="/" element={<AuthCheck />} />
          <Route path={PAGE_PATH.LOGIN} element={<LoginRoute />} />
          <Route
            path={PAGE_PATH.TODO_LIST}
            element={
              <PrivateRoute>
                <TodoListPage />
              </PrivateRoute>
            }
          />
        </Routes>
      </BrowserRouter>
    </ConfigProvider>
  );
}

export default App;
