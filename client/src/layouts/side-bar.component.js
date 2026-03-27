import { Menu } from '@/antd-components/menu.component';
import { Space } from '@/antd-components/space.component';
import { AddTodoModal } from '@/pages/todo-list-page/components/add-todo-modal.component';
import {
  faArrowTrendUp,
  faCheckCircle,
  faExclamationTriangle,
  faHourglassHalf,
  faList,
  faPlus,
} from '@fortawesome/free-solid-svg-icons';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { useState } from 'react';

import {
  CreateTaskButton,
  HeaderSubtitle,
  HeaderTitle,
  IconCreateTask,
  SideBarContainer,
  SideBarHeader,
  SideBarMenu,
} from './styles/side-bar.styled';

export const SideBar = ({ onAddNewTodo, onFilterStatus }) => {
  const [isModalOpen, setIsModalOpen] = useState(false);
  const [selectedMenu, setSelectedMenu] = useState('my-tasks');

  // Function to open add todo modal
  const handleOpenAddModal = () => setIsModalOpen(true);

  // Function to close add todo modal
  const handleCloseAddModal = () => setIsModalOpen(false);

  return (
    <SideBarContainer>
      <SideBarHeader>
        <Space direction="vertical" style={{ marginLeft: '1.5em' }} size={0}>
          <HeaderTitle>Task Manager</HeaderTitle>
          <HeaderSubtitle>Productivity Workspace</HeaderSubtitle>
        </Space>

        <SideBarMenu
          mode="inline"
          selectedKeys={[selectedMenu]}
          onClick={item => {
            setSelectedMenu(item.key);
            if (onFilterStatus) onFilterStatus(item.key);
          }}
        >
          <Menu.Item key="my-tasks" icon={<FontAwesomeIcon icon={faList} />}>
            My tasks
          </Menu.Item>
          <Menu.Item key="pending" icon={<FontAwesomeIcon icon={faHourglassHalf} />}>
            Pending
          </Menu.Item>
          <Menu.Item key="in-progress" icon={<FontAwesomeIcon icon={faArrowTrendUp} />}>
            In progress
          </Menu.Item>
          <Menu.Item key="completed" icon={<FontAwesomeIcon icon={faCheckCircle} />}>
            Completed
          </Menu.Item>
          <Menu.Item key="overdue" icon={<FontAwesomeIcon icon={faExclamationTriangle} />}>
            Overdue
          </Menu.Item>
        </SideBarMenu>
      </SideBarHeader>

      <CreateTaskButton type="text" onClick={() => handleOpenAddModal()}>
        <Space align="center">
          <IconCreateTask icon={faPlus} />
          Create a new task
        </Space>
      </CreateTaskButton>

      <AddTodoModal isOpen={isModalOpen} onAddNewTodo={onAddNewTodo} onClose={handleCloseAddModal} />
    </SideBarContainer>
  );
};
