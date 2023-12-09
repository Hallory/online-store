// Logo.jsx
import React from 'react';
import { Link } from 'react-router-dom/dist';

const Logo = () => {
    return (
        <div className='relative'>
            <Link to='/'>
                ElectroMagic
            </Link>
        </div>
        
    );

};

export default Logo;
