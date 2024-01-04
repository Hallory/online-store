import React from 'react';
import { motion } from 'framer-motion';
import { useDispatch } from 'react-redux';

import { addToWishlist } from '../../redux/slices/wishlistSlice';

import ProductRating from './ProductRating';
import Button from '../elements/Button';

import { HeartIcon } from "@heroicons/react/outline";
import { ScaleIcon } from "@heroicons/react/outline";
import Icon from "./Icon";

const ProductCard = ({ product }) => {
    const dispatch = useDispatch();

    const handleAddToWishlist = (e) => { 
        e.preventDefault(); 
        e.stopPropagation(); 
        dispatch(addToWishlist(product)); 
        console.log('Added to wishlist!'); 
    }

    return (
        <motion.div
            className='rounded-md overflow-hidden border border-gray-200'
        >
            {product.thumbnail && (
                <div className='relative'>
                    <div className='w-[242px] h-[202px] p-4 m-auto'>
                        <img className='mx-auto w-full h-full object-cover object-center' src={product.thumbnail} alt='product img' />
                    </div>
                    <div className='absolute top-1 right-1 pl-2'>
                        <Button
                            icon={HeartIcon}
                            onClick={handleAddToWishlist}
                            variant='icon'
                        />
                    </div>
                    <div className='absolute top-11 right-1 pl-2'>
                        <Button
                            icon={ScaleIcon}
                            onClick={console.log('Added to compare!')}
                            variant='icon'
                        />
                    </div>
                </div>
            )}
            <div className='flex flex-col gap-2 pt-4 px-2 pb-5'>
                <div className='text-[0.625rem] text-black-800'>
                    <span className='pr-1'>Product code:</span>
                    <span>{product.id}</span>
                </div>
                <div className='text-[1.125rem] text-black-900'>
                    <span className=''>Смартфон Motorola G72 8/128GB Meteorite Grey (PAVG0004RS)</span>
                </div>
                <div>
                    <ProductRating product={product}/>
                </div>
                <div className='flex justify-between'>
                    <div>
                        <div className='text-[0.875rem] text-black-800 font-medium tracking-[0.031rem] line-through'>
                            <span>{product.price}</span>
                        </div>
                        <div className='text-[1.25rem] text-danger-600 font-medium tracking-[0.031rem]'>
                            <span>{Math.ceil(product.price - (product.price / product.discountPercentage))} ₴</span>
                        </div>
                    </div>
                    <div className='flex gap-3'>
                        <div className='flex items-center gap-0.5'>
                            <Icon name="mono" />
                            <span className='text-black-800 text-[0.75rem]'>4</span>
                        </div>
                        <div className='flex items-center gap-0.5'>
                            <Icon name="privat" />
                            <span className='text-black-800 text-[0.75rem]'>8</span>
                        </div>
                        <div className='flex items-center gap-0.5'>
                            <Icon name="credit" />
                            <span className='text-black-800 text-[0.75rem]'>10</span>
                        </div>
                    </div>
                </div>
            </div>
        </motion.div>
    );
};

export default ProductCard;