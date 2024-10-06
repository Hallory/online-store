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
import AdminPanel from './admin/admin-panel/AdminPanel';
import Layout from './components/layout/Layout';

function App() {
    const [products, setProducts] = useState([]);
    const dispatch = useDispatch();
    const baseUrl = useSelector((state) => state.baseUrl.value);
    const [prevPath, setPrevPath] = useState(null);
    const location = useLocation();
    const navigate = useNavigate();

    useEffect(() => {
        const url = baseUrl;
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

    const shouldAnimate =
        prevPath &&
        (prevPath.includes('/login') || prevPath.includes('/register'));

    return (
        <div className="w-full">
            <Routes>
                <Route
                    path="/"
                    element={
                        <Layout>
                            <Home products={products} />
                        </Layout>
                    }
                />
                <Route
                    path="/products"
                    element={
                        <Layout>
                            <Products products={products} />
                        </Layout>
                    }
                />
                <Route
                    path="/products/:productId"
                    element={
                        <Layout>
                            <ProductDetails products={products} />
                        </Layout>
                    }
                />
                <Route
                    path="/wishlist"
                    element={
                        <Layout>
                            <Wishlist products={products} />
                        </Layout>
                    }
                />
                <Route
                    path="/login"
                    element={
                        <AnimatePresence mode="wait">
                            <Layout>
                                {' '}
                                <Login
                                    shouldAnimate={shouldAnimate}
                                    key="login"
                                />
                            </Layout>
                        </AnimatePresence>
                    }
                />
                <Route
                    path="/register"
                    element={
                        <AnimatePresence mode="wait">
                            <Layout>
                                <Register
                                    shouldAnimate={shouldAnimate}
                                    key="register"
                                />
                            </Layout>
                        </AnimatePresence>
                    }
                />
                <Route path="/admin/*" element={<AdminPanel />} />
            </Routes>
        </div>
    );
}

export default App;
