import { Divider } from '@/components/antd/divider.component';

import { COLORS } from '../constant';

// Text formatting utilities
export const truncateText = (text, maxLength = 50) => {
  if (!text) return <Divider style={{ borderColor: COLORS.BLACK }} />;

  if (text.length <= maxLength) return text;

  return text.substring(0, maxLength) + '...';
};

// Format description for table display
export const formatDescription = (description, maxLength = 50) => truncateText(description, maxLength);
