import { Button } from '@/antd-components/button.component';
import { FloatButton } from '@/antd-components/float-button.component';
import { Input } from '@/antd-components/input.component';
import { Modal } from '@/antd-components/modal.component';
import { COLORS } from '@/utilities/constants';
import styled from 'styled-components';

export const ChatHeader = styled.div`
  height: 3rem;
  width: 25rem;
  display: flex;
  align-items: center;
  gap: 0.5rem;
  background: var(--item-background-color);
  margin-top: -1.3rem;
  margin-left: -1.5rem;
  padding: 0.6rem;
  border-bottom: 0.0625rem solid var(--divider-color);
  z-index: 1;
`;

export const ChatTitle = styled.div`
  font-size: 0.9rem;
  font-weight: bold;
  color: var(--primary-text-color);
`;

export const ChatBody = styled.div`
  padding: 1.5rem 1rem;
  overflow-y: auto;
  flex: 1;
  background: var(--main-background-color);
  display: flex;
  flex-direction: column;
  gap: 0.15rem;
  width: 25rem;
  margin-top: -0.5rem;
  margin-left: -1.5rem;
`;

export const ChatMessage = styled.div`
  width: 20rem;
  line-height: 1.4;
  word-break: break-word;
  white-space: pre-wrap;
  border-radius: 0.5rem;
  padding: 0.65rem 0.7rem;
  font-size: 0.88rem;
  background: ${({ from }) =>
    from === 'ai' ? `${COLORS.LIGHT_GRAY}` : from === 'user' ? '#096dd9' : `${COLORS.LIGHT_GRAY}`};
  color: ${({ from }) => (from === 'user' ? `${COLORS.WHITE}` : `${COLORS.DARK_GRAY}`)};
  align-self: ${({ from }) => (from === 'user' ? 'flex-end' : 'flex-start')};
`;

export const ChatFooter = styled.div`
  border-top: 0.0625rem solid var(--divider-color);
  display: flex;
  gap: 0.5rem;
  background-color: var(--item-background-color);
  height: 3rem;
  width: 25.2rem;
  align-items: center;
  margin-bottom: -4rem;
  margin-left: -1.5rem;
  padding: 0.5rem;
`;

export const ChatModal = styled(Modal)`
  left: 30.5rem;
  top: 14.5rem;

  .ant-modal-content {
    border: 0.0625rem solid var(--divider-color);
    border-radius: 0.75rem;
    height: 25rem;
    overflow: hidden;
    background-color: ${COLORS.GHOST_WHITE};
  }

  .ant-modal-body {
    height: calc(100% - 2.5rem);
    display: flex;
    flex-direction: column;
  }
`;

export const AIFloatButton = styled(FloatButton)`
  width: 3rem;
  height: 3rem;
  text-align: center;
  border: none;

  .ant-float-btn-body {
    background-color: var(--primary-blue-color) !important;

    &:hover {
      background-color: var(--hover-color) !important;
    }
  }
`;

export const SendPromptButton = styled(Button)`
  width: 5rem !important;
  text-align: center;
  background-color: var(--primary-blue-color) !important;
  border-color: transparent;
  color: var(--primary-text-color);

  &:hover {
    background-color: var(--hover-color) !important;
  }
`;

export const PromptTextArea = styled(Input.TextArea)`
  background-color: var(--input-background-color);
  border-color: var(--divider-color);

  &:hover {
    background-color: var(--input-background-color);
    border-color: var(--divider-color);
  }

  &:focus-within {
    border-color: var(--divider-color);
    background-color: var(--input-background-color);
    color: var(--primary-text-color);
  }

  .ant-input {
    color: var(--primary-text-color);
  }

  &::placeholder {
    color: var(--input-placeholder-color) !important;
  }
`;
