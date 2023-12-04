// Button.js
import React from 'react';

const Button = ({ onClick, icon: Icon, label, variant = 'primary' }) => {
    const getVariantStyles = () => {
        switch (variant) {
            case 'primary':
                return '';
            case 'secondary':
                return '';
            default:
                return 'border border-gray-300 rounded-md shadow-sm hover:bg-blue-600 ';
        }
    };

    return (
        <button
            onClick={onClick}
            className={`px-4 py-2 rounded-md focus:outline-none ${getVariantStyles()}`}
        >
            {Icon && <Icon className="inline-block mr-2" />}
            <span>{label}</span>
        </button>
    );
};

export default Button;



