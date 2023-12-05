import React from 'react';
import { motion } from 'framer-motion';
import Button from '../elements/Button';
import { IoMdHeartEmpty } from "react-icons/io";

const ProductCard = ({ product }) => {
    return (
        <motion.div
            className='rounded-md overflow-hidden border border-gray-200'
        >
            {product.thumbnail && (
                <img className='h-60 w-full object-cover object-center' src={product.thumbnail} alt='product img' />
            )}
            <div className='px-3 pt-3'>
                <h3 className=''>{product.title}</h3>
                <div className=''>
                    <p className=''>${product.price}</p>
                </div>
            </div>
            <div className='flex justify-center gap-1 pb-4 pt-2'>
                <Button
                    label='Add to Cart'
                    onClick={() => { console.log('Click!'); }}
                    variant=''
                />
                <Button
                    icon={IoMdHeartEmpty}
                    onClick={() => { console.log('Click!'); }}
                    variant='icon'
                />
            </div>
        </motion.div>
    );
};

export default ProductCard;