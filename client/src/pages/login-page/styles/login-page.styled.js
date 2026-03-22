import { Button } from '@/components/antd/button.component';
import { Divider } from '@/components/antd/divider.component';
import { Image } from '@/components/antd/image.component';
import { Space } from '@/components/antd/space.component';
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

export const LoginForm = styled.div`
  width: 100%;
  max-width: 25rem;
  padding: 2rem;
  background-color: ${COLORS.WHITE};
  border-radius: 0.5rem;
  box-shadow: ${COLORS.BOX_SHADOW};
`;

export const FormTitle = styled.h2`
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  text-align: center;
  margin-bottom: 2rem;
  font-size: 2rem;
  color: ${COLORS.DARK_BLUE};
`;

export const FormDescription = styled.p`
  margin-top: -0.2rem;
  font-size: 0.7rem;
  color: ${COLORS.MEDIUM_GRAY};
  font-style: italic;
  text-align: center;
`;

export const TitleWrapper = styled.div`
  display: flex;
  align-items: center;
  justify-content: center;
  gap: 0.75rem;
  white-space: nowrap;
`;

export const TodoImage = styled(Image)`
  width: 3rem;
  height: 3rem;
  margin-bottom: 0.5rem;
`;

export const SocialImageButton = styled(Image)`
  width: 2rem !important;
  height: 2rem !important;
  cursor: pointer;
  transition: transform 0.2s ease;

  &:hover {
    transform: scale(1.1);
  }
`;

export const FormButtonWrapper = styled.div`
  width: 100%;
  margin-top: 0.5rem;
`;

export const LoginButton = styled(Button)`
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

export const RegisterLink = styled(Typography.Link)`
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

export const FormFooterText = styled.p`
  font-size: 0.9rem;
  margin: 0;
`;

export const StyledDivider = styled(Divider)`
  border-color: ${COLORS.FOG_GRAY} !important;
`;

export const DividerText = styled.p`
  font-size: 0.7rem;
  color: ${COLORS.MEDIUM_GRAY} !important;
  font-weight: 500;
  margin: 0;
`;

export const PasswordLabelWrapper = styled(Space)`
  display: flex;
  justify-content: space-between;
  align-items: center;
  width: 100%;
`;

export const SocialLoginWrapper = styled.div`
  display: flex;
  justify-content: center;
  align-items: center;
  gap: 10rem;
`;

export const ForgotPasswordButton = styled(Typography.Link)`
  font-size: 0.85rem;
  color: ${COLORS.DARK_BLUE} !important;
  background-color: transparent;
  border: none;
  box-shadow: none;
  padding: 0;
  font-weight: 500;

  &:hover {
    color: ${COLORS.BRIGHT_BLUE} !important;
  }
`;
