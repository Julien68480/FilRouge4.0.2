import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import Navbar from './components/layout/Navbar';
import HomePage from './pages/HomePage';
import StoryManagerPage from './pages/StoryManagerPage';
import DetailStoryPage from './pages/DetailStoryPage';

function App() {
  return (
    <Router> 
      <div className="min-h-screen bg-gray-50">
        <Navbar />  
        <Routes>
          <Route path="/" element={<HomePage />} />
          <Route path="/stories" element={<StoryManagerPage />} />
          <Route path="/stories/:storyId" element={<DetailStoryPage />} />
        </Routes>
      </div>
    </Router>
  );
}

export default App;
