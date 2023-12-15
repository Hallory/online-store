import React from 'react';
import Filter from '../../filters/Filters';
import { useLocation } from 'react-router-dom';


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
        <div className={`${isHidden ? 'hidden' : 'block'} pl-4`}>
            <Filter/>

        </div>
    )
};


export default Sidebar