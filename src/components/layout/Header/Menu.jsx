import React from 'react';
import { Link } from 'react-router-dom';

const Menu = () => {
    return (
        <nav className='relative'>
            <ul className='flex'>
                <li><Link to=''>Compare</Link></li>
                <li><Link to=''>Wish list</Link></li>
                <li><Link to=''>Cart</Link></li>
            </ul>
        </nav>
    );
};

export default Menu;
