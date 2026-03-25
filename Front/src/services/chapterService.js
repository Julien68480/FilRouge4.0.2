const API_URL = import.meta.env.VITE_API_URL || 'http://localhost:8080/api';

export const chapterService = {
  getByStoryId: (storyId) => 
    fetch(`${API_URL}/storys/${storyId}/chapters`)
      .then(res => {
        if (!res.ok) throw new Error(`HTTP ${res.status}`);
        return res.json();
      }),

  create: async (storyId, chapterData) => {
    const response = await fetch(`${API_URL}/storys/${storyId}/chapters`, {
      method: 'POST',
      headers: { 
        'Content-Type': 'application/json' 
      },
      body: JSON.stringify(chapterData)
    });
    if (!response.ok) throw new Error(`HTTP ${response.status}`);
    return response.json();
  }
};
