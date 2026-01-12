import { Button } from '@/components/antd/button.component';
import { Dropdown } from '@/components/antd/dropdown.component';
import { TextField } from '@/components/antd/text-field.component';
import { COLORS } from '@/utilities/constants';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import styled from 'styled-components';

export const HeaderWrapper = styled.div`
  width: 100%;
`;

export const Title = styled.h3`
  display: flex;
  justify-content: center;
  font-size: 2rem;
  color: ${COLORS.BLUE_GREEN};
`;

export const StyledTextField = styled(TextField)`
  width: 30rem;
  height: 2.5rem;
  margin-left: 0.5rem;

  @media (max-width: 768px) {
    width: 10rem;
  }
`;

const DisabledButtonStyle = `
  &:disabled {
    background-color: ${COLORS.LIGHT_GRAY} !important;
    cursor: not-allowed;
    opacity: 1;
  }
`;

export const AddButton = styled(Button)`
  height: 2.5rem;
  cursor: pointer;
  ${DisabledButtonStyle}
`;

export const DeleteAllButton = styled(Button)`
  height: 2.5rem;
  cursor: pointer;
  ${DisabledButtonStyle}
`;

export const SearchButton = styled(FontAwesomeIcon)`
  display: flex;
  align-seft: center;
  height: 1.5rem;
  color: ${COLORS.BRIGHT_BLUE};
  cursor: pointer;

  &:hover {
    color: ${COLORS.BLUE};
  }
`;

export const HeaderContainer = styled.div`
  display: flex;
  justify-content: space-between;
  margin-bottom: 1rem;
`;

export const StatisticDropdown = styled(Dropdown)`
  text-align: right;
  margin-right: 0.5rem;
`;
