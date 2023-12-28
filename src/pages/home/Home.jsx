import { Link } from 'react-router-dom';
import React from 'react';

import HeroBanner from '../../components/blocks/landingPageMane/HeroBanner';


const Home = () => {
    return (
        <div className='text-center flex flex-col gap-20'>
            <h1 className='text-[3.375rem] font-bold'>
                Welcome to Electronics Store
            </h1>
            <HeroBanner />       

        </div>
    );
};

export default Home;