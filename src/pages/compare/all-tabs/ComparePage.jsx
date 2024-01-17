import React from 'react';

import Button from '../../../components/elements/Button';
import ProductRating from '../../../components/product/ProductRating';

import { XIcon } from "@heroicons/react/outline";
import { ShoppingCartIcon } from '@heroicons/react/outline/';
import Icon from "../../../components/product/Icon";

const ComparePage = ({ product }) => {
    console.log(product);


    return (
        <div className='min-w-[18.75rem] max-w-[18.75rem] w-full text-black-900'>
            <div className='p-4'>
                <div className='flex items-start'>
                    <div className='min-w-[60px] h-[60px] m-auto'>
                        <img className='mx-auto w-full h-full object-cover object-center' src={product.thumbnail} alt='product img' />
                    </div>
                    <p className='pl-2 text-sm'>Smartphone Motorola G72 8/128GB Meteorite Grey (PAVG0004RS)</p>
                    <Button
                        icon={XIcon}
                        onClick={console.log('Remove to compare!')}
                        variant='icon-remove'
                    />
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
                <div className='flex justify-center gap-1 pb-6'>
                    <Button
                        label='Buy now'
                        onClick={() => { console.log('Click!'); }}
                        variant='primary-outline'
                    />
                    <Button
                        icon={ShoppingCartIcon}
                        onClick={() => { console.log('Click!'); }}
                        variant='icon-fill'
                    />
                </div>
            </div>
                <div className='leading-[140%]'>
                    {Object.values(product).map((value, index) => (
                        <div
                            className='px-6 py-4 '
                            key={index}>
                            {value}
                        </div>
                    ))}
            </div>
        </div>
    );
};

export default ComparePage;

