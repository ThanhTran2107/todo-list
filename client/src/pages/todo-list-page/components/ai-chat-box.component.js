import { Image } from '@/antd-components/image.component';
import { useAIChat } from '@/utilities/hooks/use-ai-chat.hook.js';
import { SendOutlined } from '@ant-design/icons';
import { map } from 'lodash-es';

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
  const {
    isChatOpen,
    promptInput,
    chatMessages,
    isChatSubmitting,
    promptInputRef,
    chatBodyRef,
    handleToggleChat,
    handleSendPrompt,
    handleInputChange,
  } = useAIChat({ onTaskCreated });

  const handleKeyDown = e => {
    if (e.key === 'Enter' && !e.shiftKey) {
      e.preventDefault();
      handleSendPrompt();
    }
  };

  return (
    <>
      <AIFloatButton
        icon={<Image preview={false} src="/ai-technology.png" alt="Todo Assistant" width={20} height={20} />}
        type="primary"
        onClick={handleToggleChat}
      />

      <ChatModal open={isChatOpen} closeIcon={false} footer={false} onCancel={handleToggleChat} width={420}>
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
            onChange={handleInputChange}
            onKeyDown={handleKeyDown}
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
