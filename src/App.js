import { useEffect, useState } from 'react';
import { Routes, Route, BrowserRouter, useNavigate, useLocation } from 'react-router-dom';
import Login from './pages/login/Login';
import Register from './pages/register/Register';
import Home from './pages/home/Home';
import { AnimatePresence } from 'framer-motion';
import ProductDetails from './pages/product-details/ProductDetails';
import axios from 'axios';
import Products from './pages/products/Products';

function App() {
  const [products, setProducts] = useState([]);
  const [prevPath, setPrevPath] = useState(null);
  const location = useLocation();
  const navigate = useNavigate();

  useEffect(() => {
    const url = 'https://dummyjson.com/products';
    axios
      .get(url)
      .then((res) => {
        const data = res.data;
        setProducts(data.products);
      })
      .catch((err) => {
        console.log(err);
      });

    // Set the previous path when the route changes
    setPrevPath(location.pathname);
  }, [location.pathname]);

  const shouldAnimate = prevPath && (prevPath.includes('/login') || prevPath.includes('/register'));

  return (
    <div className={location.pathname === "/login" || location.pathname === "/register" ? "pt-0" : "pt-[50px]"}>
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
            <AnimatePresence mode='wait'>
              <Login shouldAnimate={shouldAnimate} key="login" />
            </AnimatePresence>
          }
        />
        <Route
          path="/register"
          element={
            <AnimatePresence mode='wait'>
              <Register  shouldAnimate={shouldAnimate} key="register" />
            </AnimatePresence>
          }
        />
      </Routes>
    </div>
  );
}

export default App;
