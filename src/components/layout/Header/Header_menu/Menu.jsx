import React from 'react';
import MenuItem from './MenuItem';

import { ScaleIcon } from '@heroicons/react/solid';
import { HeartIcon } from '@heroicons/react/outline';
import { ShoppingCartIcon } from '@heroicons/react/outline';

const Menu = () => {
    return (
        <nav className='max-w-lg'>
            <ul className='flex flex-wrap'>
                <li>
                    <MenuItem name='Compare' icon={ScaleIcon} route='/compare' />
                </li>
                <li>
                    <MenuItem name='Wish list' icon={HeartIcon} route='/wishlist' />
                </li>
                <li>
                    <MenuItem name='Cart' icon={ShoppingCartIcon} route='/cart' />
                </li>
            </ul>
        </nav>
    );
};

export default Menu;
