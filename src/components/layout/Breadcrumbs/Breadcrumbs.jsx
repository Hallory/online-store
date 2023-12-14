import React from 'react';
import { useLocation } from 'react-router-dom';
import { useDispatch, useSelector } from 'react-redux';
import { sortProducts } from "../../../redux/slices/productsSlice";
import PathName from '../../elements/pathname-segment/PathName';

const Breadcrumbs = () => {
    const dispatch = useDispatch();

    const location = useLocation();
    const paths = ['/products', '/products/:productId'];

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
            <div className='flex justify-between mt-4 px-6 pb-6 border-b border-solid border-b-brd '>
                <div className='flex flex-col'>
                    <div className=''>
                        <PathName />
                    </div>
                    <h3 className='text-black text-[1.5rem] font-bold'>
                        Marketplace Products
                    </h3>
                </div>
                <div className=''>
                    {location.pathname !== '/products' ? (
                        <div>Product code: { }</div>
                    ) : (
                        <div className='flex justify-center items-center py-1 pl-3 gap-1 border border-brd rounded-sm text-[0.875rem] leading-[140%]'>
                            <label htmlFor="sortDropdown" className='text-clr_txt'>
                                Sort by
                            </label>
                            <select onChange={(e) => dispatch(sortProducts(e.target.value))} className='text-black focus:outline-none' id="sortDropdown">
                                <option value="featured">Featured</option>
                                <option value="expensive">Most Expensive</option>
                                <option value="cheapest">Cheapest</option>
                                <option value="rating">By Rating</option>
                                <option value="new">New Products</option>
                            </select>
                        </div>
                    )}
                </div>
            </div>
        </div>
    );
};

export default Breadcrumbs;
