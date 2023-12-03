import React from 'react';
import Logo from './Logo';
import SearchBar from './SearchBar';
import Menu from './Menu';

const Header = (props) => {
    return (
        <header className='flex absolute min-h-[70px] z-40'>
            <Logo />
            <SearchBar />
            <Menu />
        </header>
    )
}

export default Header;