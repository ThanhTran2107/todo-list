import { Select } from '@/components/antd/select.component';
import { Space } from '@/components/antd/space.component';
import { COLORS } from '@/utilities/constants';
import { faSearch } from '@fortawesome/free-solid-svg-icons';
import { isEmpty } from 'lodash-es';
import { useState } from 'react';

import {
  AddButton,
  DeleteAllButton,
  HeaderContainer,
  HeaderWrapper,
  SearchButton,
  StatisticDropdown,
  StyledTextField,
  Title,
} from '../styles/todo-list-page-header.styled';
import { AddTodoModal } from './add-todo-modal.component';

// Header component for the todo list application
export const TodoListPageHeader = ({
  todoCount,
  completedCount,
  uncompletedCount,
  hasCurrentTasks,
  hasResetFilter,
  onResetOriginalData,
  onAddNewTodo,
  onSearchTasksByName,
  onFilterData,
  onDeleteAllTasks,
}) => {
  const [input, setInput] = useState('');
  const [isModalOpen, setIsModalOpen] = useState(false);

  const items = [
    {
      label: <p>{completedCount} Completed</p>,
      key: completedCount,
    },
    {
      label: <p>{uncompletedCount} Incompleted</p>,
      key: uncompletedCount,
    },
  ];

  const options = [
    { label: 'All', value: 0 },
    { label: 'Completed', value: true },
    { label: 'Incompleted', value: false },
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

  return (
    <HeaderWrapper>
      <Title>Workday Task Tracker</Title>

      <HeaderContainer>
        <Space style={{ marginTop: '3rem' }}>
          <StyledTextField placeholder="Enter a task..." onChange={handleInputChange} value={input} />

          <AddButton type="primary" onClick={handleOpenAddModal}>
            Add Task
          </AddButton>

          <DeleteAllButton disabled={todoCount === 0} onClick={() => onDeleteAllTasks()}>
            Delete All
          </DeleteAllButton>

          <SearchButton icon={faSearch} onClick={() => onSearchTasksByName(input)} />
        </Space>

        <Space direction="vertical">
          <StatisticDropdown menu={{ items }} trigger={['hover']}>
            {todoCount > 1 ? <p>{todoCount} Tasks</p> : <p>{todoCount} Task</p>}
          </StatisticDropdown>

          <Select
            defaultValue="All"
            value={hasResetFilter === 0 ? 'All' : hasResetFilter === 1 ? 'Completed' : 'Incompleted'}
            style={{
              width: '10rem',
              marginRight: '0.5rem',
              backgroundColor: !hasCurrentTasks ? COLORS.LIGHT_GRAY : undefined,
              cursor: !hasCurrentTasks ? 'not-allowed' : 'pointer',
              border: !hasCurrentTasks ? `0.0625rem solid ${COLORS.LIGHT_GRAY}` : undefined,
              borderRadius: !hasCurrentTasks ? '0.375rem' : undefined,
            }}
            options={options}
            onChange={value => onFilterData(value)}
            disabled={!hasCurrentTasks}
          />
        </Space>
      </HeaderContainer>

      <AddTodoModal isOpen={isModalOpen} onAddNewTodo={onAddNewTodo} onClose={handleCloseAddModal} />
    </HeaderWrapper>
  );
};
