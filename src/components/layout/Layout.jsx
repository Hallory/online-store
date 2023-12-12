import React from 'react';
import Header from './Header/Header';
import Footer from './Footer/Footer';
import Sidebar from './Sidebar/Sidebar';

const Layout = ({ children }) => {

    return (
        <div className='flex flex-col max-w-[90rem] min-h-screen mx-auto'>
            <Header />
            <div className='flex-1 flex'>
                <Sidebar />
                <main className='flex-1 overflow-x-hidden overflow-y-auto'>
                    <div className='container mx-auto px-4 py-6'>
                        {children}
                    </div>
                </main>
            </div>
            <Footer className='bg-gray-800 text-white py-4 text-center'/>
        </div>
    )
}

export default Layout;
