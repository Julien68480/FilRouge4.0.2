import { useState, useEffect, useCallback } from 'react';
import { storyService } from '../services/storyService';
import { chaptersService } from '../services/chaptersService';

export function useStoryDetail(storyId) {
  const [story, setStory] = useState(null);
  const [chapters, setChapters] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

  // 🆕 FONCTION REFETCH (pour recharger les données)
  const refetch = useCallback(async () => {
    try {
      setLoading(true);
      // Recharger histoire ET chapitres
      const [storyRes, chaptersRes] = await Promise.all([
        storyService.getById(storyId),
        chaptersService.getChaptersByStory(storyId)
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

  // useEffect initial (inchangé)
  useEffect(() => {
    const fetchData = async () => {
      try {
        setLoading(true);
        const storyRes = await storyService.getById(storyId);
        setStory(storyRes.data);
        
        const chaptersRes = await chaptersService.getChaptersByStory(storyId);
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
    refetch  // ← Ajouté !
  };
}
