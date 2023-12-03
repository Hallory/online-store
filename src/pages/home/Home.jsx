import React, { useEffect, useState } from 'react';
import axios from 'axios';
import ProductCard from '../../components/product/ProductCard';
import { Link } from 'react-router-dom';
import { AnimatePresence, motion } from 'framer-motion';

const Home = ({products}) => {
    

    return (
        <div>
            <h2>Home</h2>
            <Link className='text-blue-500' to="/products">View Products</Link>
            
        </div>
    );
};

export default Home;