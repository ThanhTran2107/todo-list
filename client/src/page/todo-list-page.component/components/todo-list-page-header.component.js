import { ComboBox } from '@/components/antd/combobox.component';
import { message } from '@/components/antd/message.component';
import { Space } from '@/components/antd/space.component';
import { COLORS } from '@/utilities/constant';
import { faSearch } from '@fortawesome/free-solid-svg-icons';
import { isEmpty } from 'lodash-es';
import { useState } from 'react';
import { v4 as uuidv4 } from 'uuid';

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

// Header component for the todo list application
export const Header = ({
  todoCount,
  completedCount,
  uncompletedCount,
  hasCurrentTasks,
  hasResetFilter,
  onResetOriginalData,
  onAddTodoList,
  onSearchTasksByName,
  onFilterData,
  onDeleteAllTasks,
}) => {
  const [input, setInput] = useState('');

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

  // Function to handle adding a new task
  const handleClickAddNewTasks = () => {
    onResetOriginalData();

    const newTodo = input.trim();
    const createdAt = new Date();

    if (!newTodo) {
      message.error('Task name cannot be empty!', 1);

      return;
    }

    onAddTodoList({
      id: uuidv4(),
      title: newTodo,
      createdAt: createdAt.toLocaleString(),
      completed: false,
    });

    message.success('Add a task successfully!', 1);
    setInput('');
  };

  return (
    <HeaderWrapper>
      <Title>Workday Task Tracker</Title>

      <HeaderContainer>
        <Space style={{ marginTop: '3rem' }}>
          <StyledTextField
            placeholder="Enter a task..."
            onChange={handleInputChange}
            onKeyDown={e => {
              if (!isEmpty(input) && e.key === 'Enter') handleClickAddNewTasks();
            }}
            value={input}
          />

          <AddButton disabled={!input} type="primary" onClick={() => handleClickAddNewTasks()}>
            Add
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

          <ComboBox
            defaultValue="All"
            value={hasResetFilter === 0 ? 'All' : hasResetFilter === 1 ? 'Completed' : 'Incompleted'}
            style={{
              width: '10rem',
              marginRight: '0.5rem',
              backgroundColor: !hasCurrentTasks ? COLORS.LIGHT_GRAY : undefined,
              cursor: !hasCurrentTasks ? 'not-allowed' : 'pointer',
              border: !hasCurrentTasks ? `1px solid ? ${COLORS.LIGHT_GRAY}` : undefined,
              borderRadius: !hasCurrentTasks ? '0.375rem' : undefined,
            }}
            options={options}
            onChange={value => onFilterData(value)}
            disabled={!hasCurrentTasks}
          />
        </Space>
      </HeaderContainer>
    </HeaderWrapper>
  );
};
