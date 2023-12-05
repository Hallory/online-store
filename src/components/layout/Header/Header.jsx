import React from 'react';
import Logo from './Logo';
import SearchBar from './SearchBar';
import Menu from './Header_menu/Menu';
import Button from '../../elements/Button';
import HeaderCategories from './Header_сategories';


const Header = (props) => {

    return (
        <header className='flex flex-wrap items-center justify-between py-6'>
            <Logo />
            <SearchBar />
            <Menu />
            <Button
                label='Sign In'
                onClick={() => { console.log('Click!'); }}
                variant=''
            />
            <HeaderCategories />
        </header>
    )
}

export default Header;