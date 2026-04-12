import { Form } from '@/antd-components/form.component';
import { message } from '@/antd-components/message.component';
import { API_ENDPOINTS, PAGE_PATH, RESET_PASSWORD_CONFIG } from '@/utilities/constants';
import { todoApi } from '@/utilities/services/api.service';
import { useEffect, useMemo, useState } from 'react';
import { useNavigate, useSearchParams } from 'react-router-dom';

import {
  AuthTextField,
  EmailLabelWrapper,
  FormBodyLabelText,
  FormButtonWrapper,
  FormFooter,
  FormFooterText,
  FormTitle,
  PrimaryButton,
  ResetPasswordForm,
  SecondaryButton,
  TitleWrapper,
  TodoImage,
  Wrapper,
} from '../reset-password-page/styles/reset-password-page.styled';

const validateTokenRequest = async token => {
  const { data } = await todoApi.get(`${API_ENDPOINTS.HELP_WITH_RESET_PASSWORD}?token=${encodeURIComponent(token)}`);
  return data;
};

export const ResetPasswordPage = () => {
  const [phase, setPhase] = useState('loading');
  const [userEmail, setUserEmail] = useState('');
  const [submitting, setSubmitting] = useState(false);

  const [searchParams] = useSearchParams();
  const navigate = useNavigate();
  const [form] = Form.useForm();

  const token = useMemo(() => searchParams.get('token')?.trim() ?? '', [searchParams]);

  const handleReset = async values => {
    setSubmitting(true);

    try {
      await todoApi.post(API_ENDPOINTS.RESET_PASSWORD, {
        token,
        newPassword: values.password,
      });

      message.success('Your password has been updated. You can sign in now.', 1);
      navigate(PAGE_PATH.LOGIN, { replace: true, state: { email: userEmail } });
    } catch (e) {
      message.error(e.response?.data?.error ?? 'Could not reset password. Please try again.', 1);
    } finally {
      setSubmitting(false);
    }
  };

  useEffect(() => {
    if (!token) return setPhase('no_token');

    let cancelled = false;

    (async () => {
      try {
        const data = await validateTokenRequest(token);

        if (cancelled) return;

        setUserEmail(typeof data?.email === 'string' ? data.email : '');
        setPhase('ready');
      } catch {
        if (!cancelled) setPhase('invalid');
      }
    })();

    return () => (cancelled = true);
  }, [token]);

  // ===== LOADING =====
  if (phase === 'loading') {
    return (
      <Wrapper>
        <ResetPasswordForm>
          <FormTitle>
            <TitleWrapper>
              <TodoImage preview={false} src="/icons8-to-do-list-48.png" />
              Checking reset link...
            </TitleWrapper>
          </FormTitle>
        </ResetPasswordForm>
      </Wrapper>
    );
  }

  // ===== INVALID / NO TOKEN =====
  if (phase === 'no_token' || phase === 'invalid') {
    const text =
      phase === 'no_token' ? 'Missing reset token. Please request again.' : 'This reset link is invalid or expired.';

    return (
      <Wrapper>
        <ResetPasswordForm>
          <FormTitle>
            <TitleWrapper>
              <TodoImage preview={false} src="/icons8-to-do-list-48.png" />
              Reset password
            </TitleWrapper>
          </FormTitle>

          <FormBodyLabelText>{text}</FormBodyLabelText>

          <FormButtonWrapper>
            <PrimaryButton type="primary" block onClick={() => navigate(PAGE_PATH.FORGOT_PASSWORD)}>
              Request new reset link
            </PrimaryButton>
          </FormButtonWrapper>

          <FormFooter>
            <SecondaryButton onClick={() => navigate(PAGE_PATH.LOGIN)}>Back to login</SecondaryButton>
          </FormFooter>
        </ResetPasswordForm>
      </Wrapper>
    );
  }

  // ===== READY =====
  return (
    <Wrapper>
      <ResetPasswordForm>
        <Form form={form} name="resetPasswordForm" layout="vertical" onFinish={handleReset} autoComplete="off">
          <FormTitle>
            <TitleWrapper>
              <TodoImage preview={false} src="/icons8-to-do-list-48.png" />
              Reset password
            </TitleWrapper>
          </FormTitle>

          {userEmail && <FormBodyLabelText>Account: {userEmail}</FormBodyLabelText>}

          <Form.Item
            label={<EmailLabelWrapper size={270}>New password</EmailLabelWrapper>}
            name="password"
            rules={[
              { required: true, message: 'Please enter your new password!' },
              { min: RESET_PASSWORD_CONFIG.PASSWORD_MIN_LENGTH, message: 'Password must be at least 10 characters!' },
              {
                pattern: /(?=.*[0-9])/,
                message: 'Password must include at least one number!',
              },
              {
                pattern: /(?=.*[a-z])/,
                message: 'Password must include at least one lowercase letter!',
              },
              {
                pattern: /(?=.*[A-Z])/,
                message: 'Password must include at least one uppercase letter!',
              },
              {
                pattern: /(?=.*[!@#$%^&*(),.?":{}|<>])/,
                message: 'Password must include at least one special character!',
              },
            ]}
          >
            <AuthTextField type="password" placeholder="Enter new password" />
          </Form.Item>

          <Form.Item
            label={<EmailLabelWrapper size={270}>Confirm password</EmailLabelWrapper>}
            name="confirmPassword"
            dependencies={['password']}
            rules={[
              { required: true, message: 'Please confirm your password!' },
              ({ getFieldValue }) => ({
                validator(_, value) {
                  if (!value || getFieldValue('password') === value) {
                    return Promise.resolve();
                  }
                  return Promise.reject(new Error('Passwords do not match!'));
                },
              }),
            ]}
          >
            <AuthTextField type="password" placeholder="Confirm new password" />
          </Form.Item>

          <Form.Item>
            <FormButtonWrapper>
              <PrimaryButton type="primary" htmlType="submit" loading={submitting} block>
                Update password
              </PrimaryButton>
            </FormButtonWrapper>
          </Form.Item>
        </Form>

        <FormFooter>
          <FormFooterText>Link expires 30 minutes after it is sent.</FormFooterText>

          <SecondaryButton onClick={() => navigate(PAGE_PATH.LOGIN)}>Cancel</SecondaryButton>
        </FormFooter>
      </ResetPasswordForm>
    </Wrapper>
  );
};
