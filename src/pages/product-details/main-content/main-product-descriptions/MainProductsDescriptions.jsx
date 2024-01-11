import React from 'react';
import Performance from './all-features/Performance';
import Description from './all-features/Description';
import Features from './all-features/Features';
import Design from './all-features/Design';

const MainProductsDescriptions = ({ product }) => {
    const [mainDetails, setMainDetails] = React.useState('performance');

    const handleMainDetails = () => {
        switch (mainDetails) {
            case 'performance':
                return <Performance product={product} />;
            case 'description':
                return <Description  product={product}/>;
            case 'features':
                return <Features product={product}/>;
            case 'design':
                return <Design product={product}/>;
            default:
                return <Performance product={product}/>;
        }
    };

    return (
        <div className="w-full bg-gray-200 lg:min-h-[25rem] flex flex-col p-10 rounded-md ">
            <div className='w-full flex gap-2 items-center border-b border-gray-300  '>
            <button className={`p-3 rounded-t-md ${mainDetails === 'performance' ? 'bg-primary-300' : ''}`} onClick={() => setMainDetails('performance')}>Performance</button>
            <button className={`p-3 rounded-t-md ${mainDetails === 'features' ? 'bg-primary-300' : ''}`} onClick={() => setMainDetails('features')}>Features</button>
            <button className={`p-3 rounded-t-md ${mainDetails === 'design' ? 'bg-primary-300' : ''}`} onClick={() => setMainDetails('design')}>Design</button>
            <button className={`p-3 rounded-t-md ${mainDetails === 'description' ? 'bg-primary-300' : ''}`} onClick={() => setMainDetails('description')}>Description</button>
            </div>
            
            <div className={`w-full flex bg-gray-100  pl-2 rounded-md flex-col  ${mainDetails==='performance' ? 'rounded-tl-none' : ''}`}>
                {handleMainDetails()}
            </div>
        </div>
    );
};

export default MainProductsDescriptions;
