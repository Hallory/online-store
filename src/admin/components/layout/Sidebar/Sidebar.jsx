import React from 'react';
import { IconList, IconLayoutGrid } from '@tabler/icons-react';
import { FunnelIcon, BarsArrowUpIcon } from '@heroicons/react/24/solid';
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
            <div className="search flex gap-2">
                <input
                    type="text"
                    placeholder="Search"
                    className="max-w-[12.5rem] p-2 border-2 border-gray-200 rounded-lg"
                />
                <button>
                <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" strokeWidth={1.5} stroke="currentColor" className="w-6 h-6">
  <path strokeLinecap="round" strokeLinejoin="round" d="M12 3c2.755 0 5.455.232 8.083.678.533.09.917.556.917 1.096v1.044a2.25 2.25 0 0 1-.659 1.591l-5.432 5.432a2.25 2.25 0 0 0-.659 1.591v2.927a2.25 2.25 0 0 1-1.244 2.013L9.75 21v-6.568a2.25 2.25 0 0 0-.659-1.591L3.659 7.409A2.25 2.25 0 0 1 3 5.818V4.774c0-.54.384-1.006.917-1.096A48.32 48.32 0 0 1 12 3Z" />
</svg>

                </button>
                <button>
                    <svg
                        xmlns="http://www.w3.org/2000/svg"
                        fill="none"
                        viewBox="0 0 24 24"
                        strokeWidth={1.5}
                        stroke="currentColor"
                        className="w-6 h-6"
                    >
                        <path
                            strokeLinecap="round"
                            strokeLinejoin="round"
                            d="M3 4.5h14.25M3 9h9.75M3 13.5h5.25m5.25-.75L17.25 9m0 0L21 12.75M17.25 9v12"
                        />
                    </svg>
                </button>
            </div>
        </div>
    );
};

export default Sidebar;
