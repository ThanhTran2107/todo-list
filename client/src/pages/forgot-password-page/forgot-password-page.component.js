import { Form } from '@/antd-components/form.component';
import { message } from '@/antd-components/message.component';
import { API_ENDPOINTS, PAGE_PATH } from '@/utilities/constants';
import { useCooldown } from '@/utilities/hooks/use-cooldown.hook.js';
import { todoApi } from '@/utilities/services/api.service';
import { useEffect, useRef, useState } from 'react';
import { useNavigate } from 'react-router-dom';

import {
  AuthTextField,
  EmailLabelWrapper,
  ForgotPasswordForm,
  FormButtonWrapper,
  FormFooter,
  FormFooterText,
  FormTitle,
  PrimaryButton,
  SecondaryButton,
  TitleWrapper,
  TodoImage,
  Wrapper,
} from '../forgot-password-page/styles/forgot-password-page.styled';

export const ForgotPasswordPage = () => {
  const [form] = Form.useForm();
  const [isLoading, setIsLoading] = useState(false);

  const emailRef = useRef(null);
  const navigate = useNavigate();

  const email = Form.useWatch('email', form);
  const { cooldown, isOnCooldown, resetCooldown } = useCooldown(email);

  const handleSubmit = async values => {
    const normalizedEmail = values.email.trim().toLowerCase();

    if (isOnCooldown) return message.error(`Please wait ${cooldown} seconds before requesting again.`, 1);

    setIsLoading(true);

    try {
      await todoApi.post(API_ENDPOINTS.FORGOT_PASSWORD, {
        email: normalizedEmail,
      });

      resetCooldown();

      navigate(PAGE_PATH.FORGOT_PASSWORD_SENT, {
        replace: true,
        state: { email: normalizedEmail },
      });
    } catch (e) {
      message.error(e.response?.data?.error ?? 'Something went wrong. Please try again.', 1);
    } finally {
      setIsLoading(false);
    }
  };

  useEffect(() => {
    if (emailRef.current) emailRef.current.focus();
  }, []);

  return (
    <Wrapper>
      <ForgotPasswordForm>
        <Form form={form} name="forgotPasswordForm" layout="vertical" onFinish={handleSubmit} autoComplete="off">
          <FormTitle>
            <TitleWrapper>
              <TodoImage preview={false} src="/icons8-to-do-list-48.png" alt="Todo Icon" />
              Reset password
            </TitleWrapper>
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

          <Form.Item>
            <FormButtonWrapper>
              <PrimaryButton type="primary" htmlType="submit" loading={isLoading} block>
                Send reset email
              </PrimaryButton>
            </FormButtonWrapper>
          </Form.Item>
        </Form>
      </ForgotPasswordForm>

      <FormFooter>
        <FormFooterText>Remember your password?</FormFooterText>

        <SecondaryButton onClick={() => navigate(PAGE_PATH.LOGIN)}>Back to login</SecondaryButton>
      </FormFooter>
    </Wrapper>
  );
};
