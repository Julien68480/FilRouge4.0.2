import { useParams, useNavigate } from "react-router-dom";
import { useState, useEffect, useRef } from "react";
import { chapterService } from "../services/chapterService";
import { shapesService } from "../services/shapesService";
import {
  Stage,
  Layer,
  Rect,
  Circle,
  RegularPolygon,
  Transformer,
} from "react-konva";
import ShapePalette from "../components/canvas/ShapePalette";

export default function EditChapterPage() {
  const [canvasShapes, setCanvasShapes] = useState([]);
  const { storyId, chapterId } = useParams();
  const navigate = useNavigate();
  const [selectedShapeId, setSelectedShapeId] = useState(null);
  const [deletedShapeIds, setDeletedShapeIds] = useState([]);
  const transformerRef = useRef(null);
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

  useEffect(() => {
    if (transformerRef.current) {
      const stage = transformerRef.current.getStage();
      const selectedNode = stage.findOne(`#shape-${selectedShapeId}`);
      if (selectedNode) {
        transformerRef.current.nodes([selectedNode]);
      } else {
        transformerRef.current.nodes([]);
      }
      transformerRef.current.getLayer().batchDraw();
    }
  }, [selectedShapeId]);

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

  const renderShape = (shape, isDraggable = false) => {
    const commonProps = {
      id: `shape-${shape.id}`,
      x: shape.x,
      y: shape.y,
      fill: shape.color,
      draggable: isDraggable,
      onClick: () => setSelectedShapeId(shape.id),
      stroke: selectedShapeId === shape.id ? "#3b82f6" : undefined,
      strokeWidth: selectedShapeId === shape.id ? 3 : 0,
      onDragEnd: isDraggable
        ? (e) => {
            setCanvasShapes((prev) =>
              prev.map((s) =>
                s.id === shape.id
                  ? { ...s, x: e.target.x(), y: e.target.y() }
                  : s,
              ),
            );
            setShapes((prev) =>
              prev.map((s) =>
                s.id === shape.id
                  ? { ...s, x: e.target.x(), y: e.target.y() }
                  : s,
              ),
            );
          }
        : undefined,
      onTransformEnd: isDraggable
        ? (e) => {
            const node = e.target;
            const scaleX = node.scaleX();
            const scaleY = node.scaleY();

            node.scaleX(1);
            node.scaleY(1);

            const newX = node.x();
            const newY = node.y();

            setCanvasShapes((prev) =>
              prev.map((s) =>
                s.id === shape.id
                  ? {
                      ...s,
                      x: newX,
                      y: newY,
                      width: s.width ? s.width * scaleX : s.width,
                      length: s.length ? s.length * scaleY : s.length,
                      radius: s.radius
                        ? s.radius * Math.max(scaleX, scaleY)
                        : s.radius,
                      side: s.side ? s.side * Math.max(scaleX, scaleY) : s.side,
                    }
                  : s,
              ),
            );

            setShapes((prev) =>
              prev.map((s) =>
                s.id === shape.id
                  ? {
                      ...s,
                      x: newX,
                      y: newY,
                      width: s.width ? s.width * scaleX : s.width,
                      length: s.length ? s.length * scaleY : s.length,
                      radius: s.radius
                        ? s.radius * Math.max(scaleX, scaleY)
                        : s.radius,
                      side: s.side ? s.side * Math.max(scaleX, scaleY) : s.side,
                    }
                  : s,
              ),
            );
          }
        : undefined,
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

      if (canvasShapes.length > 0) {
        const shapesToSend = canvasShapes.map(({ id, ...rest }) => rest);
        await shapesService.createShapes(chapterId, shapesToSend);
      }
      if (shapes.length > 0) {
        await shapesService.updateShapes(chapterId, shapes);
      }
      if (deletedShapeIds.length > 0) {
        await Promise.all(
          deletedShapeIds.map((id) => shapesService.deleteShape(id)),
        );
      }

      navigate(-1, { state: { updatedChapter: form } });
    } catch (error) {
      console.error(error);
    }
  };
  const handleDeleteChapter = async () => {
    if (!storyId || !chapterId) return;

    if (window.confirm("Supprimer ce chapitre ?")) {
      try {
        await chapterService.deleteChapter(storyId, chapterId);
        navigate(-1); // retour arrière
      } catch (error) {
        console.error("Erreur suppression chapitre", error);
      }
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
              {shapes.map((shape) => renderShape(shape, true))}
              {canvasShapes.map((shape) => renderShape(shape, true))}
              <Transformer
                ref={transformerRef}
                rotateEnabled={false}
                enabledAnchors={[
                  "top-left",
                  "top-right",
                  "bottom-left",
                  "bottom-right",
                ]}
                boundBoxFunc={(oldBox, newBox) => {
                  if (newBox.width < 20 || newBox.height < 20) {
                    return oldBox;
                  }
                  return newBox;
                }}
              />
            </Layer>
          </Stage>
          {selectedShapeId && (
            <button
              type="button"
              onClick={() => {
                if (typeof selectedShapeId === "number") {
                  setDeletedShapeIds((prev) => [...prev, selectedShapeId]);
                }
                setCanvasShapes((prev) =>
                  prev.filter((s) => s.id !== selectedShapeId),
                );
                setShapes((prev) =>
                  prev.filter((s) => s.id !== selectedShapeId),
                );
                setSelectedShapeId(null);
              }}
              className="mt-2 bg-red-500 text-white px-4 py-2 rounded-xl hover:bg-red-600 font-semibold"
            >
              🗑️ Supprimer la forme
            </button>
          )}
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
          <button
            type="button"
            onClick={handleDeleteChapter}
            className="mt-2 bg-red-500 text-white px-4 py-2 rounded-xl hover:bg-red-600 font-semibold"
          >
            🗑️ Supprimer le chapitre
          </button>
        </div>
      </form>
    </div>
  );
}
