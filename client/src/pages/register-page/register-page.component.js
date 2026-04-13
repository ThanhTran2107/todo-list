import { Form } from '@/antd-components/form.component';
import { message } from '@/antd-components/message.component';
import { Space } from '@/antd-components/space.component';
import { API_ENDPOINTS, PAGE_PATH } from '@/utilities/constants';
import { usePasswordValidation } from '@/utilities/hooks/use-password-validation.hook.js';
import { todoApi } from '@/utilities/services/api.service';
import { useEffect, useRef, useState } from 'react';
import { useNavigate } from 'react-router-dom';

import {
  AuthPasswordField,
  AuthTextField,
  ConfirmPasswordLabelWrapper,
  EmailLabelWrapper,
  FooterText,
  FormDescription,
  FormFooter,
  FormTitle,
  LoginLink,
  PasswordLabelWrapper,
  RegisterButton,
  RegisterForm,
  RegisterFormAction,
  RegisterIcon,
  TitleWrapper,
  Wrapper,
} from './styles/register-page.styled';

// Register page component with email, password, and confirm password fields
export const RegisterPage = () => {
  const [form] = Form.useForm();
  const [isLoading, setIsLoading] = useState(false);
  const emailRef = useRef(null);
  const { getPasswordRules, getConfirmRules } = usePasswordValidation();

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
      message.error(e.response.data?.error, 1);
    } finally {
      setIsLoading(false);
    }
  };

  useEffect(() => {
    if (emailRef.current) emailRef.current.focus();
  }, []);

  return (
    <Wrapper>
      <RegisterForm>
        <Form form={form} name="registerForm" layout="vertical" onFinish={handleRegister} autoComplete="off">
          <FormTitle>
            <Space direction="vertical">
              <TitleWrapper>
                <RegisterIcon src="/icons8-to-do-list-48.png" alt="Todo Icon" />
                Create your account
              </TitleWrapper>

              <FormDescription>Enter your credentials to create your account</FormDescription>
            </Space>
          </FormTitle>

          <Form.Item
            label={<EmailLabelWrapper size={270}>Email</EmailLabelWrapper>}
            name="email"
            rules={[
              { required: true, message: 'Please enter your email!' },
              { type: 'email', message: 'Please enter a valid email!' },
            ]}
          >
            <AuthTextField ref={emailRef} placeholder="Enter your email" />
          </Form.Item>

          <Form.Item
            label={<PasswordLabelWrapper size={270}>Password</PasswordLabelWrapper>}
            name="password"
            rules={getPasswordRules()}
          >
            <AuthPasswordField placeholder="Enter your password" />
          </Form.Item>

          <Form.Item
            label={<ConfirmPasswordLabelWrapper size={270}>Confirm Password</ConfirmPasswordLabelWrapper>}
            name="confirmPassword"
            dependencies={['password']}
            rules={getConfirmRules()}
          >
            <AuthPasswordField placeholder="Confirm your password" />
          </Form.Item>

          <Form.Item>
            <RegisterFormAction>
              <RegisterButton type="primary" htmlType="submit" loading={isLoading} block>
                Submit
              </RegisterButton>
            </RegisterFormAction>
          </Form.Item>
        </Form>
      </RegisterForm>

      <FormFooter>
        <FooterText>Already have an account ?</FooterText>
        <LoginLink onClick={() => navigate(PAGE_PATH.LOGIN)}>Login</LoginLink>
      </FormFooter>
    </Wrapper>
  );
};
