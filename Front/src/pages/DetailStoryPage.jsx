import { useParams, Link, Outlet } from "react-router-dom";
import { useStoryDetail } from "../hooks/useStoryDetail";
import { useState, useEffect, useCallback } from "react";
import { storyService } from "../services/storyService";
import { chapterService } from "../services/chapterService";
import { useLocation } from "react-router-dom";
import { useNavigate } from "react-router-dom";

export default function DetailStoryPage() {
  const location = useLocation();
  const navigate = useNavigate();
  const { storyId } = useParams();
  const { story, chapters, loading, error, refetch } = useStoryDetail(storyId);
  const [editingStory, setEditingStory] = useState(false);
  const [addingChapter, setAddingChapter] = useState(false);
  const [showSuccessMessage, setShowSuccessMessage] = useState(false);
  const [successMessage, setSuccessMessage] = useState("");
  const [chapterForm, setChapterForm] = useState({
    titre: "",
    textNarratif: "",
    instructions: "",
  });
  const [storyForm, setStoryForm] = useState({
    titre: "",
    description: "",
    difficultyLevel: "EASY",
  });

  useEffect(() => {
    if (story) {
      setStoryForm({
        titre: story.titre,
        description: story.description,
        difficultyLevel: story.difficultyLevel,
      });
    }
  }, [story]);

  useEffect(() => {
    if (location.state?.updatedChapter) {
      setChapters((prev) =>
        prev.map((ch) =>
          ch.id === location.state.updatedChapter.id
            ? location.state.updatedChapter
            : ch,
        ),
      );
    }
  }, [location.state]);

  const showSuccess = useCallback((message) => {
    setSuccessMessage(message);
    setShowSuccessMessage(true);
    setTimeout(() => setShowSuccessMessage(false), 3000);
  }, []);

  const handleSuccessRefetch = useCallback(
    async (successMsg) => {
      showSuccess(successMsg);
      setTimeout(async () => {
        await refetch();
      }, 1500);
    },
    [showSuccess, refetch],
  );

  const handleSaveStory = async (e) => {
    e.preventDefault();
    const storyData = {
      titre: storyForm.titre,
      description: storyForm.description,
      difficultyLevel: storyForm.difficultyLevel,
    };

    try {
      await storyService.update(storyId, storyData);
      setEditingStory(false);
      await handleSuccessRefetch("Histoire modifiée avec succès !");
    } catch (error) {
      console.error("Erreur:", error);
      showSuccess("❌ Erreur modification histoire");
    }
  };

  const handleDeleteStory = async () => {
    if (!storyId) return;

    if (window.confirm(`Supprimer l'histoire "${story.titre}" ?`)) {
      try {
        await storyService.delete(storyId);
        navigate(-1);
      } catch (error) {
        console.error("Erreur suppression histoire", error);
      }
    }
  };

  const handleAddChapter = async (e) => {
    e.preventDefault();
    console.log("🔥 FORM SUBMIT", chapterForm);

    const chapterData = { ...chapterForm, order: chapters.length + 1 };
    console.log("📤 ENVOI API", chapterData);

    const optimisticChapter = { ...chapterData, id: Date.now() };
    const newChapters = [...chapters, optimisticChapter];

    chapterService
      .createChapter(storyId, chapterData)
      .then((result) => {
        handleSuccessRefetch("Chapitre ajouté !");
      })
      .catch((error) => {
        console.error("❌ Sync KO", error);
        showSuccess("❌ Erreur création chapitre");
      });

    setChapterForm({ titre: "", textNarratif: "", instructions: "" });
    setAddingChapter(false);
  };

  if (loading)
    return (
      <div className="container mx-auto py-12 text-center">
        <div className="animate-spin rounded-full h-12 w-12 border-b-2 border-blue-600 mx-auto mb-4"></div>
        Chargement...
      </div>
    );

  if (error || !story)
    return (
      <div className="container mx-auto py-12 text-center text-red-600">
        <div className="text-4xl mb-4">😢</div>
        Histoire non trouvée
      </div>
    );

  return (
    <>
      <div className="container mx-auto px-4 py-12">
        {showSuccessMessage && (
          <div className="fixed top-4 right-4 z-50 bg-green-500 text-white px-6 py-3 rounded-xl shadow-2xl animate-pulse font-semibold border-2 border-green-400">
            ✓ {successMessage}
          </div>
        )}

        <div className="mb-12">
          {editingStory ? (
            <div className="bg-white p-8 rounded-2xl shadow-xl">
              <div className="flex justify-between items-center mb-6">
                <h1 className="text-3xl font-bold text-gray-900">
                  Modifier Histoire
                </h1>
                <button
                  onClick={() => setEditingStory(false)}
                  className="text-2xl hover:text-red-500"
                >
                  ✕
                </button>
              </div>
              <form onSubmit={handleSaveStory} className="space-y-4">
                <input
                  type="text"
                  required
                  value={storyForm.titre}
                  onChange={(e) =>
                    setStoryForm({ ...storyForm, titre: e.target.value })
                  }
                  className="w-full p-4 border rounded-xl focus:ring-2 focus:ring-blue-500 focus:border-transparent"
                  placeholder="Titre de l'histoire"
                />
                <textarea
                  required
                  value={storyForm.description}
                  onChange={(e) =>
                    setStoryForm({ ...storyForm, description: e.target.value })
                  }
                  className="w-full p-4 border rounded-xl focus:ring-2 focus:ring-blue-500 focus:border-transparent h-24"
                  placeholder="Description"
                />
                <select
                  value={storyForm.difficultyLevel}
                  onChange={(e) =>
                    setStoryForm({
                      ...storyForm,
                      difficultyLevel: e.target.value,
                    })
                  }
                  className="w-full p-4 border rounded-xl focus:ring-2 focus:ring-blue-500 focus:border-transparent"
                >
                  <option value="EASY">Facile</option>
                  <option value="MEDIUM">Moyen</option>
                  <option value="HARD">Difficile</option>
                </select>
                <button
                  type="submit"
                  className="bg-green-600 text-white px-8 py-3 rounded-xl font-bold hover:bg-green-700 w-full transition-colors"
                >
                  Sauvegarder
                </button>
              </form>
            </div>
          ) : (
            <>
              <h1 className="text-4xl font-bold text-gray-900 mb-4">
                {story.titre}
              </h1>
              <div
                className={`inline-block px-4 py-2 rounded-full text-sm font-bold ${
                  story.difficultyLevel === "HARD"
                    ? "bg-red-100 text-red-800"
                    : story.difficultyLevel === "MEDIUM"
                      ? "bg-orange-100 text-orange-800"
                      : "bg-green-100 text-green-800"
                }`}
              >
                {story.difficultyLevel === "HARD"
                  ? "Difficile"
                  : story.difficultyLevel === "MEDIUM"
                    ? "Moyen"
                    : "Facile"}
              </div>
              <p className="text-xl text-gray-600 mt-4">{story.description}</p>
              <button
                onClick={() => setEditingStory(true)}
                className="mt-6 bg-blue-600 text-white px-8 py-3 rounded-xl hover:bg-blue-700 font-semibold shadow-lg transition-colors"
              >
                Modifier Histoire
              </button>
            </>
          )}
          <button
            onClick={handleDeleteStory}
            className="mt-2 ml-3 bg-red-500 text-white px-8 py-3 rounded-xl hover:bg-red-600 font-semibold shadow-lg"
          >
            🗑️ Supprimer l’histoire
          </button>
        </div>

        <div className="bg-white p-8 rounded-2xl shadow-xl">
          <div className="flex justify-between items-center mb-6">
            <h2 className="text-2xl font-bold">
              Chapitres ({chapters.length})
            </h2>
            <button
              onClick={() => setAddingChapter(true)}
              className="bg-blue-600 text-white px-6 py-2 rounded-xl hover:bg-blue-700 font-semibold transition-colors"
            >
              + Ajouter Chapitre
            </button>
          </div>
          <div className="space-y-4">
            {chapters.map((chapter) => (
              <div
                key={chapter.id}
                className="p-6 bg-gray-50 rounded-xl cursor-pointer hover:bg-gray-100 transition-all border-l-4 border-blue-400 hover:shadow-md max-w-md"
              >
                <div className="flex justify-between items-start mb-3">
                  <h3 className="text-xl font-bold text-gray-900">
                    {chapter.titre}
                  </h3>
                  <div className="text-right">
                    <span className="text-sm bg-blue-100 text-blue-800 px-3 py-1 rounded-full font-medium block mb-2">
                      Ordre {chapter.order}
                    </span>
                    <Link
                      to={`/stories/${storyId}/chapters/${chapter.id}/edit`}
                      className="text-sm bg-gray-600 text-white px-4 py-1 rounded-lg hover:bg-gray-700 transition-colors inline-block"
                    >
                      Modifier
                    </Link>
                  </div>
                </div>
                <p className="text-gray-700 mb-3 leading-relaxed">
                  {chapter.textNarratif?.substring(0, 120)}...
                </p>
                {chapter.instructions && (
                  <div className="bg-yellow-50 border-l-4 border-yellow-400 pl-4 py-3 mb-3">
                    <p className="text-sm font-medium text-yellow-900">
                      📋 Instructions :
                    </p>
                    <p className="text-sm text-yellow-800 mt-1">
                      {chapter.instructions}
                    </p>
                  </div>
                )}
              </div>
            ))}
          </div>
        </div>
      </div>

      {addingChapter && (
        <div className="fixed inset-0 bg-black bg-opacity-50 flex items-center justify-center z-50 p-4">
          <div className="bg-white p-8 rounded-2xl shadow-2xl max-w-md w-full max-h-[90vh] overflow-y-auto">
            <div className="flex justify-between items-center mb-6">
              <h2 className="text-2xl font-bold text-gray-900">
                Nouveau Chapitre
              </h2>
              <button
                onClick={() => {
                  setAddingChapter(false);
                  setChapterForm({
                    titre: "",
                    textNarratif: "",
                    instructions: "",
                  });
                }}
                className="text-2xl hover:text-red-500 transition-colors"
              >
                ✕
              </button>
            </div>
            <form onSubmit={handleAddChapter} className="space-y-4">
              <input
                type="text"
                required
                value={chapterForm.titre}
                onChange={(e) =>
                  setChapterForm({ ...chapterForm, titre: e.target.value })
                }
                className="w-full p-4 border rounded-xl focus:ring-2 focus:ring-blue-500 focus:border-transparent"
                placeholder="Titre du chapitre"
              />
              <textarea
                required
                value={chapterForm.textNarratif}
                onChange={(e) =>
                  setChapterForm({
                    ...chapterForm,
                    textNarratif: e.target.value,
                  })
                }
                className="w-full p-4 border rounded-xl focus:ring-2 focus:ring-blue-500 focus:border-transparent h-32"
                placeholder="Texte narratif"
              />
              <textarea
                value={chapterForm.instructions}
                onChange={(e) =>
                  setChapterForm({
                    ...chapterForm,
                    instructions: e.target.value,
                  })
                }
                className="w-full p-4 border rounded-xl focus:ring-2 focus:ring-blue-500 focus:border-transparent h-24"
                placeholder="Instructions (optionnel)"
              />
              <div className="flex gap-3 pt-2">
                <button
                  type="button"
                  onClick={() => {
                    setAddingChapter(false);
                    setChapterForm({
                      titre: "",
                      textNarratif: "",
                      instructions: "",
                    });
                  }}
                  className="flex-1 bg-gray-500 text-white px-6 py-3 rounded-xl hover:bg-gray-600 font-semibold transition-colors"
                >
                  Annuler
                </button>
                <button
                  type="submit"
                  className="flex-1 bg-green-600 text-white px-6 py-3 rounded-xl font-bold hover:bg-green-700 transition-colors"
                >
                  Créer Chapitre
                </button>
              </div>
            </form>
          </div>
        </div>
      )}
      <Outlet />
    </>
  );
}
