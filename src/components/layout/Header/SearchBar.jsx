import React from 'react';
import { IoSearchOutline } from "react-icons/io5";

const SearchBar = () => {

    

    return (
        <div className='flex max-w-[43.75rem] w-full max-h-[2.75rem] h-full min-h-[2.75rem] px-3 py-2 ml-6 gap-1 items-center self-stretch bg-default border border-brd rounded-md text-black focus-visible:outline-none'>
            <input
                type='text'
                placeholder='Search for products'
                className='grow text-[1.125rem] overflow-hidden text-black leading-[140%] bg-transparent placeholder:text-[0.875rem] placeholder:text-placeholder placeholder:text-ellipsis placeholder:whitespace-nowrap placeholder:font-normal focus-visible:outline-none'
            />
            <button className=''>
                <IoSearchOutline  className='w-6 h-6 text-placeholder' />
            </button>
        </div>
    );
};

export default SearchBar;