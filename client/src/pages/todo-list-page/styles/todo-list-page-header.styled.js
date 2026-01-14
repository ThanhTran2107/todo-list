import { DatePicker } from '@/components/antd/date-picker.component';
import { Dropdown } from '@/components/antd/dropdown.component';
import { TextField } from '@/components/antd/input.component';
import { Select } from '@/components/antd/select.component';
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

export const StyledSelect = styled(Select)`
  width: 10rem;
  margin-right: 0.5rem;
  border-radius: 0.5rem;
  transition: all 0.3s ease;

  &:hover {
    border-color: ${COLORS.BLUE};
  }

  &.ant-select-disabled {
    background-color: ${COLORS.LIGHT_GRAY};
    cursor: not-allowed;
    border: 0.0625rem solid ${COLORS.LIGHT_GRAY};
    border-radius: 0.375rem;
  }
`;

export const StyledDatePicker = styled(DatePicker)`
  width: 10rem;
  margin-right: 0.5rem;
  border-radius: 0.5rem;
  transition: all 0.3s ease;

  &:hover {
    border-color: ${COLORS.BLUE};
  }

  &.ant-picker-disabled {
    background-color: ${COLORS.LIGHT_GRAY};
    cursor: not-allowed;
    border: 0.0625rem solid ${COLORS.LIGHT_GRAY};
    border-radius: 0.375rem;
  }
`;
