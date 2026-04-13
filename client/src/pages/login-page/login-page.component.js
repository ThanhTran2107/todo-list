import { Checkbox } from '@/antd-components/checkbox.component';
import { Form } from '@/antd-components/form.component';
import { Space } from '@/antd-components/space.component';
import { AUTH_ID, PAGE_PATH, STORAGE_KEYS } from '@/utilities/constants';
import { useAuthFlow } from '@/utilities/hooks/use-auth-flow.hook.js';
import { useFacebookSDK } from '@/utilities/hooks/use-facebook-sdk.hook.js';
import { getLocalStorage, setLocalStorage } from '@/utilities/services/storage.service';
import { useGoogleLogin } from '@react-oauth/google';
import { useEffect, useMemo, useRef } from 'react';
import FacebookLogin from 'react-facebook-login/dist/facebook-login-render-props';
import { useLocation, useNavigate } from 'react-router-dom';

import {
  AuthPasswordField,
  AuthTextField,
  DividerText,
  EmailLabelWrapper,
  ForgotPasswordButton,
  FormButtonWrapper,
  FormDescription,
  FormFooter,
  FormFooterText,
  FormTitle,
  LoginButton,
  LoginForm,
  PasswordLabelWrapper,
  RegisterLink,
  SocialImageButton,
  SocialLoginWrapper,
  StyledDivider,
  TitleWrapper,
  TodoImage,
  Wrapper,
} from './styles/login-page.styled';

export const LoginPage = () => {
  const [form] = Form.useForm();
  const emailRef = useRef(null);
  const navigate = useNavigate();
  const location = useLocation();

  const { isLoading, loginWithEmailPassword, loginWithGoogle, loginWithFacebook } = useAuthFlow();
  const isFacebookAvailable = useFacebookSDK();

  const defaultEmail = useMemo(() => {
    return location.state?.email ?? getLocalStorage(STORAGE_KEYS.REMEMBER_EMAIL) ?? '';
  }, [location.state]);

  const googleLogin = useGoogleLogin({
    onSuccess: loginWithGoogle,
    onError: () => {},
    flow: 'implicit',
  });

  const handleLogin = values => {
    loginWithEmailPassword(values);

    values.remember
      ? setLocalStorage(STORAGE_KEYS.REMEMBER_EMAIL, values.email)
      : localStorage.removeItem(STORAGE_KEYS.REMEMBER_EMAIL);
  };

  useEffect(() => {
    if (emailRef.current) emailRef.current.focus();
  }, []);

  useEffect(() => {
    if (defaultEmail)
      setTimeout(() => {
        form.setFieldsValue({
          email: defaultEmail,
          password: '',
          remember: !!getLocalStorage(STORAGE_KEYS.REMEMBER_EMAIL),
        });
      }, 100);
  }, [defaultEmail, form]);

  return (
    <Wrapper>
      <LoginForm>
        <Form form={form} name="loginForm" layout="vertical" onFinish={handleLogin}>
          <FormTitle>
            <TitleWrapper>
              <TodoImage preview={false} src="/icons8-to-do-list-48.png" alt="Todo Icon" />
              Welcome back
            </TitleWrapper>

            <FormDescription>Enter your credentials to access your workspace</FormDescription>
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
            label={
              <PasswordLabelWrapper size={270}>
                Password
                <ForgotPasswordButton
                  onClick={e => {
                    e.preventDefault();
                    navigate(PAGE_PATH.FORGOT_PASSWORD);
                  }}
                >
                  Forgot?
                </ForgotPasswordButton>
              </PasswordLabelWrapper>
            }
            name="password"
            rules={[{ required: true, message: 'Please enter your password!' }]}
          >
            <AuthPasswordField placeholder="Enter your password" />
          </Form.Item>

          <Form.Item name="remember" valuePropName="checked" style={{ marginTop: -15 }}>
            <Checkbox style={{ color: 'var(--primary-text-color)' }}>Remember me</Checkbox>
          </Form.Item>

          <Form.Item>
            <FormButtonWrapper>
              <LoginButton type="primary" htmlType="submit" loading={isLoading} block>
                Login
              </LoginButton>
            </FormButtonWrapper>
          </Form.Item>

          <StyledDivider plain>
            <DividerText>OR CONTINUE WITH</DividerText>
          </StyledDivider>

          <SocialLoginWrapper>
            <Space direction="vertical" align="center" style={{ color: 'var(--primary-text-color)' }}>
              <SocialImageButton preview={false} onClick={googleLogin} src="/google.png" alt="Google icon" />
              Google
            </Space>

            {isFacebookAvailable && (
              <FacebookLogin
                appId={AUTH_ID.FACEBOOK_APP_ID}
                fields="name,email,picture"
                callback={loginWithFacebook}
                onFailure={() => {}}
                render={({ onClick }) => (
                  <Space direction="vertical" align="center" style={{ color: 'var(--primary-text-color)' }}>
                    <SocialImageButton
                      onClick={() => onClick()}
                      preview={false}
                      src="/facebook.png"
                      alt="Facebook icon"
                    />
                    Facebook
                  </Space>
                )}
              />
            )}
          </SocialLoginWrapper>
        </Form>
      </LoginForm>

      <FormFooter>
        <FormFooterText>Don't have an account ?</FormFooterText>

        <RegisterLink onClick={() => navigate(PAGE_PATH.REGISTER)}>Register</RegisterLink>
      </FormFooter>
    </Wrapper>
  );
};
