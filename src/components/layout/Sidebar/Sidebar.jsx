import React from 'react';
import { useLocation } from 'react-router-dom';
import FilterBrands from '../../filters/FilterBrands';
import FilterPrices from '../../filters/FilterPrices';


const Sidebar = ({ products }) => {

    const location = useLocation();
    const paths = ['/', '/login', '/register', '/products/:productId'];
    const isHidden = paths.some((path) => {
        if (path.includes(':')) {
            const regex = new RegExp(`^${path.replace(/:[^/]+/g, '[^/]+')}$`);
            return regex.test(location.pathname);
        } else {
            return location.pathname === path;
        }
    });

    return (
        <div className={`${isHidden ? 'hidden' : 'block'} pl-4 max-w-[345px] w-full`}>
            <FilterBrands/>
            <FilterPrices />    
        </div>
    )
};


export default Sidebar