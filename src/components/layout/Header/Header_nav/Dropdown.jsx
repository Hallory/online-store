import React, { useState } from 'react';

const Dropdown = () => {
    const [isDropdownOpen, setDropdownOpen] = useState(false);

    const toggleDropdown = () => {
        setDropdownOpen(!isDropdownOpen);
    };

        const handleBuyersClick = () => {
        console.log('Buyers clicked!');
    };

    return (
        <div className='relative'>
            <div
                className="flex font-medium leading-[100%] cursor-pointer px-4 py-2"
                onClick={toggleDropdown}>
                <span className='pr-2 tracking-[0.031rem]'>
                    For buyers
                </span>
                    <span>
                        {isDropdownOpen ? (
                            <svg
                                width="20"
                                height="20"
                                viewBox="0 0 24 24"
                                fill="none"
                                xmlns="http://www.w3.org/2000/svg">
                                <path d="M5 15L12 8L19 15" stroke="#080817" stroke-width="2" stroke-linecap="round" stroke-linejoin="round"/>
                            </svg>
                        ) : (
                            <svg
                                width="20"
                                height="20"
                                viewBox="0 0 24 24"
                                fill="none"
                                xmlns="http://www.w3.org/2000/svg">
                                <path d="M19 9L12 16L5 9" stroke="#080817" stroke-width="2" stroke-linecap="round" stroke-linejoin="round"/>
                            </svg>
                        )}
                    </span>
                {isDropdownOpen && (
                <div className='absolute bg-default top-full mt-1 shadow-md z-10'>
                        <div className="p-2 cursor-pointer" onClick={handleBuyersClick}>
                            <span>Buyer Option 1</span>
                        </div>
                        <div className="p-2 cursor-pointer" onClick={handleBuyersClick}>
                            <span>Buyer Option 2</span>
                        </div>
                </div>
            )}
            </div>
        </div>
    );
};

export default Dropdown;
