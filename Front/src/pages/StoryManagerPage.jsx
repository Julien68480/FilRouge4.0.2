import { useState, useEffect } from "react";
import { storyService } from "../services/storyService";
import Card from "../components/common/Card";
import { Link } from "react-router-dom";

export default function StoryManagerPage() {
  const [stories, setStories] = useState([]);
  const [loading, setLoading] = useState(true);
  const [editingId, setEditingId] = useState(null);
  const [formData, setFormData] = useState({
    titre: "",
    description: "",
    difficultyLevel: "EASY",
  });
  const [message, setMessage] = useState("");

  useEffect(() => {
    fetchStories();
  }, []);

  const fetchStories = async () => {
    try {
      const { data } = await storyService.getAll();
      setStories(data);
    } catch (error) {
      console.error(error);
    } finally {
      setLoading(false);
    }
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      if (editingId) {
        await storyService.update(editingId, formData);
        setMessage("Histoire modifiée ! ✅");
        setEditingId(null);
      } else {
        await storyService.create(formData);
        setMessage("Histoire créée ! ✅");
      }
      setFormData({ titre: "", description: "", difficultyLevel: "EASY" });
      fetchStories(); // Refresh
      setTimeout(() => setMessage(""), 3000); // Disparaît après 3s
    } catch (error) {
      setMessage("Erreur serveur 😢");
      console.error(error);
    }
  };

  const handleEdit = (story) => {
    setEditingId(story.id);
    setFormData({
      titre: story.titre,
      description: story.description,
      difficultyLevel: story.difficultyLevel,
    });
  };

  if (loading)
    return (
      <div className="container mx-auto py-12 text-center">Chargement...</div>
    );

  return (
    <div className="container mx-auto px-4 py-12">
      <h1 className="text-4xl font-bold text-gray-900 mb-12">
        Gestion des Histoires
      </h1>

      {/* Formulaire Créer/Modifier */}
      <div className="max-w-2xl mx-auto bg-white p-8 rounded-2xl shadow-xl mb-12">
        <h2 className="text-2xl font-bold mb-6">
          {editingId ? "Modifier Histoire" : "Nouvelle Histoire"}
        </h2>
        <form onSubmit={handleSubmit} className="space-y-4">
          <input
            type="text"
            placeholder="Titre"
            value={formData.titre}
            onChange={(e) =>
              setFormData({ ...formData, titre: e.target.value })
            }
            className="w-full p-4 border border-gray-200 rounded-xl focus:ring-2 focus:ring-blue-500 outline-none"
            required
          />
          <textarea
            placeholder="Description"
            value={formData.description}
            onChange={(e) =>
              setFormData({ ...formData, description: e.target.value })
            }
            className="w-full p-4 border border-gray-200 rounded-xl focus:ring-2 focus:ring-blue-500 outline-none h-28"
            required
          />
          <select
            value={formData.difficultyLevel}
            onChange={(e) =>
              setFormData({ ...formData, difficultyLevel: e.target.value })
            }
            className="w-full p-4 border border-gray-200 rounded-xl focus:ring-2 focus:ring-blue-500 outline-none"
          >
            <option value="EASY">EASY</option>
            <option value="MEDIUM">MEDIUM</option>
            <option value="HARD">HARD</option>
          </select>
          <div className="flex gap-3">
            <button
              type="submit"
              className="flex-1 bg-blue-600 text-white py-3 rounded-xl font-semibold hover:bg-blue-700 transition"
            >
              {editingId ? "Modifier" : "Créer"}
            </button>
            {editingId && (
              <button
                type="button"
                onClick={() => setEditingId(null)}
                className="px-6 bg-gray-500 text-white py-3 rounded-xl hover:bg-gray-600 transition"
              >
                Annuler
              </button>
            )}
          </div>
        </form>
        {message && (
          <div
            className={`p-4 rounded-xl mt-4 font-semibold text-white ${
              message.includes("✅") ? "bg-green-500" : "bg-red-500"
            }`}
          >
            {message}
          </div>
        )}
      </div>

      <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-6">
        {stories.map((story) => (
          <div key={story.id} className="relative group">
            <Card story={story} />
            <Link
              to={`/stories/${story.id}`}
              className="absolute top-4 right-4 opacity-0 group-hover:opacity-100 bg-blue-600 text-white px-4 py-2 rounded-lg text-sm font-semibold hover:bg-blue-700 transition-all"
            >
              Modifier
            </Link>
          </div>
        ))}
      </div>
    </div>
  );
}
