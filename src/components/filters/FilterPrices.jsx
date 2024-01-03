import React, { useState, useEffect } from "react";
import { useDispatch, useSelector } from 'react-redux';
import { getProducts, filteringByPrice } from "../../redux/slices/productsSlice";
import Slider from 'rc-slider';
import 'rc-slider/assets/index.css';
import './FilterPrices.css';

const createSliderWithTooltip = Slider.createSliderWithTooltip;
const Range = createSliderWithTooltip(Slider.Range);

const FilterPrices = () => {
    const dispatch = useDispatch();
    const { list } = useSelector(state => state.products);

    useEffect(() => {
        dispatch(getProducts());
    }, [dispatch]);

    const prices = [...new Set(list.map(product => product.price))];
    console.log('prices', prices);

    const MIN = Math.min(...prices);
    const MAX = Math.max(...prices);

    const [values, setValues] = useState([MIN, MAX]);
    const [highlightedInput, setHighlightedInput] = useState(null);

    const handleSliderChange = (newValues) => {
        setValues(newValues);
        dispatch(filteringByPrice(newValues)); 
    };

    const handleInputChange = (index, e) => {
        const newValue = e.target.value;
        const newValues = [...values];

        if (newValue === '' || (!isNaN(newValue) && newValue >= MIN && newValue <= MAX)) {
            newValues[index] = newValue === '' ? MIN : parseInt(newValue, 10);
            setValues(newValues);
            dispatch(filteringByPrice(newValues)); 
        } else {
            setHighlightedInput(index);
            setTimeout(() => {
                setHighlightedInput(null);
                newValues[index] = index === 0 ? MIN : MAX;
                setValues(newValues);
                dispatch(filteringByPrice(newValues)); 
            }, 2000);
        }
    };

    return (
        <div className="p-4 border border-gray-150 rounded-md">
            <h3 className="pb-6">Filter by price</h3>
            <div className="mb-4 mt-6">
                <Range
                    value={values}
                    min={MIN}
                    max={MAX}
                    onChange={handleSliderChange}
                    range
                    tipFormatter={(value) => `${value}`}
                    tipProps={{
                        placement: 'top',
                        prefixCls: 'tooltip-prefix', 
                        visible: true, 
                    }}
                    
                />
            </div>
            <div className="flex gap-4">
                <input
                    value={values[0]}
                    className="w-[50%] rounded-sm border border-gray-150 py-4 px-3"
                    type="text"
                    onChange={(e) => handleInputChange(0, e)}
                />
                <input
                    value={values[1]}
                    className="w-[50%] rounded-sm border border-gray-150 py-4 px-3"
                    type="text"
                    onChange={(e) => handleInputChange(1, e)}
                />
            </div>
        </div>
    );
};

export default FilterPrices;
