import { api } from './api';

export const chaptersService = {
  getChaptersByStory: (storyId) => api.get(`/storys/${storyId}/chapters`),
  createChapter: (storyId, chapterData) => api.post(`/storys/${storyId}/chapters`, chapterData),
  getChapter: (storyId, chapterId) => api.get(`/storys/${storyId}/chapters/${chapterId}`),
  updateChapter: (storyId, chapterId, chapterData) => api.put(`/storys/${storyId}/chapters/${chapterId}`, chapterData),
  deleteChapter: (storyId, chapterId) => api.delete(`/storys/${storyId}/chapters/${chapterId}`)
};
