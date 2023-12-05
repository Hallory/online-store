import { Link } from 'react-router-dom';
import React from 'react';


const Home = ({ products }) => {

    return (
        <div>
            <h2>Home</h2>
            <Link className='text-blue-500' to="/products">View Products</Link>
        </div>
    );
};

export default Home;