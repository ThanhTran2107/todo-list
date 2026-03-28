import { Space } from '@/antd-components/space.component';
import { SideBar } from '@/layouts/side-bar.component';
import { TopBar } from '@/layouts/top-bar.component';
import { useTodoList } from '@/utilities/hooks/use-todo-list.hook';

import { ContentContainerHeader } from './components/content-container-header.component';
import { TodoListTable } from './components/todo-list-table.component';
import { ViewTaskDetailsModal } from './components/view-task-details-modal.component';
import { ContentContainer, Wrapper } from './styles/todo-list-page.styled';

// Main TodoListPage component that manages the entire todo list application
export const TodoListPage = () => {
  const {
    todoList,
    fetchedTodos,
    isLoading,
    viewTask,
    currentPriorityFilter,
    currentDueDateFilter,
    handleViewTaskDetails,
    handleCloseViewModal,
    handleCompleteTask,
    handleResetOriginalData,
    handleAddNewTodo,
    handleSearchTasksByName,
    handleFilterStatus,
    handleFilterPriority,
    handleFilterDueDate,
    handleUpdateTask,
    handleDeleteTask,
    handleDeleteAllTasks,
  } = useTodoList();

  return (
    <Wrapper>
      <TopBar onResetOriginalData={handleResetOriginalData} onSearchTasksByName={handleSearchTasksByName} />

      <Space align="start">
        <SideBar onAddNewTodo={handleAddNewTodo} onFilterStatus={handleFilterStatus} />

        <ContentContainer>
          <ContentContainerHeader
            hasCurrentTasks={fetchedTodos.length > 0}
            currentPriority={currentPriorityFilter}
            currentDueDate={currentDueDateFilter}
            onFilterPriority={handleFilterPriority}
            onFilterDueDate={handleFilterDueDate}
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
        </ContentContainer>
      </Space>

      <ViewTaskDetailsModal isOpen={!!viewTask} task={viewTask} onClose={handleCloseViewModal} />
    </Wrapper>
  );
};
