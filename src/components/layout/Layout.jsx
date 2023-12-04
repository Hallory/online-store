import React from 'react';
import Header from './Header/Header';
import Footer from './Footer/Footer';
import Sidebar from './Sidebar/Sidebar';

const Layout = ({ children }) => {
    return (
        <div className='flex flex-col min-h-screen px-4'>
            <Header />
            <div className='flex-1 flex'>
                <Sidebar />
                <main className='flex-1 overflow-x-hidden overflow-y-auto'>
                    <div className='container mx-auto px-4 py-6'>
                        {children}
                    </div>
                </main>
            </div>
            <Footer />
        </div>
    )
}

export default Layout;
