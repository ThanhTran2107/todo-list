import { Image } from '@/antd-components/image.component';
import { message } from '@/antd-components/message.component';
import { API_ENDPOINTS } from '@/utilities/constants';
import { todoApi } from '@/utilities/services/api.service';
import { handleUnauthorized } from '@/utilities/services/auth-utils.service';
import { SendOutlined } from '@ant-design/icons';
import { isEmpty, map, trim } from 'lodash-es';
import { useState } from 'react';

import {
  AIFloatButton,
  ChatBody,
  ChatFooter,
  ChatHeader,
  ChatMessage,
  ChatModal,
  ChatTitle,
  PromptTextArea,
  SendPromptButton,
} from '../styles/ai-chat-box.styled';

const parsePromptToTodo = prompt => {
  const normalized = trim(prompt || '');

  if (!normalized) return null;

  const firstLine = normalized.split(/\n/)[0].trim();
  const title = firstLine || 'AI generated task';
  const now = new Date();

  return {
    title: title.slice(0, 100),
    description: normalized,
    dueDate: new Date(Date.now() + 24 * 60 * 60 * 1000).toISOString(),
    priority: 'MEDIUM',
    status: 'PENDING',
    completed: false,
    createdAt: now.toISOString(),
    updatedAt: null,
  };
};

export const AIChatBox = ({ onTaskCreated }) => {
  const [isChatOpen, setIsChatOpen] = useState(false);
  const [promptInput, setPromptInput] = useState('');
  const [chatMessages, setChatMessages] = useState([]);
  const [isChatSubmitting, setIsChatSubmitting] = useState(false);

  const handleSendPrompt = async () => {
    const promptText = promptInput.trim();

    if (!promptText) return message.error('Please enter a prompt before sending.', 1);

    setIsChatSubmitting(true);
    setChatMessages(prev => [...prev, { id: `${Date.now()}-user`, from: 'user', text: promptText }]);

    const generatedTask = parsePromptToTodo(promptText);

    if (!generatedTask) {
      setIsChatSubmitting(false);

      return message.error('Failed to parse prompt to task.', 1);
    }

    try {
      const response = await todoApi.post(API_ENDPOINTS.TODOS, generatedTask);
      const createdTask = response.data;

      setChatMessages(prev => [
        ...prev,
        { id: `${Date.now()}-ai`, from: 'ai', text: 'Task generated and saved successfully.' },
      ]);
      setPromptInput('');
      onTaskCreated?.(createdTask);
      message.success('AI task created successfully!', 1);
    } catch (error) {
      if (error.response?.status === 401) return handleUnauthorized();

      message.error('Failed to create AI task.', 1);
      setChatMessages(prev => [
        ...prev,
        { id: `${Date.now()}-ai`, from: 'ai', text: 'Failed to save the task. Please try again.' },
      ]);
    } finally {
      setIsChatSubmitting(false);
    }
  };

  return (
    <>
      <AIFloatButton
        icon={<Image preview={false} src="/ai-technology.png" alt="Todo Assistant" width={20} height={20} />}
        type="primary"
        onClick={() => setIsChatOpen(prev => !prev)}
      />

      <ChatModal open={isChatOpen} closeIcon={false} footer={false} onCancel={() => setIsChatOpen(false)} width={420}>
        <ChatHeader>
          <Image src="/ai-technology.png" alt="Todo Assistant" width={32} height={32} preview={false} />
          <ChatTitle>Todo Assistant</ChatTitle>
        </ChatHeader>

        <ChatBody>
          {isEmpty(chatMessages) ? (
            <ChatMessage from="ai">
              Hello! I'm your Todo Assistant. What task schedule would you like me to create ?
            </ChatMessage>
          ) : (
            map(chatMessages, msg => (
              <ChatMessage key={msg.id} from={msg.from}>
                {msg.text}
              </ChatMessage>
            ))
          )}
        </ChatBody>

        <ChatFooter>
          <PromptTextArea
            autoSize={{ minRows: 1, maxRows: 1 }}
            placeholder="Enter your Vietnamese prompt..."
            value={promptInput}
            onChange={e => setPromptInput(e.target.value)}
            disabled={isChatSubmitting}
          />

          <SendPromptButton
            icon={<SendOutlined />}
            type="primary"
            onClick={handleSendPrompt}
            loading={isChatSubmitting}
          />
        </ChatFooter>
      </ChatModal>
    </>
  );
};
