import React from 'react';
import Nav from './Nav/Nav';
import Sidebar from './Sidebar/Sidebar';

const Layout = ({ children }) => {
  return (
    <div className="flex h-screen">
      <Nav />
      <div className="flex flex-col flex-grow">
        <Sidebar />
        <main className="p-4">{children}</main>
      </div>
    </div>
  );
};

export default Layout;
