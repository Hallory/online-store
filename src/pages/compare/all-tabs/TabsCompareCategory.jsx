import React, { useState, useEffect } from 'react';
import ComparePage from './ComparePage';

const TabsCompareCategory = () => {
    const [selectedCategory, setSelectedCategory] = useState('');
    const [storedCompareList, setStoredCompareList] = useState({});
    const [displayKeys, setDisplayKeys] = useState(false);

    useEffect(() => {
        const storedList = JSON.parse(localStorage.getItem('compareList')) || {};
        setStoredCompareList(storedList);

        const categories = Object.keys(storedList);
        if (categories.length > 0) {
            setSelectedCategory(categories[0]);
        }
    }, []); 

    const handleMainDetails = () => {
    if (!selectedCategory || !storedCompareList[selectedCategory]) {
        return null;
    }

    const keysArray = Object.keys(storedCompareList[selectedCategory][0]);

    if (keysArray.length === 0) {
        return null;
    }

    return storedCompareList[selectedCategory].map((product, index) => (
        <div key={product.id} className='flex'>
            <div>
                {index === 0 && (
                    <div className='h-[235px]'>
                        Performance
                    </div>
                )}
                <div>
                    {!displayKeys && index === 0 && (
                        <ul>
                            {keysArray.map((key) => (
                                <li
                                    className='px-6 py-4 '
                                    key={key}>
                                    {key}
                                </li>
                            ))}
                        </ul>
                    )}
                </div>
            </div>

            <div>
                <ComparePage product={product} />
            </div>
        </div>
    ));
};


    return (
        <div className="w-full bg-gray-200 flex flex-col p-10 rounded-md">
            <div>
                {Object.keys(storedCompareList).map((category) => (
                    <button
                        key={category}
                        className={`p-3 rounded-t-md ${selectedCategory === category ? 'bg-primary-300' : ''}`}
                        onClick={() => {
                            setSelectedCategory(category);
                            setDisplayKeys(false); 
                        }}
                    >
                        {category[0].toUpperCase() + category.slice(1)}
                    </button>
                ))}
            </div>
            <div className={`w-full flex bg-gray-100 pl-2 rounded-md ${selectedCategory === 'rounded-tl-none'}`}>
                {handleMainDetails()}
            </div>
        </div>
    );
};

export default TabsCompareCategory;
