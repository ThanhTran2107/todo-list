import { Button } from '@/components/antd/button.component';
import { Dropdown } from '@/components/antd/dropdown.component';
import { message } from '@/components/antd/message.component';
import { Space } from '@/components/antd/space.component';
import { ThemeSelector } from '@/components/shared/theme-selector.component';
import { API_ENDPOINTS, MODAL_TITLES, STORAGE_KEYS } from '@/utilities/constant';
import { todoApi } from '@/utilities/services/api.service';
import { getLocalStorage, setLocalStorage } from '@/utilities/services/storage.service';
import { removeVietnameseTones } from '@/utilities/services/text-processing.service';
import { faSignOutAlt, faUser } from '@fortawesome/free-solid-svg-icons';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import Cookies from 'js-cookie';
import { filter, isEmpty, map } from 'lodash-es';
import { useEffect, useRef, useState } from 'react';
import { useNavigate } from 'react-router-dom';

import { ConfirmDeletionModal } from './components/confirm-deletion-modal.component';
import { Header } from './components/todo-list-page-header.component';
import { TodoList } from './components/todo-list-table.component';
import { Wrapper } from './styles/todo-list-page.styled';

const { TODO_LIST, ORIGINAL_LIST } = STORAGE_KEYS;
const { DELETE_ALL_TASKS, DELETE_A_TASK } = MODAL_TITLES;

// Main TodoListPage component that manages the entire todo list application
export const TodoListPage = () => {
  const [todoList, setTodoList] = useState([]);
  const [originalList, setOriginalList] = useState([]);
  const [searchedList, setSearchedList] = useState([]);

  const [completedCount, setCompletedCount] = useState(0);
  const [uncompletedCount, setUncompletedCount] = useState(0);

  const hasResetFilterRef = useRef(0);

  const navigate = useNavigate();

  // Function to update completed and uncompleted task counts
  const updateStatistics = list => {
    const completedCount = filter(list, todo => todo.completed === true);
    const uncompletedCount = filter(list, todo => todo.completed === false);

    setCompletedCount(completedCount.length);
    setUncompletedCount(uncompletedCount.length);
  };

  // Function to apply filter based on completion status
  const applyFilter = (data, value) => {
    switch (value) {
      case 0:
        hasResetFilterRef.current = 0;

        return data;

      case true:
        hasResetFilterRef.current = 1;

        return filter(data, todo => todo.completed);

      case false:
        hasResetFilterRef.current = 2;

        return filter(data, todo => !todo.completed);

      default:
        return data;
    }
  };

  // Function to reset todo list to original data
  const handleResetOriginalData = () => {
    if (isEmpty(originalList)) return;

    hasResetFilterRef.current = 0;

    setTodoList(originalList);
    setSearchedList([]);
  };

  // Function to reset todo list to searched data
  const handleResetSearchedData = () => setTodoList(searchedList);

  // Function to add a new task to the todo list
  const handleAddNewTasks = newTask => {
    setTodoList(prev => [newTask, ...prev]);
    setOriginalList(prev => [newTask, ...prev]);
  };

  // Function to toggle the completion status of a task
  const handleCompleteTask = id => {
    const updateItemStatus = list =>
      map(list, todo => {
        if (todo.id === id) return { ...todo, completed: !todo.completed };

        return todo;
      });

    const updatedTodoList = updateItemStatus(todoList);
    const updatedSearchedList = updateItemStatus(searchedList);
    const updatedOriginalList = updateItemStatus(originalList);

    setTodoList(updatedTodoList);
    setSearchedList(updatedSearchedList);
    setOriginalList(updatedOriginalList);

    message.success('Update the task status successfully!', 1);
  };

  // Function to search tasks by name
  const handleSearchTasksByName = name => {
    const searchName = removeVietnameseTones(name.trim());

    if (!searchName) return;

    const found = filter(originalList, todo => removeVietnameseTones(todo.title).includes(searchName));

    setTodoList(found);
    setSearchedList(found);
  };

  // Function to filter tasks based on completion status
  const handleFilterData = value => {
    const hasSearch = !isEmpty(searchedList);
    const hasOriginal = !isEmpty(originalList);

    if (!hasOriginal) setOriginalList(todoList);

    if (value === 0) {
      hasSearch ? handleResetSearchedData() : handleResetOriginalData();

      return;
    }

    const sourceData = hasSearch ? searchedList : hasOriginal ? originalList : todoList;

    const result = applyFilter(sourceData, value);

    setTodoList(result);
  };

  // Function to update the name of a task
  const handleUpdateTaskName = updatedTaskName => {
    const updatedItemName = list =>
      map(list, todo => {
        if (todo.id === updatedTaskName.id) return { ...updatedTaskName };

        return todo;
      });

    const updatedTodoList = updatedItemName(todoList);
    const updatedSearchedList = updatedItemName(searchedList);
    const updatedOriginalList = updatedItemName(originalList);

    setTodoList(updatedTodoList);
    setSearchedList(updatedSearchedList);
    setOriginalList(updatedOriginalList);

    message.success('Update the task name successfully!', 1);
  };

  // Function to delete a specific task
  const handleDeleteTask = id => {
    ConfirmDeletionModal({
      onOk: () => {
        const deleteItem = list => filter(list, todo => todo.id !== id);

        const updatedTodoList = deleteItem(todoList);
        const updatedSearchedList = deleteItem(searchedList);
        const updatedOriginalList = deleteItem(originalList);

        setTodoList(updatedTodoList);
        setOriginalList(updatedOriginalList);
        setSearchedList(updatedSearchedList);

        message.success('Delete the task successfully!', 1);
      },
      title: DELETE_A_TASK,
    });
  };

  // Function to delete all tasks
  const handleDeleteAllTasks = () => {
    ConfirmDeletionModal({
      onOk: () => {
        setTodoList([]);
        setOriginalList([]);
        setSearchedList([]);

        message.success('Delete all tasks successfully!', 1);
      },
      title: DELETE_ALL_TASKS,
    });
  };

  // Function to logout the account
  const handleLogout = async () => {
    try {
      const response = await todoApi.post(API_ENDPOINTS.LOGOUT);

      message.success(response.data.message, 1);
      Cookies.remove(STORAGE_KEYS.AUTH_TOKEN);
      navigate('/login', { replace: true });
    } catch (e) {
      console.error(e);
    }
  };

  const userMenuItems = [
    {
      key: 'logout',
      icon: <FontAwesomeIcon icon={faSignOutAlt} />,
      label: 'Logout',
      onClick: handleLogout,
      danger: true,
    },
  ];

  useEffect(() => {
    const todoListData = getLocalStorage(TODO_LIST);
    const originalListData = getLocalStorage(ORIGINAL_LIST);

    if (!isEmpty(originalListData)) {
      updateStatistics(originalListData);
      setTodoList(originalListData);
      setOriginalList(originalListData);

      return;
    }

    updateStatistics(todoListData);
    setTodoList(todoListData);
    setOriginalList(originalListData);
  }, []);

  useEffect(() => {
    updateStatistics(todoList);

    setLocalStorage(TODO_LIST, [...todoList]);
    setLocalStorage(ORIGINAL_LIST, [...originalList]);
  }, [todoList, originalList]);

  return (
    <Wrapper>
      <Space>
        <Dropdown menu={{ items: userMenuItems }} arrow placement="bottomRight">
          <Button type="text" icon={<FontAwesomeIcon icon={faUser} style={{ color: 'var(--text-color)' }} />} />
        </Dropdown>

        <ThemeSelector />
      </Space>

      <Header
        todoCount={todoList.length}
        completedCount={completedCount}
        uncompletedCount={uncompletedCount}
        hasCurrentTasks={!isEmpty(originalList)}
        hasResetFilter={hasResetFilterRef.current}
        onAddTodoList={handleAddNewTasks}
        onSearchTasksByName={handleSearchTasksByName}
        onResetOriginalData={handleResetOriginalData}
        onFilterData={handleFilterData}
        onDeleteAllTasks={handleDeleteAllTasks}
      />

      <TodoList
        todoList={todoList}
        onComplete={handleCompleteTask}
        onDelete={handleDeleteTask}
        onUpdateTaskName={handleUpdateTaskName}
      />
    </Wrapper>
  );
};
