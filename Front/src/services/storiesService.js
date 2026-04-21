import { api } from './api';

export const storiesService = {
  getAllStories: async () => {
    const response = await api.get('/stories');
    return response.data;
  }
};
