import { Form } from '@/components/antd/form.component';
import { Modal } from '@/components/antd/modal.component';
import { TextField } from '@/components/antd/text-field.component';
import { trim } from 'lodash-es';
import { useState } from 'react';

// EditTaskNameModal component for updating task names
export const EditTaskNameModal = ({ isOpen, selectedRow, onUpdateTaskName, onCloseEditModal }) => {
  const [hasChanged, setHasChanged] = useState(false);
  const [form] = Form.useForm();

  // Function to handle the update button click and validate form
  const handleUpdateButtonClick = selectedRow => {
    form
      .validateFields()
      .then(formValue => {
        const { titleField } = formValue;
        const updatedTaskName = { ...selectedRow, title: trim(titleField) };

        onUpdateTaskName(updatedTaskName);
        onCloseEditModal();
      })
      .catch(e => {
        if (e.errorFields) return;

        console.log(e);
      });
  };

  return (
    <Modal
      title="Update Task"
      open={isOpen}
      closable={!isOpen}
      maskClosable={!isOpen}
      okText="Update"
      cancelText="Cancel"
      onOk={() => handleUpdateButtonClick(selectedRow)}
      onCancel={onCloseEditModal}
      okButtonProps={{ disabled: !hasChanged }}
    >
      <Form
        form={form}
        name="editTaskForm"
        layout="vertical"
        onFinish={() => handleUpdateButtonClick(selectedRow)}
        style={{ marginTop: '1.5rem' }}
      >
        <Form.Item
          label="Task Title"
          name="titleField"
          rules={[
            {
              required: true,
              message: 'Please enter the task title to update!',
            },
            {
              type: 'string',
              whitespace: true,
            },
            {
              validator: (_, value) =>
                value && trim(value) === selectedRow.title
                  ? Promise.reject(new Error('The new task title must be different from the old one!'))
                  : Promise.resolve(),
            },
          ]}
        >
          <TextField
            type="text"
            placeholder="Enter a task title..."
            defaultValue={selectedRow.title}
            onChange={() => setHasChanged(true)}
          />
        </Form.Item>
      </Form>
    </Modal>
  );
};
