import React, { useState, useEffect } from "react";
import { useDispatch, useSelector } from 'react-redux';
import { getProducts } from "../../redux/slices/productsSlice";
import { filteredProducts } from "../../redux/slices/productsSlice";

import { MinusIcon } from '@heroicons/react/solid';
import { PlusIcon } from '@heroicons/react/solid';

const FilterColors = () => {
    const dispatch = useDispatch();
    const { list } = useSelector(state => state.products);

    useEffect(() => {
        dispatch(getProducts());
    }, [dispatch]);

    const brandCounts = {};
    list.forEach(product => {
        brandCounts[product.brand] = (brandCounts[product.brand] || 0) + 1;
    });

    const [isBrandsCollapsed, setBrandsCollapsed] = useState(false);
    const [uniqueBrands, setUniqueBrands] = useState([]);

    const handleToggleBrands = () => {
        setBrandsCollapsed(!isBrandsCollapsed);
    }

    return (
        <div className="p-4 border border-gray-150 rounded-md">
            <div className="flex justify-between">
                <h3 className="font-medium">Color</h3>
                <button onClick={handleToggleBrands}>
                    {!isBrandsCollapsed ? (
                        <MinusIcon className="w-6"/>
                    ) : (
                        <PlusIcon className="w-6"/>
                    )}
                </button>
            </div>
            <div className={isBrandsCollapsed ? "hidden" : "visible"}>
                <div className={`${isBrandsCollapsed ? 'hidden' : 'overflow-y-auto max-h-[170px]'} no-scrollbar`}>
                    {uniqueBrands.map((brand) => (
                        <div key={brand} className="flex items-center mb-3">
                            <input
                                type="checkbox"
                                id={brand}
                                onChange={() => dispatch(filteredProducts(brand))}
                                className="w-6 h-6 text-blue-600 bg-gray-100 border-gray-300 rounded focus:ring-blue-500 dark:focus:ring-blue-600 dark:ring-offset-gray-800 focus:ring-2 dark:bg-gray-700 dark:border-gray-600"
                            />
                            <label htmlFor={brand} className="cursor-pointer ms-2 font-medium text-black-600 dark:text-gray-300">
                                {brand} {brandCounts[brand]}
                            </label>
                        </div>
                    ))}
                </div>
            </div>
        </div>
    );
}

export default FilterColors;
