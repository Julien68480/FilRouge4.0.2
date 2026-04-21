const SHAPES = ["rectangle", "carre", "rond", "triangle"];

export default function ShapePalette({ onAddShape }) {
  return (
    <div className="flex gap-2 p-3 bg-gray-50 border-b">
      {SHAPES.map((type) => (
        <button
          key={type}
          type="button"
          onClick={() => onAddShape(type)}
          className="bg-blue-500 text-white px-3 py-1.5 rounded-lg text-sm font-medium hover:bg-blue-600 transition-colors capitalize"
        >
          + {type}
        </button>
      ))}
    </div>
  );
}
