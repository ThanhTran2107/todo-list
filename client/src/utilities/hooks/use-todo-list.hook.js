import { ConfirmDeletionModal } from '@/pages/todo-list-page/components/confirm-deletion-modal.component';
import { API_ENDPOINTS, MODAL_TITLES, PAGE_PATH, STATUS_VALUES, STORAGE_KEYS } from '@/utilities/constants';
import { useGetTodos } from '@/utilities/hooks/use-get-todos.hook';
import { todoApi } from '@/utilities/services/api.service';
import { handleUnauthorized } from '@/utilities/services/auth-utils.service';
import { setLocalStorage } from '@/utilities/services/storage.service';
import { removeVietnameseTones } from '@/utilities/services/text-processing.service';
import { faSignOutAlt } from '@fortawesome/free-solid-svg-icons';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { message } from 'antd';
import Cookies from 'js-cookie';
import { filter, find, isEmpty, map } from 'lodash-es';
import { useEffect, useRef, useState } from 'react';
import { useNavigate } from 'react-router-dom';

const { TODO_LIST, ORIGINAL_LIST } = STORAGE_KEYS;
const { DELETE_ALL_TASKS, DELETE_A_TASK } = MODAL_TITLES;

export const useTodoList = () => {
  const navigate = useNavigate();
  const [todoList, setTodoList] = useState([]);
  const [originalList, setOriginalList] = useState([]);
  const [searchedList, setSearchedList] = useState([]);

  const [completedCount, setCompletedCount] = useState(0);
  const [uncompletedCount, setUncompletedCount] = useState(0);

  const [viewTask, setViewTask] = useState(null);

  const hasResetFilterRef = useRef(0);

  const { todos: fetchedTodos, isLoading } = useGetTodos();

  // Function to update completed and uncompleted task counts
  const updateStatistics = list => {
    const completedCount = filter(list, todo => todo.completed === true);
    const uncompletedCount = filter(list, todo => todo.completed === false);

    setCompletedCount(completedCount.length);
    setUncompletedCount(uncompletedCount.length);
  };

  // Function to view task details
  const handleViewTaskDetails = task => setViewTask(task);

  // Function to close view details modal
  const handleCloseViewModal = () => setViewTask(null);

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

  // Function to toggle the completion status of a task
  const handleCompleteTask = async id => {
    const todo = find(todoList, t => t.id === id);

    if (!todo) return;

    const newStatus = todo.status === STATUS_VALUES.COMPLETED ? STATUS_VALUES.IN_PROGRESS : STATUS_VALUES.COMPLETED;

    try {
      await todoApi.put(API_ENDPOINTS.TODO_BY_ID.replace('{id}', id), {
        title: todo.title,
        description: todo.description,
        status: newStatus,
        completed: newStatus === STATUS_VALUES.COMPLETED,
        dueDate: todo.dueDate,
        priority: todo.priority,
      });

      const updateItemStatus = list =>
        map(list, t => {
          if (t.id === id) {
            return {
              ...t,
              completed: newStatus === STATUS_VALUES.COMPLETED,
              status: newStatus,
            };
          }

          return t;
        });

      const updatedTodoList = updateItemStatus(todoList);
      const updatedSearchedList = updateItemStatus(searchedList);
      const updatedOriginalList = updateItemStatus(originalList);

      setTodoList(updatedTodoList);
      setSearchedList(updatedSearchedList);
      setOriginalList(updatedOriginalList);

      message.success('Update the task status successfully!', 1);
    } catch (e) {
      if (e.response?.status === 401) return handleUnauthorized();

      message.error('Failed to update task status', 1);
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
  const handleAddNewTodo = newTask => {
    setTodoList(prev => [newTask, ...prev]);
    setOriginalList(prev => [newTask, ...prev]);
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

  // Function to update the task
  const handleUpdateTask = async updatedTask => {
    try {
      await todoApi.put(API_ENDPOINTS.TODO_BY_ID.replace('{id}', updatedTask.id), updatedTask);

      const updatedItem = list =>
        map(list, todo => {
          if (todo.id === updatedTask.id) return { ...updatedTask };

          return todo;
        });

      const updatedTodoList = updatedItem(todoList);
      const updatedSearchedList = updatedItem(searchedList);
      const updatedOriginalList = updatedItem(originalList);

      setTodoList(updatedTodoList);
      setSearchedList(updatedSearchedList);
      setOriginalList(updatedOriginalList);

      message.success('Update the task successfully!', 1);
    } catch (e) {
      if (e.response?.status === 401) return handleUnauthorized();

      message.error('Failed to update the task!', 1);
    }
  };

  // Function to delete a specific task
  const handleDeleteTask = id => {
    ConfirmDeletionModal({
      onOk: async () => {
        try {
          const response = await todoApi.delete(API_ENDPOINTS.TODO_BY_ID.replace('{id}', id));

          const deleteItem = list => filter(list, todo => todo.id !== id);

          const updatedTodoList = deleteItem(todoList);
          const updatedSearchedList = deleteItem(searchedList);
          const updatedOriginalList = deleteItem(originalList);

          setTodoList(updatedTodoList);
          setOriginalList(updatedOriginalList);
          setSearchedList(updatedSearchedList);

          message.success(response.data?.message, 1);
        } catch (e) {
          if (e.response?.status === 401) return handleUnauthorized();

          message.error(e.response?.data?.error, 1);
        }
      },
      title: DELETE_A_TASK,
    });
  };

  // Function to delete all tasks
  const handleDeleteAllTasks = async () => {
    ConfirmDeletionModal({
      onOk: async () => {
        try {
          const response = await todoApi.delete(API_ENDPOINTS.TODOS);

          setTodoList([]);
          setOriginalList([]);
          setSearchedList([]);

          message.success(response.data?.message, 1);
        } catch (e) {
          if (e.response?.status === 401) return handleUnauthorized();

          message.error(e.response?.data?.error, 1);
        }
      },
      title: DELETE_ALL_TASKS,
    });
  };

  const userMenuItems = [
    {
      key: 'logout',
      icon: <FontAwesomeIcon icon={faSignOutAlt} />,
      label: 'Logout',
      onClick: async () => {
        try {
          const response = await todoApi.post(API_ENDPOINTS.LOGOUT);

          message.success(response.data.message, 1);
          Cookies.remove(STORAGE_KEYS.AUTH_TOKEN);
          navigate(PAGE_PATH.LOGIN, { replace: true });
        } catch (e) {
          message.error(e.response?.data?.error, 1);
        }
      },
      danger: true,
    },
  ];

  useEffect(() => {
    if (!isLoading && fetchedTodos.length > 0) {
      updateStatistics(fetchedTodos);
      setTodoList(fetchedTodos);
      setOriginalList(fetchedTodos);
    }
  }, [fetchedTodos, isLoading]);

  useEffect(() => {
    updateStatistics(todoList);

    setLocalStorage(TODO_LIST, [...todoList]);
    setLocalStorage(ORIGINAL_LIST, [...originalList]);
  }, [todoList, originalList]);

  return {
    todoList,
    isLoading,
    completedCount,
    uncompletedCount,
    viewTask,
    hasResetFilterRef,
    handleViewTaskDetails,
    handleCloseViewModal,
    handleCompleteTask,
    handleResetOriginalData,
    handleAddNewTodo,
    handleSearchTasksByName,
    handleFilterData,
    handleUpdateTask,
    handleDeleteTask,
    handleDeleteAllTasks,
    userMenuItems,
  };
};
