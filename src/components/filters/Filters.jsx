import React, { useState, useEffect} from "react";
import { useDispatch, useSelector } from 'react-redux';
import { getProducts } from "../../redux/slices/productsSlice";

const Filters = () => {
    const dispatch = useDispatch();
    const { list } = useSelector(state => state.products);
    
    useEffect(()=>{
        dispatch(getProducts());

    },[dispatch]);

    const uniqueBrands = [...new Set(list.map((product) => product.brand))];
    console.log(uniqueBrands);

    const [selectedBrands, setSelectedBrands] = useState([]);
    console.log(selectedBrands);

    const handleBrandChange = (brand) => {

        if (selectedBrands.includes(brand)) {
            setSelectedBrands(selectedBrands.filter((selectedBrand) => selectedBrand !== brand));

        } else {

            setSelectedBrands([...selectedBrands, brand]);

        }

        dispatch(getProducts(selectedBrands));

    };

    return (
        <div>
            {uniqueBrands.map((brand) => (
                <div key={brand}>
                    <input
                        type="checkbox"
                        id={brand}
                        checked={selectedBrands.includes(brand)}
                        onChange={() => handleBrandChange(brand)}
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