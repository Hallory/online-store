
import React, { useState, useEffect } from 'react';

const Sidebar = ({ products, onFilterChange }) => {
    const [brandsWithCount, setBrandsWithCount] = useState([]);
    const [colorsWithCount, setColorsWithCount] = useState([]);
    const [selectedBrand, setSelectedBrand] = useState('');
    const [selectedColors, setSelectedColors] = useState([]);
    const [priceRange, setPriceRange] = useState({ min: 0, max: 1000 });
    const [minPriceInput, setMinPriceInput] = useState('');
    const [maxPriceInput, setMaxPriceInput] = useState('');
    const [searchBrand, setSearchBrand] = useState('');
    const [searchColor, setSearchColor] = useState('');

    useEffect(() => {
        // Extract unique brands and colors from the products
        const uniqueBrands = [...new Set(products.map((product) => product.brand))];

        // Count the occurrences of each brand and color
        const brandsWithCount = uniqueBrands.map((brand) => ({
            name: brand,
            count: products.filter((product) => product.brand === brand).length,
        }));

        // Determine the most popular colors
        const colorsWithCount = getMostPopularColors(products);

        setBrandsWithCount(brandsWithCount);
        setColorsWithCount(colorsWithCount);
    }, [products]);

    const getMostPopularColors = (products) => {
        const colorCounts = {};
        products.forEach((product) => {
            const color = product.color && product.color.toLowerCase();
            colorCounts[color] = (colorCounts[color] || 0) + 1;
        });

        // Sort colors by count in descending order
        const sortedColors = Object.keys(colorCounts).sort(
            (a, b) => colorCounts[b] - colorCounts[a]
        );

        // Limit to, for example, the top 5 colors
        const mostPopularColors = sortedColors.slice(0, 5);

        return mostPopularColors.map((color) => ({
            name: color,
            count: colorCounts[color],
        }));
    };

    const handleBrandChange = (brand) => {
        setSelectedBrand(brand);
        onFilterChange({ brand, colors: selectedColors, priceRange });
    };

    const handleColorChange = (color) => {
        const updatedColors = selectedColors.includes(color)
            ? selectedColors.filter((selectedColor) => selectedColor !== color)
            : [...selectedColors, color];

        setSelectedColors(updatedColors);
        onFilterChange({ brand: selectedBrand, colors: updatedColors, priceRange });
    };

    const handlePriceChange = (event, newValue) => {
        setPriceRange({ min: newValue[0], max: newValue[1] });
        onFilterChange({ brand: selectedBrand, colors: selectedColors, priceRange: { min: newValue[0], max: newValue[1] } });
    };

    const handleMinPriceInputChange = (event) => {
        setMinPriceInput(event.target.value);
    };

    const handleMaxPriceInputChange = (event) => {
        setMaxPriceInput(event.target.value);
    };

    const handlePriceInputBlur = () => {
        const minPrice = parseFloat(minPriceInput);
        const maxPrice = parseFloat(maxPriceInput);

        if (!isNaN(minPrice) && !isNaN(maxPrice)) {
            setPriceRange({ min: minPrice, max: maxPrice });
            onFilterChange({ brand: selectedBrand, colors: selectedColors, priceRange: { min: minPrice, max: maxPrice } });
        }
    };

    const handleSearchBrandChange = (event) => {
        setSearchBrand(event.target.value);
    };

    const handleSearchColorChange = (event) => {
        setSearchColor(event.target.value);
    };

    return (
        <div className="sidebar">
            <div>
                <h4>Brand</h4>
                <input
                    type="text"
                    placeholder="Search Brand"
                    value={searchBrand}
                    onChange={handleSearchBrandChange}
                    className="mb-2 p-2 border border-gray-300 rounded"
                />
                <div className="max-h-28 overflow-y-auto">
                    {brandsWithCount
                        .filter((brand) =>
                            brand.name.toLowerCase().includes(searchBrand.toLowerCase())
                        )
                        .map((brand) => (
                            <div key={brand.name}>
                                <input
                                    type="checkbox"
                                    id={brand.name}
                                    checked={selectedBrand === brand.name}
                                    onChange={() => handleBrandChange(brand.name)}
                                />
                                <label htmlFor={brand.name}>
                                    {brand.name} ({brand.count})
                                </label>
                            </div>
                        ))}
                </div>
            </div>

            <div>
                <h4>Color</h4>
                <input
                    type="text"
                    placeholder="Search Color"
                    value={searchColor}
                    onChange={handleSearchColorChange}
                    className="mb-2 p-2 border border-gray-300 rounded"
                />
                <div className="max-h-28 overflow-y-auto">
                    {colorsWithCount
                        .filter((color) =>
                            color.name.toLowerCase().includes(searchColor.toLowerCase())
                        )
                        .map((color) => (
                            <div key={color.name}>
                                <input
                                    type="checkbox"
                                    id={color.name}
                                    checked={selectedColors.includes(color.name)}
                                    onChange={() => handleColorChange(color.name)}
                                />
                                <label htmlFor={color.name}>
                                    {color.name} ({color.count})
                                </label>
                            </div>
                        ))}
                </div>
            </div>

            <div>
                <h4>Price</h4>
                <div className="flex items-center">
                    <input
                        type="text"
                        placeholder="Min"
                        value={minPriceInput}
                        onChange={handleMinPriceInputChange}
                        onBlur={handlePriceInputBlur}
                        className="mr-2 p-2 border border-gray-300 rounded"
                    />
                    <span className="mr-2">to</span>
                    <input
                        type="text"
                        placeholder="Max"
                        value={maxPriceInput}
                        onChange={handleMaxPriceInputChange}
                        onBlur={handlePriceInputBlur}
                        className="mr-2 p-2 border border-gray-300 rounded"
                    />
                </div>
                <div>
                    <input
                        type="range"
                        min="0"
                        max="1000"
                        step="1"
                        value={priceRange.min}
                        onChange={(event) =>
                            handlePriceChange(event, [event.target.value, priceRange.max])
                        }
                    />
                </div>
            </div>

            {/* ... (rest of the component remains unchanged) ... */}
        </div>
    );
};


export default Sidebar