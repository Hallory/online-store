import React from 'react';
import { motion } from 'framer-motion';
import Button from '../elements/Button';
import { IoMdHeartEmpty } from "react-icons/io";
import { useDispatch } from 'react-redux';
import { addToWishlist } from '../../redux/slices/wishlistSlice';

const ProductCard = ({ product }) => {
    const dispatch = useDispatch();

    const handleAddToWishlist = () => {
        dispatch(addToWishlist(product));
    }

    return (
        <motion.div
            className='rounded-md overflow-hidden border border-gray-200'
        >
            {product.thumbnail && (
                <div className='w-[242px] h-[202px] p-4 m-auto'>
                    <img className='mx-auto w-full h-full object-cover object-center' src={product.thumbnail} alt='product img' />
                    <Button
                        className='absolute'
                        icon={IoMdHeartEmpty}
                        onClick={handleAddToWishlist}
                        variant='icon'
                    />
                </div>
            )}
            <div className='px-3 pt-3'>
                <h3 className=''>{product.title}</h3>
                <div className=''>
                    <p className=''>${product.price}</p>
                </div>
            </div>
        </motion.div>
    );
};

export default ProductCard;