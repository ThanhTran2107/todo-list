import { API_ENDPOINTS, ASSISTANT_MESSAGES, INTENT_TYPES } from '@/utilities/constants';
import { todoApi } from '@/utilities/services/api.service';
import { handleUnauthorized } from '@/utilities/services/auth-utils.service';
import { filter } from 'lodash-es';
import { useCallback, useEffect, useRef, useState } from 'react';

export const useAIChat = ({ onTaskCreated }) => {
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

  const formatDueDate = dueDate => {
    if (!dueDate) return 'no due date';

    return new Date(dueDate).toLocaleString('vi-VN', {
      hour: '2-digit',
      minute: '2-digit',
      day: '2-digit',
      month: '2-digit',
      year: 'numeric',
    });
  };

  const addMessage = useCallback(message => {
    setChatMessages(prev => [...prev, message]);
  }, []);

  const removeLoadingMessage = () => setChatMessages(prev => filter(prev, msg => msg.id !== 'ai-loading-temp'));

  const handleToggleChat = () => setIsChatOpen(prev => !prev);

  const handleInputChange = e => setPromptInput(e.target.value);

  const handleSendPrompt = async () => {
    const promptText = promptInput.trim();
    if (!promptText) return addMessage({ id: `${Date.now()}-ai`, from: 'ai', text: ASSISTANT_MESSAGES.EMPTY });

    setIsChatSubmitting(true);
    setPromptInput('');
    addMessage({ id: `${Date.now()}-user`, from: 'user', text: promptText });

    const slowLoadingTimer = setTimeout(() => {
      addMessage({ id: 'ai-loading-temp', from: 'ai', text: ASSISTANT_MESSAGES.LOADING });
    }, 20000);

    try {
      const parseResponse = await todoApi.post(API_ENDPOINTS.NLP_PARSE, { prompt: promptText });
      clearTimeout(slowLoadingTimer);

      const parsedPrompt = parseResponse.data;
      const assistantMessage = parsedPrompt.assistantMessage?.trim();

      if (!parsedPrompt || !parsedPrompt.task)
        return addMessage({ id: `${Date.now()}-ai`, from: 'ai', text: assistantMessage });

      if (parsedPrompt.intent !== 'CREATE')
        return addMessage({
          id: `${Date.now()}-ai`,
          from: 'ai',
          text: ASSISTANT_MESSAGES.INTENT_NOT_SUPPORTED.replace('%s', INTENT_TYPES[parsedPrompt.intent]),
        });

      const generatedTask = parsedPrompt.task;
      const response = await todoApi.post(API_ENDPOINTS.TODOS, generatedTask);
      const createdTask = response.data;

      if (assistantMessage) {
        addMessage({ id: `${Date.now()}-ai`, from: 'ai', text: assistantMessage });
      } else {
        const dueDateText = formatDueDate(createdTask.dueDate);

        addMessage({
          id: `${Date.now()}-ai`,
          from: 'ai',
          text: ASSISTANT_MESSAGES.TASK_CREATED.replace('%s', createdTask.title).replace('%s', dueDateText),
        });
      }

      onTaskCreated(createdTask);
    } catch (error) {
      if (error.response?.status === 401) return handleUnauthorized();

      const errorMessage =
        error.message === 'Failed to parse prompt.'
          ? ASSISTANT_MESSAGES.PARSE_FAILED
          : ASSISTANT_MESSAGES.TASK_CREATION_FAILED;

      addMessage({ id: `${Date.now()}-ai`, from: 'ai', text: errorMessage });
    } finally {
      clearTimeout(slowLoadingTimer);
      removeLoadingMessage();
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

  return {
    isChatOpen,
    promptInput,
    chatMessages,
    isChatSubmitting,
    promptInputRef,
    chatBodyRef,
    handleToggleChat,
    handleSendPrompt,
    handleInputChange,
  };
};
