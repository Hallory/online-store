import React from 'react';
import { useDispatch, useSelector } from 'react-redux';

import { incrementQuantity, decrementQuantity, removeFromCart } from '../../redux/slices/cartSlice';

import Button from '../../components/elements/Button';

import { XIcon, MinusIcon, PlusSmIcon } from "@heroicons/react/outline";

const CartItems = () => {
    const dispatch = useDispatch();
    const cart = useSelector(state => state.cart.cart);

    const incrementProductQuantity = (productId) => {
        dispatch(incrementQuantity(productId));
    };

    const decrementProductQuantity = (productId) => {
        dispatch(decrementQuantity(productId));
    };

    const removeItemFromCart = (productId) => {
        dispatch(removeFromCart(productId));
    };

    return (
        <div className='max-w-[38.438rem] p-6'>
            
        {cart.map((product) => (
            <div key={product.id} className='flex gap-2 py-4'>
                <div className='h-[5.625rem] max-w-[5.625rem] m-auto'>
                    <img className='mx-auto w-full h-full object-cover object-center' src={product.thumbnail} alt='product img' />
                </div>
                <div className='flex flex-col'>
                    <p className='flex-1 font-medium'>Smartphone Motorola G72 8/128GB Meteorite Grey (PAVG0004RS)</p>
                    <div className='flex gap-4'>
                        <div className='flex border border-black-200 rounded-sm'>
                            <button
                                className='w-6 h-6'
                                onClick={() => decrementProductQuantity(product.id)}>
                                <MinusIcon />
                            </button>
                            <p className='px-3'>{product.quantity || 0}</p>
                            <button
                                className='w-6 h-6'
                                onClick={() => incrementProductQuantity(product.id)}>
                                <PlusSmIcon />
                            </button>
                        </div>
                        <p className='font-medium leading-[140%] self-center'>{product.price * product.quantity} $</p>
                    </div>
                </div>
                <div>
                    <Button
                        icon={XIcon}
                        onClick={() => removeItemFromCart(product.id)}
                        variant='icon-remove'
                />
                </div>
            </div>
        ))}
        </div>
    );
}

export default CartItems;
