import { useStories } from "../hooks/useStories";
import Card from "../components/common/Card";

export default function HomePage() {
  const { stories, loading, error } = useStories();

  if (loading) return (
    <div className="container mx-auto px-4 py-12 text-center">
      <div>Chargement des histoires...</div>
    </div>
  );

  if (error) return (
    <div className="container mx-auto px-4 py-12 text-center text-red-600">
      {error}
    </div>
  );

  return (
    <div className="container mx-auto px-4 py-12">
      <div className="text-center mb-12">
        <h1 className="text-5xl font-bold text-gray-900 mb-4">Histoire En Vrac</h1>
        <p className="text-xl text-gray-600 max-w-2xl mx-auto">
          {stories.length} histoire(s) trouvée(s)
        </p>
      </div>
      
      <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-6">
        {stories.map((story) => (
          <Card key={story.id} story={story} />
        ))}
      </div>
    </div>
  );
}
