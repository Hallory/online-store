import React from 'react';
import Logo from './Logo';
import SearchBar from './SearchBar';
import Menu from './Menu';

const Header = (props) => {
    return (
        <header className=''>
            <Logo />
            <SearchBar />
            <Menu />
        </header>
    )
}

export default Header;