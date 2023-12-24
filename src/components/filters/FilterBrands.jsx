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

    const [isBrandsCollapsed, setBrandsCollapsed] = useState(false);

    const handleToggleBrands = () => {
        setBrandsCollapsed(!isBrandsCollapsed);
    }

    return (
        <div className="p-4">
            {/* Filtering by brands */}
            <div className="">
                <div className="flex justify-between">
                    <h3>Brand</h3>
                    <button onClick={handleToggleBrands}>V</button>
                </div>
                <div>
                    <input type="text" />
                </div>
                <div className={isBrandsCollapsed ? "hidden" : "max-h-[275px] overflow-y-auto p-4"}>
                    {uniqueBrands.map((brand) => (
                        <div key={brand} className="flex items-center">
                            <input
                                type="checkbox"
                                id={brand}
                            onChange={() => dispatch(filteredProducts(brand))}
                                className="w-6 h-6 text-blue-600 bg-gray-100 border-gray-300 rounded focus:ring-blue-500 dark:focus:ring-blue-600 dark:ring-offset-gray-800 focus:ring-2 dark:bg-gray-700 dark:border-gray-600"
                            />
                            <label htmlFor={brand} className="cursor-pointer ms-2 text-sm font-medium text-gray-900 dark:text-gray-300">
                                {brand} {brandCounts[brand]}
                            </label>
                        </div>
                    ))}
                </div>
            </div>
        </div>
    );
}

export default Filters;