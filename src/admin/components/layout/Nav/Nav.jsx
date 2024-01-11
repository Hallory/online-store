import React from 'react';
import { Link } from 'react-router-dom';

const Nav = () => {
    return (
        <div className="nav flex flex-col w-1/5">
            <Link to="/admin/orders">Oder</Link>
            <Link to="/admin/managers">Managers</Link>
            
        </div>
    );
};

export default Nav;
