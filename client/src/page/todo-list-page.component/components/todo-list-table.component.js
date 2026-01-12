import { Button } from '@/components/antd/button.component';
import { Dropdown } from '@/components/antd/dropdown.component';
import { Spin } from '@/components/antd/spin.component';
import { Table } from '@/components/antd/table.component';
import { COLORS, PRIORITY_LEVELS, STATUS_TYPES, STATUS_VALUES } from '@/utilities/constants';
import { formatDate } from '@/utilities/services/format-date.service';
import { formatDescription, truncateText } from '@/utilities/services/text-format.service';
import { faCheck, faEdit, faEllipsisV, faEye, faRedo, faTrash } from '@fortawesome/free-solid-svg-icons';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { useState } from 'react';

import { RowName, StyledTable, TableWrapper } from '../styles/todo-list-table.styled';
import { EditTaskModal } from './edit-task-modal.component';

const { Column } = Table;

// TodoList component that displays the list of tasks in a table
export const TodoListTable = ({ todoList, isLoading, onComplete, onDelete, onUpdateTask, onViewDetails }) => {
  const [editRowId, setEditRowId] = useState(null);

  // Function to select a row for updating
  const handleSelectRowToUpdate = id => setEditRowId(id);

  // Function to close the edit modal
  const handleCloseEditModal = () => setEditRowId(null);

  return (
    <TableWrapper>
      <StyledTable
        loading={{
          spinning: isLoading,
          indicator: <Spin />,
        }}
        dataSource={todoList}
        onChange={() => window.scrollTo({ top: 0, behavior: 'smooth' })}
      >
        <Column align="center" title="No." key="index" render={(_, __, index) => index + 1} />
        <Column
          title="Title"
          dataIndex="title"
          key="title"
          render={(title, record) => (
            <RowName>
              {editRowId === record.id && (
                <EditTaskModal
                  isOpen={editRowId}
                  selectedRow={record}
                  onUpdateTask={onUpdateTask}
                  onCloseEditModal={handleCloseEditModal}
                />
              )}
              <span
                style={{
                  textDecoration: record.status === STATUS_VALUES.COMPLETED ? 'line-through' : 'none',
                  color: STATUS_TYPES[record.status] === STATUS_TYPES.OVERDUE ? COLORS.RED : 'inherit',
                }}
              >
                {truncateText(title)}
              </span>
            </RowName>
          )}
        />
        <Column
          title="Description"
          dataIndex="description"
          key="description"
          render={(description, record) => (
            <span style={{ color: STATUS_TYPES[record.status] === STATUS_TYPES.OVERDUE ? COLORS.RED : 'inherit' }}>
              {formatDescription(description)}
            </span>
          )}
        />
        <Column
          title="Due Date"
          dataIndex="dueDate"
          key="dueDate"
          render={(dueDate, record) => (
            <span style={{ color: STATUS_TYPES[record.status] === STATUS_TYPES.OVERDUE ? COLORS.RED : 'inherit' }}>
              {formatDate(dueDate)}
            </span>
          )}
        />
        <Column
          align="center"
          title="Priority"
          dataIndex="priority"
          key="priority"
          render={(priority, record) => (
            <span style={{ color: STATUS_TYPES[record.status] === STATUS_TYPES.OVERDUE ? COLORS.RED : 'inherit' }}>
              {PRIORITY_LEVELS[priority] || '-'}
            </span>
          )}
        />
        <Column
          align="center"
          title="Status"
          dataIndex="status"
          key="status"
          render={(status, record) => {
            let color =
              record.status === STATUS_VALUES.OVERDUE
                ? COLORS.RED
                : record.status === STATUS_VALUES.COMPLETED
                  ? COLORS.GREEN
                  : 'inherit';

            let fontWeight = record.status === STATUS_VALUES.COMPLETED ? 'bold' : 'normal';
            
            return <span style={{ color, fontWeight }}>{STATUS_TYPES[status] || STATUS_TYPES.PENDING}</span>;
          }}
        />
        <Column
          title="Created At"
          dataIndex="createdAt"
          key="createdAt"
          render={(createdAt, record) => (
            <span style={{ color: STATUS_TYPES[record.status] === STATUS_TYPES.OVERDUE ? COLORS.RED : 'inherit' }}>
              {formatDate(createdAt)}
            </span>
          )}
        />
        <Column
          title="Updated At"
          dataIndex="updatedAt"
          key="updatedAt"
          render={(updatedAt, record) => (
            <span style={{ color: STATUS_TYPES[record.status] === STATUS_TYPES.OVERDUE ? COLORS.RED : 'inherit' }}>
              {formatDate(updatedAt)}
            </span>
          )}
        />
        <Column
          align="center"
          title="Actions"
          key="actions"
          render={(_, record) => {
            const menuItems = [
              {
                key: 'view',
                icon: <FontAwesomeIcon icon={faEye} />,
                label: 'View Details',
                onClick: () => onViewDetails(record),
              },
              {
                type: 'divider',
              },
              {
                key: 'edit',
                icon: <FontAwesomeIcon icon={faEdit} />,
                label: 'Edit',
                onClick: () => handleSelectRowToUpdate(record.id),
              },
              {
                type: 'divider',
              },
              {
                key: 'complete',
                icon: <FontAwesomeIcon icon={record.status === STATUS_VALUES.COMPLETED ? faRedo : faCheck} />,
                label: record.status === STATUS_VALUES.COMPLETED ? 'Mark as Incomplete' : 'Mark as Complete',
                onClick: () => onComplete(record.id),
                disabled: record.status === STATUS_VALUES.OVERDUE,
              },
              {
                type: 'divider',
              },
              {
                key: 'delete',
                icon: <FontAwesomeIcon icon={faTrash} />,
                label: 'Delete',
                onClick: () => onDelete(record.id),
                danger: true,
              },
            ];

            return (
              <Dropdown menu={{ items: menuItems }} trigger={['click']} placement="bottomRight" arrow>
                <Button type="text" icon={<FontAwesomeIcon icon={faEllipsisV} />} />
              </Dropdown>
            );
          }}
        />
      </StyledTable>
    </TableWrapper>
  );
};
