import React from 'react';
import { Link } from 'react-router-dom';
import Dropdown from './Dropdown';

const Nav = () => {
    const navMenuData = [
        {
        title: 'About Us',
        route: '/about'
        },
        {
        title: 'Contacts',
        route: '/contacts'
        },
        {
        title: 'FAQ',
        route: '/general-questions'
        }
    ];

        return (
            <nav className='font-medium leading-[100%] flex items-center gap-9'>
                <Dropdown />
                <ul className="flex gap-9">
                    {navMenuData.map((item, index) => (
                        <li className="" key={index}>
                            <Link to={item.route} className="">
                                <span>{item.title}</span>
                            </Link>
                        </li>
                    ))}
                </ul>
            </nav>
        );
    };

export default Nav;
