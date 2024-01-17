// Button.js
import React from 'react';

const Button = ({ onClick, icon: Icon, label, variant = 'primary' }) => {
    const getVariantStyles = () => {
        switch (variant) {
            case 'primary':
                return 'px-6 py-3 bg-primary-500 text-black-100 hover:bg-primary-700 active:bg-primary-800';
            case 'primary-outline':
                return 'w-full grow px-6 py-[0.69rem] border border-primary-500 text-primary-500 shadow-sm hover:text-primary-600 hover:border-primary-600 active:text-primary-800 active:border-primary-800';
            case 'primary-full':
                return ' w-full px-6 py-3 bg-primary-500 text-black-100 hover:bg-primary-700 active:bg-primary-800';
            case 'icon':
                return 'p-2 text-black-800 hover:text-primary-700 hover:shadow-sm ring-1 ring-opacity-10 ring-gray-300 hover:shadow-md ring-1 ring-opacity-9 ring-gray-300 hover:shadow-lg ring-2 ring-opacity-5 ring-indigo-500 active:shadow-sm ring-1 ring-opacity-10 ring-gray-300 active:shadow-md ring-1 ring-opacity-9 ring-gray-300 active:shadow-lg ring-2 ring-opacity-5 ring-indigo-500';
            case 'icon-remove':
                return 'text-danger-700 hover:text-primary-700 hover:shadow-sm ring-1 ring-opacity-10 ring-gray-300 hover:shadow-md ring-1 ring-opacity-9 ring-gray-300 hover:shadow-lg ring-2 ring-opacity-5 ring-indigo-500 active:shadow-sm ring-1 ring-opacity-10 ring-gray-300 active:shadow-md ring-1 ring-opacity-9 ring-gray-300 active:shadow-lg ring-2 ring-opacity-5 ring-indigo-500'; 
            case 'icon-fill':
                return 'px-4 py-2 bg-primary-500 text-black-100 hover:bg-primary-700 active:bg-primary-800';
            
            default:
                return '';
        }
    };

    return (
        <button
            onClick={onClick}
            className={`rounded-md inline-flex justify-center items-center focus:outline-none ${getVariantStyles()}`}
        >
            <span>{Icon && <Icon className="inline-block w-6 h-6" />}</span>
            <span className='text-[1.25rem] font-medium leading-[100%] tracking-[0.031rem] '>{label}</span>
        </button>
    );
};

export default Button;



