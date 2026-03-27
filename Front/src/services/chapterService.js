import { api } from "./api";

export const chapterService = {

  getChaptersByStory: (storyId) => api.get(`/stories/${storyId}/chapters`),

  createChapter: (storyId, chapterData) =>
    api.post(`/stories/${storyId}/chapters`, chapterData),

  getChapter: (storyId, chapterId) =>
    api.get(`/stories/${storyId}/chapters/${chapterId}`),

  updateChapter: (storyId, chapterId, chapterData) =>
    api.put(`/stories/${storyId}/chapters/${chapterId}`, chapterData),

  deleteChapter: (storyId, chapterId) =>
    api.delete(`/stories/${storyId}/chapters/${chapterId}`),
};
