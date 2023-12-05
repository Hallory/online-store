import React from 'react';
import Logo from './Logo';
import SearchBar from './SearchBar';
import Menu from './Header_menu/Menu';
import Button from '../../elements/Button';
import { Link } from 'react-router-dom';


const Header = (props) => {

  
    

    return (
        <header className={`flex items-center justify-between space-x-4 py-6`}>
            <Logo />
            <SearchBar />
            <Menu />
            <Link to='/login'>
            <Button
                label='Sign In'
                onClick={() => { console.log('Click!'); }}
                variant=''
            >
            </Button></Link>
        </header>
    )
}

export default Header;