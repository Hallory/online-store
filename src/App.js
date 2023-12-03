import './App.css';
import { Routes, Route, BrowserRouter } from 'react-router-dom';
import Login from './pages/login/Login';
import Register from './pages/register/Register';
import Home from './pages/home/Home';
import { AnimatePresence } from 'framer-motion';
import ProductDetails from './pages/product-details/ProductDetails';
import { useEffect, useState } from 'react';
import axios from 'axios';
import Products from './pages/products/Products';

function App() {
  const [products, setProducts] = useState([]);

  useEffect(() => {
    const url = 'https://dummyjson.com/products';
    axios
      .get(url)
      .then((res) => {
        const data = res.data;
        setProducts(data.products);
        console.log(data.products);
      })
      .catch((err) => {
        console.log(err);
      });
  }, []);

  return (
    <div className="App pt-[100px]">
      <Routes>
        <Route path="/" element={<Home products={products} />} />
        <Route path="/products" element={<Products products={products} />} />
        <Route
          path="/products/:productId"
          element={<ProductDetails products={products} />}
        />
        <Route
          path="/login"
          element={
            <AnimatePresence mode="wait">
              <Login />
            </AnimatePresence>
          }
        />
        <Route
          path="/register"
          element={
            <AnimatePresence mode="wait">
              <Register />
            </AnimatePresence>
          }
        />
      </Routes>
    </div>
  );
}

export default App;