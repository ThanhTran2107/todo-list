import { Button } from '@/components/antd/button.component';
import { Form } from '@/components/antd/form.component';
import { Image } from '@/components/antd/image.component';
import { message } from '@/components/antd/message.component';
import { Space } from '@/components/antd/space.component';
import { TextField } from '@/components/antd/text-field.component';
import { API_ENDPOINTS, LOCALSTORAGE_KEYS, PAGE_PATH } from '@/utilities/constant';
import { todoApi } from '@/utilities/services/api.service';
import { setLocalStorage } from '@/utilities/services/storage.service';
import { useState } from 'react';
import { useNavigate } from 'react-router-dom';

import { FormTitle, LoginContainer, LoginForm, RegisterLink } from './styles/login-page.styled';

const { AUTH_TOKEN } = LOCALSTORAGE_KEYS;

// Login page component with email and password fields, login and register buttons
export const LoginPage = () => {
  const [loading, setLoading] = useState(false);
  const [form] = Form.useForm();

  const navigate = useNavigate();

  const handleLogin = async values => {
    setLoading(true);

    try {
      const response = await todoApi.post(API_ENDPOINTS.LOGIN, {
        email: values.email,
        password: values.password,
      });

      message.success('Login successfully!', 1);
      setLocalStorage(AUTH_TOKEN, response.data.token);
      navigate(PAGE_PATH.TODO_LIST, { replace: true });
    } catch (e) {
      console.error(e);
      message.error(e.response?.data?.error, 1);
    } finally {
      setLoading(false);
    }
  };

  const handleRegister = async () => {
    // TODO: Implement register logic or navigate to register page
    console.log('Navigate to register');
    // For now, you can add register API call here similar to login
  };

  return (
    <LoginContainer>
      <LoginForm>
        <Form form={form} name="loginForm" layout="vertical" onFinish={handleLogin} autoComplete="off">
          <FormTitle>
            <Image
              preview={false}
              src="/icons8-to-do-list-48.png"
              alt="Todo Icon"
              style={{ width: '48px', height: '48px', marginRight: '1rem' }}
            />
            Login
          </FormTitle>

          <Form.Item
            label="Email"
            name="email"
            rules={[
              { required: true, message: 'Please enter your email!' },
              { type: 'email', message: 'Please enter a valid email!' },
            ]}
          >
            <TextField placeholder="Enter your email" />
          </Form.Item>

          <Form.Item
            label="Password"
            name="password"
            rules={[{ required: true, message: 'Please enter your password!' }]}
          >
            <TextField.Password placeholder="Enter your password" />
          </Form.Item>

          <Form.Item>
            <Space direction="vertical" style={{ width: '100%' }}>
              <Button type="primary" htmlType="submit" loading={loading} block style={{ marginTop: '0.5rem' }}>
                Login
              </Button>

              <RegisterLink onClick={handleRegister}>Don't have an account?</RegisterLink>
            </Space>
          </Form.Item>
        </Form>
      </LoginForm>
    </LoginContainer>
  );
};
