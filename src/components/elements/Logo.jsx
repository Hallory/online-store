// Logo.jsx
import React from 'react';
import { Link } from 'react-router-dom/dist';

const Logo = () => {
    return (
        <div className='pr-12'>
            <Link to='/'>
                <span className='text-[2rem] font-bold leading-[110%] py-1'>
                    ElectroMagic
                </span>
            </Link>
        </div>
        
    );

};

export default Logo;
