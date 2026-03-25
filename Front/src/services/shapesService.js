import { api } from './api';

export const shapesService = {
  createShape: (chapterId, shapeData) => api.post(`/chapters/${chapterId}/shapes`, shapeData),
  getShapesByChapter: (chapterId) => api.get(`/chapters/${chapterId}/shapes`),
  deleteShape: (id) => api.delete(`/shapes/${id}`)
};
