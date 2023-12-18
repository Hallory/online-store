import React, { useState, useEffect} from "react";
import ReactSlider from 'react-slider';
import { useDispatch, useSelector } from 'react-redux';
import { getProducts } from "../../redux/slices/productsSlice";

const FilterPrices = () => {

    const dispatch = useDispatch();
    const { list } = useSelector(state => state.products);

    
    useEffect(()=>{
        dispatch(getProducts());

    }, [dispatch]);
    
    const priceCounts = [];
    list.forEach(product => {
        priceCounts.push(product.price);
    });

    
    
    console.log(priceCounts);
    


    const MIN = 100;
    const MAX = 12000;

    const [values, setValues] = useState([MIN, MAX]);
    const [minInputValue, setMinInputValue] = useState(MIN.toString());
    const [maxInputValue, setMaxInputValue] = useState(MAX.toString());

    const handleMinInputChange = (e) => {
        const newValue = parseInt(e.target.value, 10);
        if (!isNaN(newValue) && newValue >= MIN && newValue <= values[1]) {
            setMinInputValue(newValue.toString());
            setValues([newValue, values[1]]);
        }
    };

    const handleMaxInputChange = (e) => {
        const newValue = parseInt(e.target.value, 10);
        if (!isNaN(newValue) && newValue <= MAX && newValue >= values[0]) {
            setMaxInputValue(newValue.toString());
            setValues([values[0], newValue]);
        }
    };

    const handleSliderChange = (newValue) => {
        setValues(newValue);
        setMinInputValue(newValue[0].toString());
        setMaxInputValue(newValue[1].toString());
    };

    return (
        <div className="p-4">
            <div className="mb-4">
                Filter by price
                <div className="text-lg font-semibold">{`$${values[0]} - $${values[1]}`}</div>
            </div>
            <ReactSlider
                className="horizontal-slider"
                thumbClassName="example-thumb"
                trackClassName="example-track"
                defaultValue={values}
                onChange={handleSliderChange}
                value={values}
                min={MIN}
                max={MAX}
                ariaLabel={['Lower thumb', 'Upper thumb']}
                ariaValuetext={(state) => `Thumb value ${state.valueNow}`}
                renderThumb={(props, state) => <div {...props}>{state.valueNow}</div>}
                pearling
                minDistance={10}
            />
            <div className="flex justify-between mt-2">
                <input
                    className="border border-gray-300 p-2 w-1/2"
                    value={minInputValue}
                    onChange={handleMinInputChange}
                    type="text"
                />
                <input
                    className="border border-gray-300 p-2 w-1/2"
                    value={maxInputValue}
                    onChange={handleMaxInputChange}
                    type="text"
                />
            </div>
        </div>
    );
};

export default FilterPrices;
