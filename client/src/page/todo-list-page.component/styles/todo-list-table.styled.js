import { Table } from '@/components/antd/table.component';
import { COLORS } from '@/utilities/constants';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import styled from 'styled-components';

export const TableWrapper = styled.div`
  width: 100%;

  @media (max-width: 550px) {
    table {
      width: 34.375rem;
    }
  }
`;

export const StyledTable = styled(Table)`
  margin: 0.5rem;

  .ant-pagination-prev,
  .ant-pagination-next {
    background-color: ${COLORS.WHITE} !important;
  }

  .ant-pagination-item {
    background-color: ${COLORS.WHITE} !important;
  }

  .ant-pagination-item:hover {
    border-color: ${COLORS.BLUE} !important;
  }
`;

export const StyledButton = styled(FontAwesomeIcon)`
  font-size: 1.3rem;
  cursor: pointer;
`;

export const CompleteButton = styled(StyledButton)`
  color: ${COLORS.BRIGHT_GREEN};
  cursor: pointer;

  &:hover {
    color: ${COLORS.GREEN};
  }
`;

export const UncompleteButton = styled(StyledButton)`
  color: ${COLORS.BRIGHT_BLUE};
  cursor: pointer;

  &:hover {
    color: ${COLORS.BLUE};
  }
`;

export const DeleteButton = styled(StyledButton)`
  color: ${COLORS.DARK_GRAY};

  &:hover {
    color: ${COLORS.RED};
  }
`;

export const EditButton = styled(StyledButton)`
  color: ${COLORS.BRIGHT_BLUE};

  &:hover {
    color: ${COLORS.BLUE};
  }
`;

export const RowName = styled.div`
  display: flex;
  justify-content: space-between;
`;
