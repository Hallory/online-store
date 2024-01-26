import React from 'react';
import { useNavigate } from 'react-router-dom';

import { useSelector } from 'react-redux';

import CartItems from './CartItems';
import Button from '../../components/elements/Button'

const Cart = (products) => {
    const navigate = useNavigate();
    
    const cart = useSelector(state => state.cart.cart);

    const totalAmount = cart.reduce((total, product) => total + (product.price * product.quantity), 0);

    const goToCheakout = () => {
        navigate('/checkout');
    }

    return (
        <div className='flex items-top justify-between mx-auto max-w-[74.688rem] mb-[6.875rem]'>
            <CartItems />
            <div className='flex flex-col gap-8 max-w-[33.125rem] w-full bg-gray-200 rounded-xl border border-black-200 p-6 mt-10'>
                <h3 className='text-[1.5rem] text-black-900 font-bold'>Have an eCoupon or discount?</h3>
                <div>
                    <h6 className='text-black-700'>eCoupon Code</h6>
                    <div className='px-3 py-2 mb-3 items-center self-stretch bg-black-100 border border-black-200 rounded-md text-black-900 focus-visible:outline-none'>
                        <input
                        className='text-[1.125rem] overflow-hidden text-black-900 leading-[140%] bg-transparent placeholder:text-[1.125rem] placeholder:text-black-700 placeholder:text-ellipsis placeholder:whitespace-nowrap placeholder:font-normal focus-visible:outline-none'
                        type="text"
                        placeholder='Enter eCoupon Code' />
                    </div>
                </div>
                <div>
                    <div className='flex justify-between font-bold text-[1.125rem] text-black-800 mb-4'>
                        <span>Subtotal</span>
                        <span>{totalAmount.toFixed(2)}$</span>
                    </div>
                    <div className='flex justify-between font-bold text-[1.5rem] text-black-900'>
                        <span>Total</span>
                        <span>{totalAmount.toFixed(2)}$</span>
                    </div>
                </div>
                <div className='w-full flex flex-col gap-4 pb-6'>
                    <Button
                        label='Checkout'
                        onClick={() => goToCheakout()}              
                        variant='primary-full' />
                    <Button
                        label='Continue Shopping'
                        onClick={() => console.log('View Cart!')}
                        variant='primary-outline' />
                </div>
            </div>
        </div>
    );
}

export default Cart;