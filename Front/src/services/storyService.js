import { api } from './api';

export const storyService = {
  getAll: () => api.get('/storys'),
  getById: (id) => api.get(`/storys/${id}`),
  create: (storyData) => api.post('/storys', storyData),
  update: (id, storyData) => api.put(`/storys/${id}`, storyData),
  delete: (id) => api.delete(`/storys/${id}`)
};
