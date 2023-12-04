import React from 'react';
import MenuItem from './MenuItem';

import { MdCompare } from "react-icons/md";
import { IoMdHeartEmpty } from "react-icons/io";
import { MdOutlineShoppingBag } from "react-icons/md";


const Menu = () => {
    return (
        <nav className='max-w-lg'>
            <ul className='flex flex-wrap gap-4'>
                <li>
                    <MenuItem name='Compare' icon={MdCompare} route='/compare' />
                </li>
                <li>
                    <MenuItem name='Wish list' icon={IoMdHeartEmpty} route='/wishlist' />
                </li>
                <li>
                    <MenuItem name='Cart' icon={MdOutlineShoppingBag} route='/cart' />
                </li>
            </ul>
        </nav>
    );
};

export default Menu;
