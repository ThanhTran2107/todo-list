import { message } from '@/antd-components/message.component';
import { PAGE_PATH } from '@/utilities/constants';
import { todoApi } from '@/utilities/services/api.service';
import { useEffect, useState } from 'react';
import { useNavigate } from 'react-router-dom';

import { usePasswordValidation } from './use-password-validation.hook.js';

const validateTokenRequest = async token => {
  const { data } = await todoApi.get(
    `${import.meta.env.VITE_APP_API_BASE_URL || ''}/auth/help-with-reset-password?token=${encodeURIComponent(token)}`,
  );
  return data;
};

export const useResetPassword = token => {
  const [phase, setPhase] = useState('loading');
  const [userEmail, setUserEmail] = useState('');
  const [isSubmitting, setIsSubmitting] = useState(false);
  const navigate = useNavigate();

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

  const handleReset = async values => {
    setIsSubmitting(true);

    try {
      await todoApi.post('/auth/reset-password', {
        token,
        newPassword: values.password,
      });

      message.success('Your password has been updated. You can sign in now.', 1);
      navigate(PAGE_PATH.LOGIN, { replace: true, state: { email: userEmail } });
    } catch (e) {
      message.error(e.response?.data?.error ?? 'Could not reset password. Please try again.', 1);
    } finally {
      setIsSubmitting(false);
    }
  };

  const { getPasswordRules, getConfirmRules } = usePasswordValidation();

  return {
    phase,
    userEmail,
    isSubmitting,
    handleReset,
    getPasswordRules,
    getConfirmRules,
  };
};
