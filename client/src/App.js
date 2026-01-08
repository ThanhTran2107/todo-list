import { PAGE_PATH } from '@/utilities/constant';
import { ConfigProvider } from 'antd';
import { BrowserRouter, Navigate, Route, Routes } from 'react-router-dom';

import { LoginPage } from './page/login-page/login-page.component';
import { TodoListPage } from './page/todo-list-page.component/todo-list-page.component';
import { PrivateRoute } from './routes/private-route';

function App() {
  return (
    <ConfigProvider theme={{ hash: false }}>
      <BrowserRouter>
        <Routes>
          <Route path="/" element={<Navigate to={PAGE_PATH.LOGIN} replace />} />
          <Route path={PAGE_PATH.LOGIN} element={<LoginPage />} />
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
