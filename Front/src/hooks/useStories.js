import { useState, useEffect } from 'react';
import { storiesService } from '../services/storiesService';

export function useStories() {
  const [stories, setStories] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

  useEffect(() => {
    const fetchStories = async () => {
      try {
        setLoading(true);
        const data = await storiesService.getAllStories();
        setStories(data);
      } catch (err) {
        setError('Erreur chargement histoires');
        console.error(err);
      } finally {
        setLoading(false);
      }
    };

    fetchStories();
  }, []);

  return { stories, loading, error };
}
