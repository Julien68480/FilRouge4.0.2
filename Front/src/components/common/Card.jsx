export default function Card({ story }) {
  // Couleurs par difficulté
  const getDifficultyColor = (level) => {
    switch (level?.toUpperCase()) {
      case 'HARD': return 'from-red-500 to-red-600 text-red-800 bg-red-100';
      case 'MEDIUM': return 'from-orange-500 to-orange-600 text-orange-800 bg-orange-100';
      case 'EASY': return 'from-green-500 to-green-600 text-green-800 bg-green-100';
      default: return 'from-gray-500 to-gray-600 text-gray-800 bg-gray-100';
    }
  };

  const difficultyColor = getDifficultyColor(story.difficultyLevel);
  const titleColor = story.difficultyLevel === 'HARD' ? 'text-red-600' : 
                    story.difficultyLevel === 'MEDIUM' ? 'text-orange-600' : 
                    'text-blue-600';

  return (
    <div className={`bg-gradient-to-br ${difficultyColor} from-white to-gray-50 p-8 rounded-2xl shadow-lg hover:shadow-2xl hover:-translate-y-2 transition-all duration-300 border border-gray-100 group`}>
      <div className="flex justify-between items-start mb-6">
        <h2 className={`text-2xl font-bold ${titleColor} group-hover:scale-105 transition-transform`}>
          {story.titre}
        </h2>
        <span className={`px-4 py-2 ${difficultyColor} text-xs font-bold rounded-full shadow-md`}>
          {story.difficultyLevel}
        </span>
      </div>
      <p className="text-gray-700 leading-relaxed mb-6 line-clamp-3">
        {story.description}
      </p>
    </div>
  );
}
