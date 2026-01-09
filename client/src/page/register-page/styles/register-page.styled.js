import { Typography } from '@/components/antd/typography.component';
import { COLORS } from '@/utilities/constant';
import styled from 'styled-components';

export const RegisterContainer = styled.div`
  display: flex;
  justify-content: center;
  align-items: center;
  min-height: 100vh;
  background-color: var(--background-color);
`;

export const RegisterForm = styled.div`
  width: 100%;
  max-width: 25rem;
  padding: 2rem;
  background: white;
  border-radius: 0.5rem;
  box-shadow: ${COLORS.BOX_SHADOW};
`;

export const FormTitle = styled.h2`
  display: flex;
  align-items: center;
  justify-content: center;
  text-align: center;
  margin-bottom: 2rem;
  font-size: 2rem;
  color: ${COLORS.BLUE};
`;

export const LoginLink = styled(Typography.Link)`
  display: block;
  text-align: center;
  text-decoration: none;
  color: ${COLORS.BLACK} !important;
  margin-top: 0.5rem;

  &:hover {
    text-decoration: underline;
    color: ${COLORS.BLACK} !important;
  }
`;
