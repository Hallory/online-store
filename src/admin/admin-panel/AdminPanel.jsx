import React from 'react';
import { Route, Routes } from 'react-router-dom';
import Orders from '../admin-pages/Orders';
import Managers from '../admin-pages/Managers';
import Layout from '../components/layout/Layout';

const AdminPanel = () => {
    return (
        <div>
            <Layout>
                <Routes>
            <Route path="/" element={<Orders/>}/>
            <Route path="/users" element={<Managers/>}/>
           </Routes>
            </Layout>
           
        </div>
    );
};

export default AdminPanel;