import { Checkbox } from '@/components/antd/checkbox.component';
import { DatePicker } from '@/components/antd/date-picker.component';
import { Form } from '@/components/antd/form.component';
import { Input } from '@/components/antd/input.component';
import { Modal } from '@/components/antd/modal.component';
import { TextField } from '@/components/antd/text-field.component';
import dayjs from 'dayjs';
import { trim } from 'lodash-es';
import { useState } from 'react';

// EditTaskModal component for updating all task fields
export const EditTaskModal = ({ isOpen, selectedRow, onUpdateTask, onCloseEditModal }) => {
  const [hasChanged, setHasChanged] = useState(false);
  const [form] = Form.useForm();

  // Function to handle the update button click and validate form
  const handleUpdateButtonClick = selectedRow => {
    form
      .validateFields()
      .then(formValue => {
        const { titleField, descriptionField, completedField, dueDateField, priorityField, statusField } = formValue;

        const updatedTask = {
          ...selectedRow,
          title: trim(titleField),
          description: trim(descriptionField) || null,
          completed: completedField || false,
          dueDate: dueDateField ? dueDateField.toISOString() : selectedRow.dueDate,
          priority: priorityField,
          status: statusField,
        };

        onUpdateTask(updatedTask);
        onCloseEditModal();
      })
      .catch(e => {
        if (e.errorFields) return;

        console.log(e);
      });
  };

  return (
    <Modal
      title="Edit Task"
      open={isOpen}
      closable={!isOpen}
      okText="Update"
      cancelText="Cancel"
      onOk={() => handleUpdateButtonClick(selectedRow)}
      onCancel={onCloseEditModal}
      okButtonProps={{ disabled: !hasChanged }}
      width={600}
    >
      <Form
        form={form}
        name="editTaskForm"
        layout="vertical"
        onFinish={() => handleUpdateButtonClick(selectedRow)}
        style={{ marginTop: '1.5rem' }}
        initialValues={{
          titleField: selectedRow?.title || '',
          descriptionField: selectedRow?.description || '',
          completedField: selectedRow?.completed || false,
          dueDateField: selectedRow?.dueDate ? dayjs(selectedRow.dueDate) : null,
          priorityField: selectedRow?.priority || 'MEDIUM',
          statusField: selectedRow?.status || 'PENDING',
        }}
      >
        <Form.Item
          label="Task Title"
          name="titleField"
          rules={[
            {
              required: true,
              message: 'Please enter the task title!',
            },
            {
              type: 'string',
              whitespace: true,
              message: 'Title cannot be empty!',
            },
          ]}
        >
          <TextField type="text" placeholder="Enter a task title..." onChange={() => setHasChanged(true)} />
        </Form.Item>

        <Form.Item
          label="Description"
          name="descriptionField"
          rules={[
            {
              type: 'string',
              whitespace: true,
            },
          ]}
        >
          <Input.TextArea
            showCount
            placeholder="Enter task description..."
            rows={3}
            maxLength={500}
            style={{ resize: 'vertical' }}
            onChange={() => setHasChanged(true)}
          />
        </Form.Item>

        <Form.Item label="Due Date" name="dueDateField">
          <DatePicker
            showTime
            format="YYYY-MM-DD HH:mm:ss"
            placeholder="Select due date"
            style={{ width: '100%' }}
            onChange={() => setHasChanged(true)}
          />
        </Form.Item>

        <Form.Item
          label="Priority"
          name="priorityField"
          rules={[
            {
              required: true,
              message: 'Please select priority!',
            },
          ]}
        >
          <select
            style={{ width: '100%', padding: '4px 11px', border: '1px solid #d9d9d9', borderRadius: '6px' }}
            onChange={() => setHasChanged(true)}
          >
            <option value="HIGH">High</option>
            <option value="MEDIUM">Medium</option>
            <option value="LOW">Low</option>
          </select>
        </Form.Item>

        <Form.Item
          label="Status"
          name="statusField"
          rules={[
            {
              required: true,
              message: 'Please select status!',
            },
          ]}
        >
          <select
            style={{ width: '100%', padding: '4px 11px', border: '1px solid #d9d9d9', borderRadius: '6px' }}
            onChange={() => setHasChanged(true)}
          >
            <option value="PENDING">Pending</option>
            <option value="IN_PROGRESS">In Progress</option>
            <option value="COMPLETED">Completed</option>
            <option value="OVERDUE">Overdue</option>
          </select>
        </Form.Item>

        <Form.Item name="completedField" valuePropName="checked">
          <Checkbox onChange={() => setHasChanged(true)}>Mark as completed</Checkbox>
        </Form.Item>
      </Form>
    </Modal>
  );
};
