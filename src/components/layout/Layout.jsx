import React from 'react';
import Header from './Header/Header';
import Footer from './Footer/Footer';
import Sidebar from './Sidebar/Sidebar';
import Breadcrumbs from './Breadcrumbs/Breadcrumbs';

const Layout = ({ children }) => {

    return (
        <div className='flex flex-col min-h-screen mx-auto'>
            <Header />
            <Breadcrumbs />
            <div className='flex-1 flex'>
                <Sidebar />
                <main className='flex-1 overflow-x-hidden overflow-y-auto'>
                    <div className='container mx-auto px-4'>
                        {children}
                    </div>
                </main>
            </div>
            <Footer className='bg-gray-800 text-white py-4 text-center'/>
        </div>
    )
}

export default Layout;
