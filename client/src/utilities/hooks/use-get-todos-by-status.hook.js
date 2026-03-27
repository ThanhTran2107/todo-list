import { API_ENDPOINTS, STATUS_VALUES } from '@/utilities/constants';
import { todoApi } from '@/utilities/services/api.service';
import { handleUnauthorized } from '@/utilities/services/auth-utils.service';
import { message } from 'antd';

export const useGetTodosByStatus = () => {
  const handleFilterStatus = async status => {
    try {
      const params = {};

      if (status && status !== 'my-tasks') {
        let statusParam;
        switch (status) {
          case 'pending':
            statusParam = STATUS_VALUES.PENDING;
            break;
          case 'in-progress':
            statusParam = STATUS_VALUES.IN_PROGRESS;
            break;
          case 'completed':
            statusParam = STATUS_VALUES.COMPLETED;
            break;
          case 'overdue':
            statusParam = STATUS_VALUES.OVERDUE;
            break;
          default:
            statusParam = null;
        }

        if (statusParam) params.status = statusParam;
      }

      const response = await todoApi.get(API_ENDPOINTS.TODOS, { params });
      const fetched = response.data || [];

      return fetched;
    } catch (e) {
      if (e.response?.status === 401) return handleUnauthorized();

      message.error('Failed to filter tasks by status', 1);
      return [];
    }
  };

  return {
    handleFilterStatus,
  };
};
