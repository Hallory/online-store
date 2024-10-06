import React from 'react';
import { Link } from 'react-router-dom';

const MenuItem = ({ name, icon: Icon, route }) => {
    return (
        <Link to={route} className="flex px-4 py-2 items-center gap-2 font-medium leading-[100%] tracking-[0.031rem]">
            <Icon className="w-5 h-5" />
            <span>{name}</span>
        </Link>
    )
}

export default MenuItem;