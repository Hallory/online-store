import React from 'react';
import { Route, Routes } from 'react-router-dom';
import Orders from '../admin-pages/Orders';
import Managers from '../admin-pages/Managers';
import Layout from '../components/layout/Layout';

const AdminPanel = () => {
  return (
    <Layout>
      <Routes>
        <Route path="/orders" element={<Orders />} />
        <Route path="/managers" element={<Managers />} />
      </Routes>
    </Layout>
  );
};

export default AdminPanel;
