import React from 'react';
import { Link } from 'react-router-dom';

const MenuItem = ({ name, icon: Icon, route }) => {
    return (
        <Link to={route} className="hover:text-gray-300 flex items-center space-x-2">
            <Icon className="w-6 h-6" />
            <span>{name}</span>
        </Link>
    )
}

export default MenuItem;