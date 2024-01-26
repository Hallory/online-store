import React from 'react';
import { IconList, IconLayoutGrid } from '@tabler/icons-react';
import { FunnelIcon, BarsArrowUpIcon } from '@heroicons/react/24/solid';
import Tasks from './tasks-bar/Tasks';
import Searchbar from './search-bar/Searchbar';
const Sidebar = () => {
    return (
        <div className="max-h-1/6 justify-between border-b flex border-gray-200 p-4">
            <div className="buttons-container rounded-lg flex max-w-[10rem] border-2 max-h-[5rem]">
                <button className="w-full rounded-l-lg p-2 gap-2 flex">
                    <IconLayoutGrid /> Grid
                </button>
                <button className="w-full rounded-r-lg p-2 gap-2 flex">
                    <IconList /> List
                </button>
            </div>
            <div className="search flex content-between w-[40%] gap-2">
                <Searchbar/>
            <Tasks/>
            </div>
        </div>
    );
};

export default Sidebar;
