import { Divider } from '@/components/antd/divider.component';

import { COLORS } from '../constant';

// Function to format date strings
export const formatDate = dateString => {
  if (!dateString) return <Divider style={{ borderColor: COLORS.BLACK }} />;

  const date = new Date(dateString);

  return date.toLocaleDateString('en-US', {
    year: 'numeric',
    month: 'short',
    day: 'numeric',
    hour: '2-digit',
    minute: '2-digit',
  });
};
