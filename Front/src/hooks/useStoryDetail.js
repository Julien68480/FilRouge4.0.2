import { useState, useEffect, useCallback } from 'react';
import { storyService } from '../services/storyService';
import { chapterService } from '../services/chapterService';

export function useStoryDetail(storyId) {
  const [story, setStory] = useState(null);
  const [chapters, setChapters] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

  const refetch = useCallback(async () => {
    try {
      setLoading(true);
      const [storyRes, chaptersRes] = await Promise.all([
        storyService.getById(storyId),
        chapterService.getChaptersByStory(storyId)
      ]);
      
      setStory(storyRes.data);
      setChapters(chaptersRes.data || []);
      setError(null);
    } catch (err) {
      setError('Erreur rechargement');
      console.error('Refetch error:', err);
    } finally {
      setLoading(false);
    }
  }, [storyId]);

  useEffect(() => {
    const fetchData = async () => {
      try {
        setLoading(true);
        const storyRes = await storyService.getById(storyId);
        setStory(storyRes.data);
        
        const chaptersRes = await chapterService.getChaptersByStory(storyId);
        setChapters(chaptersRes.data || []);
        setError(null);
      } catch (err) {
        setError('Erreur chargement');
      } finally {
        setLoading(false);
      }
    };

    if (storyId) fetchData();
  }, [storyId]);

  return { 
    story, 
    chapters, 
    loading, 
    error, 
    refetch  
  };
}
