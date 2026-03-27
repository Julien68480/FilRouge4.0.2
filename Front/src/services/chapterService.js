import { api } from './api';

export const chapterService = {
  // Version axios (préférée si api.js existe)
  getChaptersByStory: (storyId) => api.get(`/storys/${storyId}/chapters`),
  createChapter: (storyId, chapterData) => api.post(`/storys/${storyId}/chapters`, chapterData),
  
  // Backend findById(id) et update(id)
  getChapter: (chapterId) => api.get(`/chapters/${chapterId}`),
  updateChapter: (chapterId, chapterData) => api.put(`/chapters/${chapterId}`, chapterData),
  deleteChapter: (chapterId) => api.delete(`/chapters/${chapterId}`)
};
