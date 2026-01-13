import { Button } from '@/components/antd/button.component';
import { Dropdown } from '@/components/antd/dropdown.component';
import { Space } from '@/components/antd/space.component';
import { ThemeSelector } from '@/components/shared/theme-selector.component';
import { useTodoList } from '@/utilities/hooks/use-todo-list.hook';
import { faUser } from '@fortawesome/free-solid-svg-icons';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { TodoListPageHeader } from './components/todo-list-page-header.component';
import { TodoListTable } from './components/todo-list-table.component';
import { ViewTaskDetailsModal } from './components/view-task-details-modal.component';
import { Wrapper } from './styles/todo-list-page.styled';

// Main TodoListPage component that manages the entire todo list application
export const TodoListPage = () => {
  const {
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
  } = useTodoList();

  return (
    <Wrapper>
      <Space>
        <Dropdown menu={{ items: userMenuItems }} arrow placement="bottomRight">
          <Button
            type="text"
            icon={<FontAwesomeIcon icon={faUser} style={{ color: 'var(--text-color)', fontSize: '1.1rem' }} />}
          />
        </Dropdown>

        <ThemeSelector />
      </Space>

      <TodoListPageHeader
        todoCount={todoList.length}
        completedCount={completedCount}
        uncompletedCount={uncompletedCount}
        hasCurrentTasks={todoList.length > 0}
        hasResetFilter={hasResetFilterRef.current}
        onAddNewTodo={handleAddNewTodo}
        onSearchTasksByName={handleSearchTasksByName}
        onResetOriginalData={handleResetOriginalData}
        onFilterData={handleFilterData}
        onDeleteAllTasks={handleDeleteAllTasks}
      />

      <TodoListTable
        todoList={todoList}
        isLoading={isLoading}
        onComplete={handleCompleteTask}
        onDelete={handleDeleteTask}
        onUpdateTask={handleUpdateTask}
        onViewDetails={handleViewTaskDetails}
      />

      <ViewTaskDetailsModal isOpen={!!viewTask} task={viewTask} onClose={handleCloseViewModal} />
    </Wrapper>
  );
};
