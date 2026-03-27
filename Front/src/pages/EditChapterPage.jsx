import { useParams, useNavigate } from "react-router-dom";
import { useState, useEffect } from "react";
import { chapterService } from "../services/chapterService";

export default function EditChapterPage() {
  const { storyId, chapterId } = useParams();
  const navigate = useNavigate();
  const [form, setForm] = useState({
    titre: "",
    textNarratif: "",
    instructions: "",
  });
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    console.log("storyId:", storyId);
    console.log("chapterId:", chapterId);

    if (!storyId || !chapterId) return;

    chapterService
      .getChapter(storyId, chapterId)
      .then((res) => {
        setForm(res.data);
        setLoading(false);
      })
      .catch(() => {
        setLoading(false);
        navigate(-1);
      });
  }, [storyId, chapterId]);

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      await chapterService.updateChapter(storyId, chapterId, form);
      navigate(-1, { state: { updatedChapter: form } });
    } catch (error) {
      console.error(error);
    }
  };

  if (loading)
    return <div className="p-8 text-center">Chargement chapitre...</div>;

  return (
    <div className="p-8 bg-white rounded-2xl shadow-xl max-w-2xl mx-auto">
      <h1 className="text-3xl font-bold mb-6">✏️ Modifier Chapitre</h1>
      <form onSubmit={handleSubmit} className="space-y-4">
        <input
          value={form.titre}
          onChange={(e) => setForm({ ...form, titre: e.target.value })}
          className="w-full p-4 border rounded-xl focus:ring-2 focus:ring-blue-500"
          placeholder="Titre du chapitre"
          required
        />
        <textarea
          value={form.textNarratif}
          onChange={(e) => setForm({ ...form, textNarratif: e.target.value })}
          className="w-full p-4 border rounded-xl focus:ring-2 focus:ring-blue-500 h-32"
          placeholder="Texte narratif"
          required
        />
        <textarea
          value={form.instructions || ""}
          onChange={(e) => setForm({ ...form, instructions: e.target.value })}
          className="w-full p-4 border rounded-xl focus:ring-2 focus:ring-blue-500 h-24"
          placeholder="Instructions (optionnel)"
        />
        <div className="flex gap-3 pt-4">
          <button
            type="button"
            onClick={() => navigate(-1)}
            className="flex-1 bg-gray-500 text-white py-3 rounded-xl hover:bg-gray-600 font-semibold"
          >
            Annuler
          </button>
          <button
            type="submit"
            className="flex-1 bg-green-600 text-white py-3 rounded-xl font-bold hover:bg-green-700"
          >
            Sauvegarder Chapitre
          </button>
        </div>
      </form>
    </div>
  );
}
