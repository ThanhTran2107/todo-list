import { Dropdown } from '@/components/antd/dropdown.component';
import { Space } from '@/components/antd/space.component';
import { Typography } from '@/components/antd/typography.component';
import { COLORS, PRIORITY_VALUES } from '@/utilities/constants';
import {
  faArrowDown,
  faBars,
  faCheckCircle,
  faCircle,
  faExclamationTriangle,
  faList,
  faMinus,
  faPlus,
  faSearch,
  faTrash,
} from '@fortawesome/free-solid-svg-icons';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { isEmpty } from 'lodash-es';
import { useState } from 'react';

import {
  HeaderContainer,
  HeaderWrapper,
  SearchButton,
  StyledDatePicker,
  StyledSelect,
  StyledTextField,
  Title,
} from '../styles/todo-list-page-header.styled';
import { AddTodoModal } from './add-todo-modal.component';

// Header component for the todo list application
export const TodoListPageHeader = ({
  todoCount,
  hasCurrentTasks,
  onResetOriginalData,
  onAddNewTodo,
  onSearchTasksByName,
  onFilterData,
  onDeleteAllTasks,
}) => {
  const [input, setInput] = useState('');
  const [isModalOpen, setIsModalOpen] = useState(false);
  const [filters, setFilters] = useState({
    completed: 0,
    dueDateBefore: null,
    priority: '',
  });

  const completedOptions = [
    {
      label: (
        <span style={{ display: 'flex', alignItems: 'center', gap: '0.5rem' }}>
          <FontAwesomeIcon icon={faList} />
          All
        </span>
      ),
      value: 0,
    },
    {
      label: (
        <span style={{ display: 'flex', alignItems: 'center', gap: '0.5rem' }}>
          <FontAwesomeIcon icon={faCheckCircle} style={{ color: COLORS.GREEN }} />
          Completed
        </span>
      ),
      value: true,
    },
    {
      label: (
        <span style={{ display: 'flex', alignItems: 'center', gap: '0.5rem' }}>
          <FontAwesomeIcon icon={faCircle} style={{ color: COLORS.RED }} />
          Incompleted
        </span>
      ),
      value: false,
    },
  ];

  const priorityOptions = [
    {
      label: (
        <span style={{ display: 'flex', alignItems: 'center', gap: '0.5rem' }}>
          <FontAwesomeIcon icon={faList} />
          All
        </span>
      ),
      value: '',
    },
    {
      label: (
        <span style={{ display: 'flex', alignItems: 'center', gap: '0.5rem' }}>
          <FontAwesomeIcon icon={faExclamationTriangle} style={{ color: COLORS.RED }} />
          High
        </span>
      ),
      value: PRIORITY_VALUES.HIGH,
    },
    {
      label: (
        <span style={{ display: 'flex', alignItems: 'center', gap: '0.5rem' }}>
          <FontAwesomeIcon icon={faMinus} style={{ color: COLORS.CYBER_YELLOW }} />
          Medium
        </span>
      ),
      value: PRIORITY_VALUES.MEDIUM,
    },
    {
      label: (
        <span style={{ display: 'flex', alignItems: 'center', gap: '0.5rem' }}>
          <FontAwesomeIcon icon={faArrowDown} style={{ color: COLORS.BLUE_GREEN }} />
          Low
        </span>
      ),
      value: PRIORITY_VALUES.LOW,
    },
  ];

  // Function to handle input change and reset data if empty
  const handleInputChange = e => {
    if (isEmpty(e.target.value)) onResetOriginalData();

    setInput(e.target.value);
  };

  // Function to open add todo modal
  const handleOpenAddModal = () => setIsModalOpen(true);

  // Function to close add todo modal
  const handleCloseAddModal = () => setIsModalOpen(false);

  const actionMenuItems = [
    {
      label: (
        <span style={{ display: 'flex', alignItems: 'center', gap: '0.5rem' }}>
          <FontAwesomeIcon icon={faPlus} />
          Add a new task
        </span>
      ),
      key: 'add',
      onClick: handleOpenAddModal,
    },
    {
      label: (
        <span style={{ display: 'flex', alignItems: 'center', gap: '0.5rem' }}>
          <FontAwesomeIcon icon={faTrash} />
          Delete all tasks
        </span>
      ),
      key: 'delete',
      onClick: () => onDeleteAllTasks(),
      disabled: todoCount === 0,
      danger: true,
    },
  ];

  return (
    <HeaderWrapper>
      <Title>Workday Task Tracker</Title>

      <HeaderContainer>
        <Space direction="vertical" style={{ marginTop: '3.5rem' }}>
          <Space>
            <StyledTextField placeholder="Enter a task..." onChange={handleInputChange} value={input} />

            <SearchButton icon={faSearch} onClick={() => onSearchTasksByName(input)} />

            <Dropdown menu={{ items: actionMenuItems }} trigger={['click']} placement="bottomLeft" arrow>
              <FontAwesomeIcon
                icon={faBars}
                style={{
                  cursor: 'pointer',
                  color: COLORS.BRIGHT_BLUE,
                  fontSize: '1.5rem',
                  marginLeft: '0.5rem',
                }}
              />
            </Dropdown>
          </Space>
        </Space>

        <Space direction="vertical" style={{ display: 'flex', alignItems: 'flex-end' }}>
          <p style={{ marginRight: '0.5rem' }}>{todoCount > 1 ? `${todoCount} Tasks` : `${todoCount} Task`}</p>

          <Space direction="horizontal" size="small">
            <Typography.Text>Due Date</Typography.Text>
            <StyledDatePicker
              placeholder="Due Date Before"
              value={filters.dueDateBefore}
              onChange={date => {
                const newFilters = { ...filters, dueDateBefore: date };
                setFilters(newFilters);
                onFilterData(newFilters);
              }}
              disabled={!hasCurrentTasks}
            />

            <Typography.Text>Status</Typography.Text>
            <StyledSelect
              placeholder="Completed"
              options={completedOptions}
              value={filters.completed}
              onChange={value => {
                const newFilters = { ...filters, completed: value };
                setFilters(newFilters);
                onFilterData(newFilters);
              }}
              disabled={!hasCurrentTasks}
            />

            <Typography.Text>Priority</Typography.Text>
            <StyledSelect
              placeholder="Priority"
              options={priorityOptions}
              value={filters.priority}
              onChange={value => {
                const newFilters = { ...filters, priority: value };
                setFilters(newFilters);
                onFilterData(newFilters);
              }}
              disabled={!hasCurrentTasks}
            />
          </Space>
        </Space>
      </HeaderContainer>

      <AddTodoModal isOpen={isModalOpen} onAddNewTodo={onAddNewTodo} onClose={handleCloseAddModal} />
    </HeaderWrapper>
  );
};
