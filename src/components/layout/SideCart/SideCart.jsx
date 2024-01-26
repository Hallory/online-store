// SideCart.js
import React from 'react';
import { useNavigate } from 'react-router-dom';
import { useDispatch, useSelector } from 'react-redux';

import { sideCartVisible } from '../../../redux/slices/cartSlice';

import CartItems from '../../../pages/cart/CartItems';
import Button from '../../elements/Button';

const SideCart = () => {
    const navigate = useNavigate();
    const dispatch = useDispatch();
    const cart = useSelector(state => state.cart.cart);
    const isSideCartVisible = useSelector(state => state.cart.isVisible);

    const toggleSideCart = () => {
        dispatch(sideCartVisible(!isSideCartVisible));
    }

    const goToCart = () => {
        navigate('/products/cart');
        dispatch(sideCartVisible(!isSideCartVisible));
    };

    const closeSideCart = () => {
        dispatch(sideCartVisible(false));
    }

    const totalAmount = cart.reduce((total, product) => total + (product.price * product.quantity), 0);

    return (
        <>
            {isSideCartVisible && (
                <div
                    className="fixed top-[8.125rem] left-0 w-full h-full bg-black-800 bg-opacity-10 z-39"
                    onClick={closeSideCart}
                ></div>
            )}
            <div className={isSideCartVisible ? 'block' : 'hidden'}>
                <div>
                    <div className="absolute top-[8.125rem] right-0 z-40 bg-black-100 h-full w-1/3 px-6 rounded-xl shadow-[0px_12px_12px_0px_rgba(75,_94,_143,_0.19),0px_28px_17px_0px_rgba(75,_94,_143,_0.11),0px_77px_22px_0px_rgba(75,_94,_143,_0.00)]">
                        <div className='py-4 flex justify-between'>
                            <h2 className="text-[1.5rem] text-black-900 font-bold leading-[110%]">Shopping Cart</h2>
                            <p className='text-[1.125rem] text-black-700 leading-[140%]'>{cart.length} item(s) in cart</p>
                        </div>
                        {cart.length > 0 ? (
                            <>
                                <CartItems />
                                <div className='flex justify-between text-black-900 text-[1.25rem] font-bold pt-6 pb-4'>
                                    <span>Total</span>
                                    <span>{totalAmount.toFixed(2)}$</span>
                                </div>
                                <div className='w-full flex flex-col gap-4 pb-6'>
                                    <Button
                                        label='View Cart'
                                        onClick={() => goToCart()}
                                        variant='primary-full' />
                                    <Button
                                        label='Continue Shopping'
                                        onClick={() => toggleSideCart()}
                                        variant='primary-outline' />
                                </div>
                            </>
                        ) : (
                            <p>Your cart is empty. Add items to your cart to continue shopping.</p>
                        )}
                    </div>
                </div>
            </div>
        </>
    );
}

export default SideCart;
