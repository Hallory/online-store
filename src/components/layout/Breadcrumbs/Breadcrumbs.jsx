import React from 'react';
import { Link, useLocation } from 'react-router-dom';
import { useDispatch } from 'react-redux';
import { sortProducts } from "../../../redux/slices/productsSlice";
import { ChevronRightIcon } from '@heroicons/react/solid';
import PathName from '../../elements/pathname-segment/PathName';

const Breadcrumbs = () => {
    const dispatch = useDispatch();

    const location = useLocation();
    const paths = ['/products', '/products/:productId', '/compare'];

    const isHidden = paths.some((path) => {
        if (path.includes(':')) {
            const regex = new RegExp(`^${path.replace(/:[^/]+/g, '[^/]+')}$`);
            return regex.test(location.pathname);
        } else {
            return location.pathname === path;
        }
    });

    return (
        <div className={isHidden ? 'block' : 'hidden'}>
            <div className='flex justify-between mt-4 mb-4 px-6 pb-6 border-b border-solid border-b-black-200 '>
                <div className='flex flex-col'>
                    <div className='flex gap-2 text-sm'>
                        <Link to='/'>Home</Link>
                        <ChevronRightIcon className='w-4 h-4'/>    
                        <PathName />

                    </div>
                    <h3 className='text-black-900 text-[1.5rem] font-bold'>
                        Marketplace Products
                    </h3>
                </div>
                <div className=''>
                    {location.pathname !== '/products' ? (
                        <div>Product code: { }</div>
                    ) : (
                        <div className='flex justify-center items-center py-1 pl-3 gap-1 border border-black-200 rounded-sm text-[0.875rem] leading-[140%]'>
                            <label htmlFor="sortDropdown" className='text-black-600 '>
                                Sort by
                            </label>
                            <select onChange={(e) => dispatch(sortProducts(e.target.value))} className='text-black-900 focus:outline-none ' id="sortDropdown">
                                <option selected value="featured">Featured</option>
                                <option value="cheapest">From Cheap to Expensive</option>
                                <option value="expensive">From Expensive to Cheap</option>
                                <option value="rating">By rating</option>
                                <option value="newest">Newest first</option>
                            </select>
                        </div>
                    )}
                </div>
            </div>
        </div>
    );
};

export default Breadcrumbs;
