// Button.js
import React from 'react';

const Button = ({ onClick, icon: Icon, label, variant = 'primary' }) => {
    const getVariantStyles = () => {
        switch (variant) {
            case 'primary':
                return '';
            case 'secondary':
                return '';
            case 'icon':
                return 'px-3 py-3 border border-gray-300 rounded-md shadow-sm hover:bg-blue-600';
            default:
                return 'px-10 py-3 border border-gray-300 rounded-md shadow-sm hover:bg-blue-600';
        }
    };

    return (
        <button
            onClick={onClick}
            className={`rounded-md focus:outline-none ${getVariantStyles()}`}
        >
            <span>{Icon && <Icon className="inline-block w-5 h-5" />}</span>
            <span>{label}</span>
        </button>
    );
};

export default Button;



