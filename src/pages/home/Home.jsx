import React, { useEffect, useState } from 'react';
import axios from 'axios';
import ProductCard from '../../components/product/ProductCard';
import { AnimatePresence, motion } from 'framer-motion';
const Home = () => {
    const [products, setProducts] = useState([]);
    useEffect(() => {
        const url = 'https://dummyjson.com/products';
        axios.get(url)
            .then((res) => {
                const data = res.data;
                setProducts(data.products);
               console.log(data.products[0].id);
            })
            .catch((err) => {
                console.log(err);
               
            });
    }, []);

    return <div >
        Home
        <div className="grid grid-cols-1 sm:grid-cols-2 md:grid-cols-3 lg:grid-cols-4 gap-4 p-4">
            {products &&
                    products.map((product) => (
                         <ProductCard key={product.id} product={product} />
                         
                      
                    ))}
        </div>
                
        </div>;
};

export default Home;
