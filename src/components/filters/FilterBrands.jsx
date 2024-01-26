import React, { useState, useEffect } from "react";
import { useDispatch, useSelector } from 'react-redux';
import { getProducts } from "../../redux/slices/productsSlice";
import { filteredProducts } from "../../redux/slices/productsSlice";

import { MinusIcon } from '@heroicons/react/solid';
import { PlusIcon } from '@heroicons/react/solid';
import { CheckIcon } from '@heroicons/react/solid';

const FilterBrands = () => {
    const dispatch = useDispatch();
    const { list } = useSelector(state => state.products);

    useEffect(() => {
        dispatch(getProducts());
    }, [dispatch]);

    const brandCounts = {};
    list.forEach(product => {
        brandCounts[product.brand] = (brandCounts[product.brand] || 0) + 1;
    });

    const uniqueBrands = [...new Set(list.map((product) => product.brand))];

    const [isBrandsCollapsed, setBrandsCollapsed] = useState(false);
    const [searchTerm, setSearchTerm] = useState('');
    const [selectedBrands, setSelectedBrands] = useState([]);

    const handleToggleBrands = () => {
        setBrandsCollapsed(!isBrandsCollapsed);
    }

    const handleCheckboxChange = (brand) => {
        const updatedSelectedBrands = selectedBrands.includes(brand)
            ? selectedBrands.filter((selectedBrand) => selectedBrand !== brand)
            : [...selectedBrands, brand];

        setSelectedBrands(updatedSelectedBrands);
        dispatch(filteredProducts(brand));
    }

    const filteredBrands = uniqueBrands.filter((brand) =>
        brand.toLowerCase().includes(searchTerm.toLowerCase())
    );

    return (
        <div className="p-4 border border-gray-150 rounded-md">
            <div className="flex justify-between">
                <h3>Brand</h3>
                <button onClick={handleToggleBrands}>
                    {!isBrandsCollapsed ? (
                        <MinusIcon className="w-6" />
                    ) : (
                        <PlusIcon className="w-6" />
                    )}
                </button>
            </div>
            <div className={isBrandsCollapsed ? "hidden" : "visible"}>
                <div className="my-4">
                    <input
                        className="w-full px-3 py-2 gap-1 items-center self-stretch bg-black-100 border border-black-200 rounded-md text-black-900 focus-visible:outline-none placeholder:text-[0.875rem] placeholder:text-black-700 placeholder:text-ellipsis placeholder:whitespace-nowrap placeholder:font-normal"
                        type="text"
                        placeholder='Search brands...'
                        value={searchTerm}
                        onChange={(e) => setSearchTerm(e.target.value)}
                    />
                </div>
                <div className={`${isBrandsCollapsed ? 'hidden' : 'overflow-y-auto max-h-[10.625rem] min-h-[10.625rem]'} no-scrollbar`}>
                    {filteredBrands.map((brand) => (
                        <div key={brand} className="flex items-center mb-3">
                            <input
                                type="checkbox"
                                id={brand}
                                onChange={() => handleCheckboxChange(brand)}
                                checked={selectedBrands.includes(brand)}
                                className="peer relative appearance-none w-5 h-5 m-0.5 bg-gray-150 border-2 border-primary-400 rounded-sm focus:outline-none checked:bg-primary-400"
                            />
                            <label
                                htmlFor={brand}
                                className={`flex justify-between w-full cursor-pointer relative ms-2 font-medium ${
                                    selectedBrands.includes(brand)
                                        ? "text-white bg-blue-500" // Text and background color when selected
                                        : "text-black-600 hover:text-black-900" // Default text color
                                } dark:text-gray-300 hover:text-white`}
                            >
                                <span>{brand}</span>
                                <span>{brandCounts[brand]}</span>
                                {selectedBrands.includes(brand) && <CheckIcon className="text-black-100 w-5 ml-2 absolute top-[0.125rem] left-[-2.313rem] " />}
                            </label>
                        </div>
                    ))}
                </div>
            </div>
        </div>
    );
}

export default FilterBrands;
