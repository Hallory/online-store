import { useEffect, useState } from 'react';
import { Routes, Route, useNavigate, useLocation } from 'react-router-dom';
import './App.css';
import { AnimatePresence } from 'framer-motion';
import axios from 'axios';
import Login from './pages/login/Login';
import Register from './pages/register/Register';
import Home from './pages/home/Home';
import ProductDetails from './pages/product-details/ProductDetails';
import Products from './pages/products/Products';
import { useDispatch, useSelector } from 'react-redux';
import Wishlist from './pages/wishlist/Wishlist';
import Compare from './pages/compare/Compare';
import Cart from './pages/cart/Cart';
import FAQs from './pages/menu-items/FAQs';
import Checkout from './pages/checkout/Сheckout';

function App() {
  const [products, setProducts] = useState([]);
  const dispatch = useDispatch();
  const baseUrl = useSelector((state) => state.baseUrl.value);
  const [prevPath, setPrevPath] = useState(null);
  const location = useLocation();
  const navigate = useNavigate();

  useEffect(() => {
    const url = baseUrl
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

    setPrevPath(location.pathname);
  }, []);

  const shouldAnimate = prevPath && (prevPath.includes('/login') || prevPath.includes('/register'));

  return (
    <div className={location.pathname === "/login" || location.pathname === "/register" ? "pt-0" : "pt-0"}>
      <Routes>
        <Route path="/" element={<Home products={products} />} />
        <Route path="/products" element={<Products products={products} />} />
        <Route path="/products/:productId" element={<ProductDetails products={products} />} />
        <Route path="/wishlist" element={<Wishlist products={products} />} />
        <Route path="/products/compare" element={<Compare products={products} />} />
        <Route path="/products/cart" element={<Cart products={products} />} />
        <Route path="/general-questions" element={<FAQs products={products} />} />
        <Route path="/checkout" element={<Checkout products={products} />} />
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
              <Register shouldAnimate={shouldAnimate} key="register" />
            </AnimatePresence>
          }
        />
      </Routes>
    </div>
  );
}

export default App;