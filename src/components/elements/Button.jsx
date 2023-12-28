// Button.js
import React from 'react';

const Button = ({ onClick, icon: Icon, label, variant = 'primary' }) => {
    const getVariantStyles = () => {
        switch (variant) {
            case 'primary':
                return 'px-6 py-3 bg-primary-500 text-black-100 hover:bg-primary-700 active:bg-primary-800';
            case 'primary-outline':
                return 'px-6 py-[0.69rem] border border-primary-500 text-primary-500 shadow-sm hover:text-primary-600 hover:border-primary-600 active:text-primary-800 active:border-primary-800';
            case 'primary-footer':
                return ' w-full px-6 py-3 bg-primary-500 text-black-100 hover:bg-primary-700 active:bg-primary-800';
            case 'icon':
                return 'px-3 py-3 border border-gray-300 rounded-md shadow-sm hover:bg-blue-600';
            default:
                return '';
        }
    };

    return (
        <button
            onClick={onClick}
            className={`rounded-md inline-flex justify-center items-center focus:outline-none ${getVariantStyles()}`}
        >
            <span>{Icon && <Icon className="inline-block w-5 h-5" />}</span>
            <span className='text-[1.25rem] font-medium leading-[100%] tracking-[0.031rem] '>{label}</span>
        </button>
    );
};

export default Button;



