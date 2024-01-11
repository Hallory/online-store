import React from 'react';
import Nav from './Nav/Nav';
import Sidebar from './Sidebar/Sidebar';

const Layout = ({children}) => {
    return (
        <div>
            <Nav/>
            <Sidebar/>
            <main>
                {children}
            </main>
        </div>
    );
};

export default Layout;