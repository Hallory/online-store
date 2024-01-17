import React from 'react';
import { useDispatch, useSelector } from 'react-redux';
import { sideCartVisible } from '../../../../redux/slices/cartSlice';

import MenuItem from './MenuItem';

import { ScaleIcon } from '@heroicons/react/solid';
import { HeartIcon } from '@heroicons/react/outline';
import { ShoppingCartIcon } from '@heroicons/react/outline';

const Menu = () => {
    const dispatch = useDispatch();
    const cart = useSelector(state => state.cart.cart);
    const isSideCartVisible = useSelector(state => state.cart.isVisible);

    const toggleSideCart = () => {
        dispatch(sideCartVisible(!isSideCartVisible));
    }

    return (
        <nav className='max-w-lg'>
            <ul className='flex flex-wrap'>
                <li>
                    <MenuItem name='Compare' icon={ScaleIcon} route='/products/compare' />
                </li>
                <li>
                    <MenuItem name='Wish list' icon={HeartIcon} route='/wishlist' />
                </li>
                <li className='relative' onClick={toggleSideCart}>
                    <MenuItem name='Cart' icon={ShoppingCartIcon}  />
                    <div className='absolute top-[3px] left-[25px] flex flex-col justify-center items-center bg-primary-500 rounded-full'>
                        <span className='text-[0.625rem] text-black-100 px-1 h-[14px] cursor-pointer'>
                            {cart.length}
                        </span>
                    </div>
                </li>
            </ul>
        </nav>
    );
};

export default Menu;
