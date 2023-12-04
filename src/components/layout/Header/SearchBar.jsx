import React from 'react';
import { IoSearchSharp } from "react-icons/io5";

const SearchBar = () => {
    return (
        <div className='relative max-w-xl w-full'>
            <input
                type='text'
                placeholder='I search...'
                className='w-full py-2 px-4 border border-gray-300 rounded-md shadow-sm focus:outline-none focus:ring-2 focus:ring-blue-500 focus:border-blue-500'
            />
            <button className='absolute inset-y-0 right-0 flex items-center px-4 text-gray-700 bg-gray-100 border border-gray-300 rounded-r-md hover:bg-gray-200'>
                <IoSearchSharp className='w-6 h-6' />
            </button>
        </div>
    );
};

export default SearchBar;