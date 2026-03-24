import { Space } from '@/antd-components/space.component';
import { PRIORITY_VALUES } from '@/utilities/constants';
import { faArrowDown, faArrowUp, faList, faMinus, faTrash } from '@fortawesome/free-solid-svg-icons';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { map } from 'lodash-es';
import { useState } from 'react';

import {
  DeleteAllButton,
  HeaderContainer,
  HeaderWrapper,
  PriorityButton,
  PriorityFilterContainer,
  StyledDatePicker,
} from '../styles/content-container-header.styled';

// Header component for the todo list application
export const ContentContainerHeader = ({ hasCurrentTasks, onFilterData, onDeleteAllTasks }) => {
  const [filters, setFilters] = useState({
    completed: 0,
    dueDateBefore: null,
    priority: PRIORITY_VALUES.ALL,
  });

  const priorityButtons = [
    { key: PRIORITY_VALUES.ALL, label: 'All', icon: faList },
    { key: PRIORITY_VALUES.HIGH, label: 'High', icon: faArrowUp },
    { key: PRIORITY_VALUES.MEDIUM, label: 'Medium', icon: faMinus },
    { key: PRIORITY_VALUES.LOW, label: 'Low', icon: faArrowDown },
  ];

  const handlePriorityChange = key => {
    const newFilters = { ...filters, priority: key };
    setFilters(newFilters);
    onFilterData(newFilters);
  };

  return (
    <HeaderWrapper>
      <HeaderContainer>
        <Space direction="horizontal" size="small">
          <DeleteAllButton
            icon={<FontAwesomeIcon icon={faTrash} />}
            type="primary"
            danger
            onClick={onDeleteAllTasks}
            disabled={!hasCurrentTasks}
          >
            Delete all
          </DeleteAllButton>

          <PriorityFilterContainer>
            {map(priorityButtons, button => (
              <PriorityButton
                key={button.key || PRIORITY_VALUES.ALL}
                active={filters.priority === button.key}
                onClick={() => handlePriorityChange(button.key)}
                disabled={!hasCurrentTasks}
              >
                <Space align="center" size="small">
                  <FontAwesomeIcon icon={button.icon} />
                  {button.label}
                </Space>
              </PriorityButton>
            ))}
          </PriorityFilterContainer>
        </Space>

        <StyledDatePicker
          placeholder="Filter due date before"
          value={filters.dueDateBefore}
          onChange={date => {
            const newFilters = { ...filters, dueDateBefore: date };
            setFilters(newFilters);
            onFilterData(newFilters);
          }}
          disabled={!hasCurrentTasks}
        />
      </HeaderContainer>
    </HeaderWrapper>
  );
};
