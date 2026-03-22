import { Button } from '@/components/antd/button.component';
import { Typography } from '@/components/antd/typography.component';
import { COLORS } from '@/utilities/constants';
import styled from 'styled-components';

export const Wrapper = styled.div`
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  min-height: 100vh;
  background-color: var(--background-color);
`;

export const RegisterForm = styled.div`
  width: 100%;
  max-width: 25rem;
  padding: 2rem;
  background-color: ${COLORS.WHITE};
  border-radius: 0.5rem;
  box-shadow: ${COLORS.BOX_SHADOW};
`;

export const FormDescription = styled.p`
  margin-top: -0.5rem;
  font-size: 0.7rem;
  color: ${COLORS.MEDIUM_GRAY};
  font-style: italic;
  text-align: center;
`;

export const FormTitle = styled.h2`
  display: flex;
  align-items: center;
  justify-content: center;
  text-align: center;
  margin-bottom: 2rem;
  font-size: 2rem;
  color: ${COLORS.DARK_BLUE};
`;

export const TitleWrapper = styled.div`
  display: flex;
  align-items: center;
  justify-content: center;
  gap: 0.75rem;
  white-space: nowrap;
`;

export const RegisterIcon = styled.img`
  width: 3rem;
  height: 3rem;
  margin-bottom: 0.5rem;
`;

export const RegisterFormAction = styled.div`
  width: 100%;
  margin-top: 0.5rem;
`;

export const RegisterButton = styled(Button)`
  height: 2.5rem;
  font-size: 1rem;
  background-color: ${COLORS.DARK_BLUE};
  border-color: ${COLORS.DARK_BLUE};
  color: white;

  &:hover {
    background-color: ${COLORS.BRIGHT_BLUE};
    border-color: ${COLORS.BRIGHT_BLUE};
  }
`;

export const LoginLink = styled(Typography.Link)`
  display: inline-flex;
  align-items: center;
  text-decoration: none;
  color: ${COLORS.DARK_BLUE} !important;
  font-weight: 500;
  font-size: 0.9rem;
  line-height: 1;

  &:hover {
    color: ${COLORS.BRIGHT_BLUE} !important;
  }
`;

export const FormFooter = styled.div`
  display: flex;
  align-items: center;
  justify-content: center;
  gap: 0.5rem;
  margin-top: 1rem;
`;

export const FooterText = styled.p`
  font-size: 0.9rem;
  margin: 0;
`;
