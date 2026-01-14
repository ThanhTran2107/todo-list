import { Button } from '@/components/antd/button.component';
import { Dropdown } from '@/components/antd/dropdown.component';
import { COLORS, PRIORITY_LEVELS, STATUS_TYPES, STATUS_VALUES } from '@/utilities/constants';
import { formatDate } from '@/utilities/services/format-date.service';
import { formatDescription, truncateText } from '@/utilities/services/text-format.service';
import { faCheck, faEdit, faEllipsisV, faEye, faRedo, faTrash } from '@fortawesome/free-solid-svg-icons';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { RowName } from '../styles/todo-list-table.styled';

// Column configurations for the TodoListTable
export const getTodoTableColumns = (editRowId, onViewDetails, onSelectRowToUpdate, onComplete, onDelete, onUpdateTask, onCloseEditModal, EditTaskModal) => [
  {
    align: 'center',
    title: 'No.',
    key: 'index',
    render: (_, __, index) => index + 1,
  },
  {
    title: 'Title',
    dataIndex: 'title',
    key: 'title',
    render: (title, record) => (
      <RowName>
        {editRowId === record.id && (
          <EditTaskModal
            isOpen={editRowId}
            selectedRow={record}
            onUpdateTask={onUpdateTask}
            onCloseEditModal={onCloseEditModal}
          />
        )}
        <span
          style={{
            color: record.status === STATUS_VALUES.OVERDUE ? COLORS.RED : 'inherit',
            fontWeight: record.status === STATUS_VALUES.OVERDUE ? 'bold' : 'normal',
            textDecoration: record.status === STATUS_VALUES.COMPLETED ? 'line-through' : 'none',
          }}
        >
          {truncateText(title)}
        </span>
      </RowName>
    ),
  },
  {
    title: 'Description',
    dataIndex: 'description',
    key: 'description',
    render: description => <span>{formatDescription(description)}</span>,
  },
  {
    title: 'Due Date',
    dataIndex: 'dueDate',
    key: 'dueDate',
    render: dueDate => <span>{formatDate(dueDate)}</span>,
  },
  {
    align: 'center',
    title: 'Priority',
    dataIndex: 'priority',
    key: 'priority',
    render: priority => <span>{PRIORITY_LEVELS[priority] || '-'}</span>,
  },
  {
    align: 'center',
    title: 'Status',
    dataIndex: 'status',
    key: 'status',
    render: (status, record) => {
      let color =
        record.status === STATUS_VALUES.OVERDUE
          ? COLORS.RED
          : record.status === STATUS_VALUES.COMPLETED
            ? COLORS.GREEN
            : 'inherit';

      let fontWeight =
        record.status === STATUS_VALUES.COMPLETED || record.status === STATUS_VALUES.OVERDUE ? 'bold' : 'normal';

      return <span style={{ color, fontWeight }}>{STATUS_TYPES[status] || STATUS_TYPES.PENDING}</span>;
    },
  },
  {
    title: 'Created At',
    dataIndex: 'createdAt',
    key: 'createdAt',
    render: createdAt => <span>{formatDate(createdAt)}</span>,
  },
  {
    title: 'Updated At',
    dataIndex: 'updatedAt',
    key: 'updatedAt',
    render: updatedAt => <span>{formatDate(updatedAt)}</span>,
  },
  {
    align: 'center',
    title: 'Actions',
    key: 'actions',
    render: (_, record) => {
      const menuItems = [
        {
          key: 'view',
          icon: <FontAwesomeIcon icon={faEye} />,
          label: 'View details',
          onClick: () => onViewDetails(record),
        },
        {
          type: 'divider',
        },
        {
          key: 'edit',
          icon: <FontAwesomeIcon icon={faEdit} />,
          label: 'Edit',
          onClick: () => onSelectRowToUpdate(record.id),
        },
        {
          type: 'divider',
        },
        {
          key: 'complete',
          icon: <FontAwesomeIcon icon={record.status === STATUS_VALUES.COMPLETED ? faRedo : faCheck} />,
          label: record.status === STATUS_VALUES.COMPLETED ? 'Mark as incompleted' : 'Mark as completed',
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
    },
  },
];