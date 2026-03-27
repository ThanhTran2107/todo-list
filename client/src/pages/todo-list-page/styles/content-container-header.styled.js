import { Button } from '@/antd-components/button.component';
import { DatePicker } from '@/antd-components/date-picker.component';
import { Dropdown } from '@/antd-components/dropdown.component';
import { Select } from '@/antd-components/select.component';
import { COLORS } from '@/utilities/constants';
import styled from 'styled-components';

export const HeaderWrapper = styled.div`
  width: 100%;
  position: sticky;
  top: 0;
  z-index: 10;
  padding: 1rem 0.2rem;
  background-color: ${COLORS.WHITE};
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
  width: 20rem;
  margin-right: 0.7rem;
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

export const PriorityFilterContainer = styled.div`
  display: flex;
  align-items: center;
  gap: 0.5rem;
`;

export const PriorityButton = styled.button`
  border: 0.0625rem solid ${COLORS.LIGHT_GRAY};
  background-color: ${({ active }) => (active ? COLORS.DARK_BLUE : COLORS.GHOST_WHITE)};
  color: ${({ active }) => (active ? COLORS.WHITE : COLORS.DARK_BLUE)};
  border-radius: 0.5rem;
  padding: 0.5rem 0.8rem;
  font-size: 0.8rem;
  font-weight: 500;
  cursor: pointer;
  min-width: 80px;
  transition: all 0.15s ease;

  &:hover {
    border-color: ${({ active }) => (active ? 'none' : COLORS.DARK_BLUE)};
    color: ${({ active }) => (active ? COLORS.WHITE : COLORS.DARK_BLUE)};
    background-color: ${({ active }) => (active ? COLORS.DARK_BLUE : COLORS.GHOST_WHITE)};
  }

  &:disabled {
    opacity: 0.55;
    cursor: not-allowed;
  }
`;

export const DeleteAllButton = styled(Button)`
  display: flex;
  align-items: center;
  gap: 0.5rem;
  border-radius: 0.5rem;
  margin-left: 0.4rem;
`;
