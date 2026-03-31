import { api } from './api';

export const shapesService = {
  createShape: (chapterId, shapeData) => api.post(`/chapters/${chapterId}/shapes`, shapeData),
  getShapesByChapter: (chapterId) => api.get(`/chapters/${chapterId}/shapes`),
  deleteShape: (id) => api.delete(`/shapes/${id}`),
  createShapes: (chapterId, shapes) => api.post(`/chapters/${chapterId}/shapes/batch`, shapes),
  updateShapes: (chapterId, shapes) => api.put(`/chapters/${chapterId}/shapes/batch`, shapes),
};