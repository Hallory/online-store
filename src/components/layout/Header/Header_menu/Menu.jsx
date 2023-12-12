import React from 'react';
import MenuItem from './MenuItem';

import { LuScale } from "react-icons/lu";
import { IoMdHeartEmpty } from "react-icons/io";
import { HiOutlineShoppingCart } from "react-icons/hi";



const Menu = () => {
    return (
        <nav className='max-w-lg'>
            <ul className='flex flex-wrap'>
                <li>
                    <MenuItem name='Compare' icon={LuScale} route='/compare' />
                </li>
                <li>
                    <MenuItem name='Wish list' icon={IoMdHeartEmpty} route='/wishlist' />
                </li>
                <li>
                    <MenuItem name='Cart' icon={HiOutlineShoppingCart} route='/cart' />
                </li>
            </ul>
        </nav>
    );
};

export default Menu;
