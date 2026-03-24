import { Image } from '@/antd-components/image.component';
import { TextField } from '@/antd-components/input.component';
import { COLORS } from '@/utilities/constants';
import styled from 'styled-components';

export const TopBarContainer = styled.div`
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 0.5rem 1rem;
  border-bottom: 0.0625rem solid ${COLORS.LIGHT_GRAY};
  background-color: ${COLORS.WHITE};
`;

export const Title = styled.h3`
  font-size: 1.5rem;
  color: ${COLORS.DARK_BLUE};
`;

export const StyledTextField = styled(TextField)`
  width: 20rem;
  height: 2.3rem;
  margin-left: 0.5rem;

  @media (max-width: 768px) {
    width: 10rem;
  }
`;

export const TodoImage = styled(Image)`
  width: 2rem !important;
  height: 2rem !important;
`;
