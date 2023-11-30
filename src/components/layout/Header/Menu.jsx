import React from 'react';
import { Link } from 'react-router-dom';

const Menu = () => {
    return (
        <nav className=''>
            <ul className=''>
                <li><Link to=''>Compare</Link></li>
                <li><Link to=''>Wish list</Link></li>
                <li><Link to=''>Cart</Link></li>
            </ul>
        </nav>
    );
};

export default Menu;
