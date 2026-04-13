import { Form } from '@/antd-components/form.component';
import { PAGE_PATH } from '@/utilities/constants';
import { useResetPassword } from '@/utilities/hooks/use-reset-password.hook.js';
import { useMemo } from 'react';
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
} from './styles/reset-password-page.styled';

export const ResetPasswordPage = () => {
  const [form] = Form.useForm();
  const navigate = useNavigate();
  const [searchParams] = useSearchParams();

  const token = useMemo(() => searchParams.get('token')?.trim() ?? '', [searchParams]);

  const { phase, userEmail, isSubmitting, handleReset, getPasswordRules, getConfirmRules } = useResetPassword(token);

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
            rules={getPasswordRules()}
          >
            <AuthTextField type="password" placeholder="Enter new password" />
          </Form.Item>
          <Form.Item
            label={<EmailLabelWrapper size={270}>Confirm password</EmailLabelWrapper>}
            name="confirmPassword"
            dependencies={['password']}
            rules={getConfirmRules()}
          >
            <AuthTextField type="password" placeholder="Confirm new password" />
          </Form.Item>
          <Form.Item>
            <FormButtonWrapper>
              <PrimaryButton type="primary" htmlType="submit" loading={isSubmitting} block>
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
