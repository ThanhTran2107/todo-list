import { API_ENDPOINTS, STORAGE_KEYS } from '@/utilities/constant';
import { todoApi } from '@/utilities/services/api.service';
import { getLocalStorage } from '@/utilities/services/storage.service';
import { isEmpty } from 'lodash-es';
import { useEffect, useState } from 'react';

const { TODO_LIST, ORIGINAL_LIST } = STORAGE_KEYS;

// Custom hook to fetch todos from API with fallback to localStorage
export const useGetTodos = () => {
  const [todos, setTodos] = useState([]);
  const [isLoading, setIsLoading] = useState(true);

  useEffect(() => {
    const fetchTodos = async () => {
      try {
        const response = await todoApi.get(API_ENDPOINTS.TODOS);
        const apiTodos = response.data;

        if (!isEmpty(apiTodos)) {
          setTodos(apiTodos);
        } else {
          // Fallback to localStorage
          const localTodos = getLocalStorage(ORIGINAL_LIST) || getLocalStorage(TODO_LIST) || [];

          setTodos(localTodos);
        }
      } catch (e) {
        console.error(e);

        const localTodos = getLocalStorage(ORIGINAL_LIST) || getLocalStorage(TODO_LIST) || [];
        setTodos(localTodos);
      } finally {
        setIsLoading(false);
      }
    };

    fetchTodos();
  }, []);

  return { todos, isLoading };
};
