import React from 'react';
import Header from './Header/Header';
import Footer from './Footer/Footer';
import Sidebar from './Sidebar/Sidebar';

const Layout = ({ children }) => {
    return (
        <div className=''>
            <Header />
            <Sidebar />
            <main className=''>{children}</main>
            <Footer />
        </div>
    )
}

export default Layout;
