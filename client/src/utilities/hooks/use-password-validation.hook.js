import { RESET_PASSWORD_CONFIG } from '@/utilities/constants';

export const usePasswordValidation = () => {
  const getPasswordRules = () => [
    { required: true, message: 'Please enter your password!' },
    {
      min: RESET_PASSWORD_CONFIG.PASSWORD_MIN_LENGTH,
      message: `Password must be at least ${RESET_PASSWORD_CONFIG.PASSWORD_MIN_LENGTH} characters!`,
    },
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
  ];

  const getConfirmRules = () => [
    { required: true, message: 'Please confirm your password!' },
    ({ getFieldValue }) => ({
      validator(_, value) {
        if (!value || getFieldValue('password') === value) return Promise.resolve();

        return Promise.reject(new Error('Passwords do not match!'));
      },
    }),
  ];

  return {
    getPasswordRules,
    getConfirmRules,
  };
};
