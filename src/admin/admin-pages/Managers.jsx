import React from 'react';
import ManagersList from '../components/managers/managers-list/ManagersList';

const Managers = () => {
    return (
        <div className='flex w-full h-full overflow-y-auto'>
            <ManagersList/>
        </div>
    );
};

export default Managers;