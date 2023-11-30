import './App.css';
import  {Routes,Route, BrowserRouter} from "react-router-dom";
import Login from './pages/login/Login';
import Register from './pages/register/Register';
import Home from './pages/home/Home';
import { AnimatePresence } from 'framer-motion';
function App() {
  return (
    <div className="App">
        <Routes>
          <Route path="/" element ={<Home />}/>
          <Route path="/login" element={<AnimatePresence mode='wait'><Login /></AnimatePresence>} />
          <Route path="/register" element={<AnimatePresence mode='wait'><Register /></AnimatePresence>} />
        </Routes>
    </div>
  );
}

export default App;
