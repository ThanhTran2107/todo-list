import { Button } from '@/components/antd/button.component';
import { Form } from '@/components/antd/form.component';
import { Image } from '@/components/antd/image.component';
import { message } from '@/components/antd/message.component';
import { Space } from '@/components/antd/space.component';
import { TextField } from '@/components/antd/text-field.component';
import { API_ENDPOINTS, PAGE_PATH, STORAGE_KEYS } from '@/utilities/constant';
import { todoApi } from '@/utilities/services/api.service';
import { setCookie } from '@/utilities/services/storage.service';
import { useState } from 'react';
import { useNavigate } from 'react-router-dom';

import { FormTitle, LoginContainer, LoginForm, RegisterLink } from './styles/login-page.styled';

const { AUTH_TOKEN } = STORAGE_KEYS;

// Login page component with email and password fields, login and register buttons
export const LoginPage = () => {
  const [isLoading, setIsLoading] = useState(false);
  const [form] = Form.useForm();

  const navigate = useNavigate();

  const handleLogin = async values => {
    setIsLoading(true);

    try {
      const response = await todoApi.post(API_ENDPOINTS.LOGIN, {
        email: values.email,
        password: values.password,
      });

      message.success('Login successfully!', 1);
      
      setCookie(AUTH_TOKEN, response.data.token);
      navigate(PAGE_PATH.TODO_LIST, { replace: true });
    } catch (e) {
      console.error(e);
      message.error(e.response.data?.error, 1);
    } finally {
      setIsLoading(false);
    }
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
              style={{ width: '3rem', height: '3rem', marginRight: '1rem' }}
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
              <Button type="primary" htmlType="submit" loading={isLoading} block style={{ marginTop: '0.5rem' }}>
                Login
              </Button>

              <RegisterLink onClick={() => navigate(PAGE_PATH.REGISTER)}>Don't have an account?</RegisterLink>
            </Space>
          </Form.Item>
        </Form>
      </LoginForm>
    </LoginContainer>
  );
};
