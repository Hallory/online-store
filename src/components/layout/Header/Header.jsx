import React from 'react';
import Logo from './Logo';
import SearchBar from './SearchBar';
import Menu from './Header_menu/Menu';
import Button from '../../elements/Button';


const Header = (props) => {


    return (
        <header className='flex items-center justify-between space-x-4 py-6'>
            <Logo />
            <SearchBar />
            <Menu />
            <Button
                label='Sign In'
                onClick={() => { console.log('Click!'); }}
                variant=''
            />
        </header>
    )
}

export default Header;