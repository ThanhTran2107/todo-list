import { Typography } from '@/components/antd/typography.component';
import { COLORS } from '@/utilities/constant';
import styled from 'styled-components';

export const LoginContainer = styled.div`
  display: flex;
  justify-content: center;
  align-items: center;
  min-height: 100vh;
  background-color: var(--background-color);
`;

export const LoginForm = styled.div`
  width: 100%;
  max-width: 400px;
  padding: 2rem;
  background: white;
  border-radius: 8px;
  box-shadow: ${COLORS.BOX_SHADOW};
`;

export const FormTitle = styled.h2`
  display: flex;
  align-items: center;
  justify-content: center;
  text-align: center;
  margin-bottom: 2rem;
  color: var(--text-color);
  font-size: 2rem;
`;

export const RegisterLink = styled(Typography.Link)`
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
