import React, { useState, useEffect} from "react";
import { useDispatch, useSelector } from 'react-redux';
import { getProducts } from "../../redux/slices/productsSlice";
import { filteredProducts } from "../../redux/slices/productsSlice";

const Filters = () => {
    const dispatch = useDispatch();
    const { list } = useSelector(state => state.products);
    
    useEffect(()=>{
        dispatch(getProducts());

    }, [dispatch]);
    
    const brandCounts = {};
    list.forEach(product => {
        brandCounts[product.brand] = (brandCounts[product.brand] || 0) + 1;
    });

    const uniqueBrands = [...new Set(list.map((product) => product.brand))];
    console.log(uniqueBrands);

    return (
        <div className="p-4">
            {/* Filtering by brands */}
            <div className="">
                <div className="flex justify-between">
                    <h3>Brand</h3>
                    <button>V</button>
                </div>
                <div>
                    <input type="text" />
                </div>
                <div className="max-h-[275px] overflow-y-auto">
                    {uniqueBrands.map((brand) => (
                        <div key={brand} className="flex items-center">
                            <input
                                type="checkbox"
                                id={brand}
                            onChange={() => dispatch(filteredProducts(brand))}
                                className="mr-2"
                            />
                            <label htmlFor={brand} className="cursor-pointer">
                                {brand} {brandCounts[brand]}
                            </label>
                        </div>
                    ))}
                </div>
            </div>
            <div>
                Filter by price
                {/* Filtering by price */}
            </div>
        </div>
    );
}

export default Filters;