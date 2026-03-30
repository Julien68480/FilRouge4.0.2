import { useParams, useNavigate } from "react-router-dom";
import { useState, useEffect } from "react";
import { chapterService } from "../services/chapterService";
import { shapesService } from "../services/shapesService";
import { Stage, Layer, Rect, Circle, RegularPolygon } from "react-konva";
import ShapePalette from "../components/canvas/ShapePalette";

export default function EditChapterPage() {
  const [canvasShapes, setCanvasShapes] = useState([]);
  const { storyId, chapterId } = useParams();
  const navigate = useNavigate();
  const [form, setForm] = useState({
    titre: "",
    textNarratif: "",
    instructions: "",
  });
  const [shapes, setShapes] = useState([]);
  const [loading, setLoading] = useState(true);
  useEffect(() => {
    if (chapterId) {
      shapesService
        .getShapesByChapter(chapterId)
        .then((res) => setShapes(res.data))
        .catch((err) => console.error(err));
    }
  }, [chapterId]);

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

  const SHAPE_COLORS = {
    rectangle: "#60a5fa",
    carre: "#34d399",
    rond: "#f87171",
    triangle: "#fbbf24",
  };

  const addShapeToCanvas = (type) => {
    const newShape = {
      id: `local-${Date.now()}`,
      type,
      x: 50,
      y: 50,
      color: SHAPE_COLORS[type],
      width: 100,
      length: 60,
      radius: 40,
      side: 80,
    };
    setCanvasShapes((prev) => [...prev, newShape]);
  };

  const renderShape = (shape) => {
    const commonProps = {
      x: shape.x,
      y: shape.y,
      fill: shape.color,
    };

    switch (shape.type) {
      case "rectangle":
        return (
          <Rect
            key={shape.id}
            {...commonProps}
            width={shape.width}
            height={shape.length}
          />
        );

      case "carre":
        return (
          <Rect
            key={shape.id}
            {...commonProps}
            width={shape.side}
            height={shape.side}
          />
        );

      case "rond":
        return <Circle key={shape.id} {...commonProps} radius={shape.radius} />;

      case "triangle":
        return (
          <RegularPolygon
            key={shape.id}
            {...commonProps}
            sides={3}
            radius={shape.side}
          />
        );

      default:
        return null;
    }
  };
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
    <div className="p-8 bg-white rounded-2xl shadow-xl max-w-6xl mx-auto">
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
          placeholder="Instructions"
        />
        <div className="border rounded-xl overflow-hidden">
          <ShapePalette onAddShape={addShapeToCanvas} />
          <Stage width={900} height={900} className="bg-white">
            <Layer>
              {shapes.map((shape) => renderShape(shape))}
              {canvasShapes.map((shape) => renderShape(shape))}
            </Layer>
          </Stage>
        </div>
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
