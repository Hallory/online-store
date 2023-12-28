import React from 'react';

import { SearchIcon } from '@heroicons/react/outline';

const SearchBar = () => {

    return (
        <div className='flex max-w-[43.75rem] w-full max-h-[2.75rem] h-full min-h-[2.75rem] px-3 py-2 ml-6 gap-1 items-center self-stretch bg-black-100 border border-black-200 rounded-md text-black-900 focus-visible:outline-none'>
            <input
                type='text'
                placeholder='Search for products'
                className='grow text-[1.125rem] overflow-hidden text-black-900 leading-[140%] bg-transparent placeholder:text-[0.875rem] placeholder:text-black-700 placeholder:text-ellipsis placeholder:whitespace-nowrap placeholder:font-normal focus-visible:outline-none'
            />
            <button className=''>
                <SearchIcon  className='w-6 h-6 text-black-700' />
            </button>
        </div>
    );
};

export default SearchBar;