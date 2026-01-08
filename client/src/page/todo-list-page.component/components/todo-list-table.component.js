import { Divider } from '@/components/antd/divider.component';
import { Space } from '@/components/antd/space.component';
import { Table } from '@/components/antd/table.component';
import { faCheck, faEdit, faRedo, faTrash } from '@fortawesome/free-solid-svg-icons';
import { useState } from 'react';

import {
  CompleteButton,
  DeleteButton,
  EditButton,
  RowName,
  StyledTable,
  TableWrapper,
  UncompleteButton,
} from '../styles/todo-list-table.styled';
import { EditTaskNameModal } from './edit-task-name-modal.component';

const { Column } = Table;

// TodoList component that displays the list of tasks in a table
export const TodoList = ({ todoList, onComplete, onDelete, onUpdateTaskName }) => {
  const [editRowId, setEditRowId] = useState(null);

  // Function to select a row for updating
  const handleSelectRowToUpdate = id => setEditRowId(id);

  // Function to close the edit modal
  const handleCloseEditModal = () => setEditRowId(null);

  return (
    <TableWrapper>
      <StyledTable dataSource={todoList} onChange={() => window.scrollTo({ top: 0, behavior: 'smooth' })}>
        <Column align="center" title="No." key="index" render={(_, __, index) => index + 1} />
        <Column
          align="center"
          title="Title"
          dataIndex="title"
          key="title"
          render={(title, record) => (
            <RowName>
              {editRowId === record.id && (
                <EditTaskNameModal
                  isOpen={editRowId}
                  selectedRow={record}
                  onUpdateTaskName={onUpdateTaskName}
                  onCloseEditModal={handleCloseEditModal}
                />
              )}
              <span
                style={{
                  textDecoration: record.completed ? 'line-through' : 'none',
                }}
              >
                {title}
              </span>

              <EditButton icon={faEdit} onClick={() => handleSelectRowToUpdate(record.id)} />
            </RowName>
          )}
        />
        <Column
          align="center"
          title="Description"
          dataIndex="description"
          key="description"
          render={description => <span>{description || '-'}</span>}
        />
        <Column
          align="center"
          title="Due Date"
          dataIndex="dueDate"
          key="dueDate"
          render={dueDate => <span>{dueDate || '-'}</span>}
        />
        <Column
          align="center"
          title="Priority"
          dataIndex="priority"
          key="priority"
          render={priority => <span>{priority || '-'}</span>}
        />
        <Column
          align="center"
          title="Status"
          dataIndex="status"
          key="status"
          render={status => <span>{status || 'Pending'}</span>}
        />
        <Column
          align="center"
          title="Created At"
          dataIndex="createdAt"
          key="createdAt"
          render={createdAt => <span>{createdAt}</span>}
        />
        <Column
          align="center"
          title="Updated At"
          dataIndex="updatedAt"
          key="updatedAt"
          render={updatedAt => <span>{updatedAt || '-'}</span>}
        />
        <Column
          align="center"
          title="Actions"
          key="actions"
          render={(text, record) => (
            <Space size="middle">
              {!record.completed ? (
                <CompleteButton icon={faCheck} onClick={() => onComplete(record.id)} />
              ) : (
                <UncompleteButton icon={faRedo} onClick={() => onComplete(record.id)} />
              )}

              <Divider type="vertical" />

              <DeleteButton icon={faTrash} onClick={() => onDelete(record.id)} />
            </Space>
          )}
        />
      </StyledTable>
    </TableWrapper>
  );
};
