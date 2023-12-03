import React from 'react';
import {motion} from 'framer-motion'
const ProductCard = ({ product }) => {

    return (
        <motion.div
        className='flex flex-col items-center justify-center w-72 h-96 bg-white rounded-lg shadow-lg'
        >
            {product.title}
            {product.description}
        </motion.div>
    );
};

export default ProductCard;