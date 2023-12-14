import React, { useState, useEffect} from "react";
import { useDispatch, useSelector } from 'react-redux';
import { getProducts } from "../../redux/slices/productsSlice";
import { filteredProducts } from "../../redux/slices/productsSlice";

const Filters = () => {
    const dispatch = useDispatch();
    const { list } = useSelector(state => state.products);
    
    useEffect(()=>{
        dispatch(getProducts());

    },[dispatch]);

    const uniqueBrands = [...new Set(list.map((product) => product.brand))];
    console.log(uniqueBrands);

    return (
        <div>
            {uniqueBrands.map((brand) => (
                <div key={brand}>
                    <input
                        type="checkbox"
                        id={brand}
                        onChange={() => dispatch(filteredProducts(brand))}
                    />
                    <label htmlFor="{brand}">
                        {brand}
                    </label>
                </div>
            ))}
        </div>
    );
}

export default Filters;