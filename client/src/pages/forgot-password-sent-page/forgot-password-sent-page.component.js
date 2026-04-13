import { message } from '@/antd-components/message.component';
import { API_ENDPOINTS, PAGE_PATH } from '@/utilities/constants';
import { useCooldown } from '@/utilities/hooks/use-cooldown.hook.js';
import { todoApi } from '@/utilities/services/api.service';
import { useMemo, useState } from 'react';
import { Navigate, useLocation, useNavigate } from 'react-router-dom';

import {
  DefaultButton,
  DividerText,
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
} from '../forgot-password-sent-page/styles/forgot-password-sent-page.styled';

export const ForgotPasswordSentPage = () => {
  const [isResending, setResending] = useState(false);

  const navigate = useNavigate();
  const location = useLocation();

  const email = useMemo(() => {
    const fromState = location.state?.email;
    return typeof fromState === 'string' ? fromState.trim().toLowerCase() : '';
  }, [location.state]);

  const { cooldown, isOnCooldown, resetCooldown } = useCooldown(email);

  const handleResend = async () => {
    if (isOnCooldown) return message.error(`Please wait ${cooldown} seconds before requesting again.`, 1);

    setResending(true);

    try {
      await todoApi.post(API_ENDPOINTS.FORGOT_PASSWORD, { email });

      message.success('If this email is registered, you will receive another password reset message shortly.', 1);

      resetCooldown();
    } catch (e) {
      message.error(e.response?.data?.error ?? 'Something went wrong. Please try again.', 1);
    } finally {
      setResending(false);
    }
  };

  if (!email) return <Navigate to={PAGE_PATH.FORGOT_PASSWORD} replace />;

  return (
    <Wrapper>
      <ResetPasswordForm>
        <FormTitle>
          <TitleWrapper>
            <TodoImage preview={false} src="/icons8-to-do-list-48.png" alt="Todo" />
            Request received
          </TitleWrapper>
        </FormTitle>

        <FormFooterText>
          If an account <strong>{email}</strong> exists, a password reset email will be sent shortly. Please check your
          inbox and spam folder.
        </FormFooterText>

        <FormFooterText>You can close this page and use the link in the email to reset your password.</FormFooterText>

        <DividerText>
          Didn't receive the email? It may take a few minutes to arrive. Check your spam folder, or wait before
          resending.
        </DividerText>

        <FormButtonWrapper>
          <PrimaryButton type="primary" block loading={isResending} onClick={handleResend}>
            {cooldown > 0 ? `Resend in ${cooldown}s` : 'Resend email'}
          </PrimaryButton>
        </FormButtonWrapper>

        <FormButtonWrapper style={{ marginTop: '0.65rem' }}>
          <DefaultButton type="default" block onClick={() => navigate(PAGE_PATH.LOGIN)}>
            Back to login
          </DefaultButton>
        </FormButtonWrapper>

        <FormFooter>
          <SecondaryButton onClick={() => navigate(PAGE_PATH.FORGOT_PASSWORD)}>Use a different email</SecondaryButton>
        </FormFooter>
      </ResetPasswordForm>
    </Wrapper>
  );
};
