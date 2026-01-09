import { Button } from '@/components/antd/button.component';
import { Form } from '@/components/antd/form.component';
import { Image } from '@/components/antd/image.component';
import { message } from '@/components/antd/message.component';
import { Space } from '@/components/antd/space.component';
import { TextField } from '@/components/antd/text-field.component';
import { API_ENDPOINTS, PAGE_PATH } from '@/utilities/constant';
import { todoApi } from '@/utilities/services/api.service';
import { useState } from 'react';
import { useNavigate } from 'react-router-dom';

import { FormTitle, LoginLink, RegisterContainer, RegisterForm } from './styles/register-page.styled';

// Register page component with email, password, and confirm password fields
export const RegisterPage = () => {
  const [isLoading, setIsLoading] = useState(false);
  const [form] = Form.useForm();

  const navigate = useNavigate();

  const handleRegister = async values => {
    setIsLoading(true);

    try {
      const response = await todoApi.post(API_ENDPOINTS.REGISTER, {
        email: values.email,
        password: values.password,
      });

      message.success(response?.data?.message, 1);
      navigate(PAGE_PATH.LOGIN, { replace: true });
    } catch (e) {
      console.error(e);
      message.error(e.response.data?.error, 1);
    } finally {
      setIsLoading(false);
    }
  };

  return (
    <RegisterContainer>
      <RegisterForm>
        <Form form={form} name="registerForm" layout="vertical" onFinish={handleRegister} autoComplete="off">
          <FormTitle>
            <Image
              preview={false}
              src="/icons8-to-do-list-48.png"
              alt="Todo Icon"
              style={{ width: '3rem', height: '3rem', marginRight: '1rem' }}
            />
            Register
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
            rules={[
              { required: true, message: 'Please enter your password!' },
              { min: 6, message: 'Password must be at least 6 characters!' },
            ]}
          >
            <TextField.Password placeholder="Enter your password" />
          </Form.Item>

          <Form.Item
            label="Confirm Password"
            name="confirmPassword"
            dependencies={['password']}
            rules={[
              { required: true, message: 'Please confirm your password!' },
              ({ getFieldValue }) => ({
                validator(_, value) {
                  if (!value || getFieldValue('password') === value) return Promise.resolve();

                  return Promise.reject(new Error('Passwords do not match!'));
                },
              }),
            ]}
          >
            <TextField.Password placeholder="Confirm your password" />
          </Form.Item>

          <Form.Item>
            <Space direction="vertical" style={{ width: '100%' }}>
              <Button type="primary" htmlType="submit" loading={isLoading} block style={{ marginTop: '0.5rem' }}>
                Register
              </Button>

              <LoginLink onClick={() => navigate(PAGE_PATH.LOGIN)}>Already have an account?</LoginLink>
            </Space>
          </Form.Item>
        </Form>
      </RegisterForm>
    </RegisterContainer>
  );
};
