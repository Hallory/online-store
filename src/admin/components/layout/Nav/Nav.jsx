import React from 'react';
import { NavLink } from 'react-router-dom';

const Nav = () => {
    return (
        <div className="bg-gray-200 text-gray-800 w-[15%] p-4 flex flex-col gap-10 h-full">
            <div className="flex flex-col gap-4">
                <p>Tasks</p>
                <NavLink className='admin_link' to="/admin/orders">Order</NavLink>
                <NavLink className='admin_link' to="/admin/calendar">Calendar</NavLink>
                <NavLink className='admin_link' to="/admin/categories">Categories</NavLink>
                <NavLink className='admin_link' to="/admin/managers">Managers</NavLink>
            </div>
            <div>
                <p>Analytics</p>
            </div>
        </div>
    );
};

export default Nav;
