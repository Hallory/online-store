import React from 'react';
import { useLocation } from 'react-router-dom';

const Sidebar = () => {
  const location = useLocation();

  return (
    <div className={`w-80 ${location.pathname === "/login"||location.pathname === "/register" ? "hidden" : "block"}`}>
      Sidebar
    </div>
  );
}

export default Sidebar;
