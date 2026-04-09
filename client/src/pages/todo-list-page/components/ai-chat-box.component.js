import { Image } from '@/antd-components/image.component';
import { API_ENDPOINTS, ASSISTANT_MESSAGES, INTENT_TYPES } from '@/utilities/constants';
import { todoApi } from '@/utilities/services/api.service';
import { handleUnauthorized } from '@/utilities/services/auth-utils.service';
import { SendOutlined } from '@ant-design/icons';
import { map } from 'lodash-es';
import { useEffect, useRef, useState } from 'react';

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

export const AIChatBox = ({ onTaskCreated }) => {
  const [isChatOpen, setIsChatOpen] = useState(false);
  const [promptInput, setPromptInput] = useState('');
  const [chatMessages, setChatMessages] = useState([
    {
      id: 'ai-welcome',
      from: 'ai',
      text: ASSISTANT_MESSAGES.WELCOME,
    },
  ]);
  const [isChatSubmitting, setIsChatSubmitting] = useState(false);
  const promptInputRef = useRef(null);
  const chatBodyRef = useRef(null);

  const handleSendPrompt = async () => {
    const promptText = promptInput.trim();

    if (!promptText)
      return setChatMessages(prev => [...prev, { id: `${Date.now()}-ai`, from: 'ai', text: ASSISTANT_MESSAGES.EMPTY }]);

    setIsChatSubmitting(true);
    setPromptInput('');
    setChatMessages(prev => [...prev, { id: `${Date.now()}-user`, from: 'user', text: promptText }]);

    try {
      const parseResponse = await todoApi.post(API_ENDPOINTS.NLP_PARSE, { prompt: promptText });
      const parsedPrompt = parseResponse.data;
      const assistantMessage = parsedPrompt.assistantMessage?.trim();

      if (!parsedPrompt || !parsedPrompt.task)
        return setChatMessages(prev => [
          ...prev,
          {
            id: `${Date.now()}-ai`,
            from: 'ai',
            text: assistantMessage,
          },
        ]);

      if (parsedPrompt.intent !== 'CREATE')
        return setChatMessages(prev => [
          ...prev,
          {
            id: `${Date.now()}-ai`,
            from: 'ai',
            text: ASSISTANT_MESSAGES.INTENT_NOT_SUPPORTED.replace('%s', INTENT_TYPES[parsedPrompt.intent]),
          },
        ]);

      const generatedTask = parsedPrompt.task;
      const response = await todoApi.post(API_ENDPOINTS.TODOS, generatedTask);
      const createdTask = response.data;

      if (assistantMessage) {
        setChatMessages(prev => [...prev, { id: `${Date.now()}-ai`, from: 'ai', text: assistantMessage }]);
      } else {
        const dueDateText = createdTask.dueDate
          ? new Date(createdTask.dueDate).toLocaleString('vi-VN', {
              hour: '2-digit',
              minute: '2-digit',
              day: '2-digit',
              month: '2-digit',
              year: 'numeric',
            })
          : 'no due date';

        setChatMessages(prev => [
          ...prev,
          {
            id: `${Date.now()}-ai`,
            from: 'ai',
            text: ASSISTANT_MESSAGES.TASK_CREATED.replace('%s', createdTask.title).replace('%s', dueDateText),
          },
        ]);
      }

      onTaskCreated(createdTask);
    } catch (error) {
      if (error.response?.status === 401) return handleUnauthorized();

      const errorMessage =
        error.message === 'Failed to parse prompt.'
          ? 'I could not understand your prompt. Please try again with a different instruction 🥹'
          : 'An error occurred while creating the task. Please try again later 🥹';

      setChatMessages(prev => [...prev, { id: `${Date.now()}-ai`, from: 'ai', text: errorMessage }]);
    } finally {
      setIsChatSubmitting(false);
    }
  };

  useEffect(() => {
    if (isChatOpen) setTimeout(() => promptInputRef.current?.focus(), 0);
  }, [isChatOpen]);

  useEffect(() => {
    if (!chatBodyRef.current) return;

    chatBodyRef.current.scrollTop = chatBodyRef.current.scrollHeight;
  }, [chatMessages, isChatOpen]);

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

        <ChatBody ref={chatBodyRef}>
          {map(chatMessages, msg => (
            <ChatMessage key={msg.id} from={msg.from}>
              {msg.text}
            </ChatMessage>
          ))}
        </ChatBody>

        <ChatFooter>
          <PromptTextArea
            ref={promptInputRef}
            placeholder="Enter your Vietnamese prompt..."
            value={promptInput}
            onChange={e => setPromptInput(e.target.value)}
            onKeyDown={e => {
              if (e.key === 'Enter' && !e.shiftKey) {
                e.preventDefault();
                handleSendPrompt();
              }
            }}
            disabled={isChatSubmitting}
            allowClear
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
