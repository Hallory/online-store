import React from 'react';
import Logo from '../../elements/Logo';
import SearchBar from './SearchBar';
import Menu from './Header_menu/Menu';
import Button from '../../elements/Button';
import { Link } from 'react-router-dom';
import Navigation from './Header_nav/Navigation';


const Header = (props) => {

    return (
        <header className='px-6 flex flex-col items-center py-4 gap-y-4 bg-bg_secondary'>
            <div className='flex justify-start items-center w-full'>
                <Logo />
                <Navigation />  
            </div>
            <div className='flex justify-between items-center w-full'>
                <Button
                    label='Categories'
                    onClick={() => { console.log('Click!'); }}
                    variant='primary'/>
                <SearchBar />
                <Menu />
                <Link to='/login'>
                    <Button
                    label='Login'
                    onClick={() => { console.log('Click!'); }}
                    variant='primary-outline'
                    />
                </Link>
            </div>
        </header>
    )
}

export default Header;