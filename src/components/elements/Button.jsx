// Button.js
import React from 'react';

const Button = ({ onClick, icon: Icon, label, variant = 'primary' }) => {
    const getVariantStyles = () => {
        switch (variant) {
            case 'primary':
                return 'px-6 py-3 bg-btn_primary text-default hover:bg-btn_primary__hover active:bg-btn_primary__active';
            case 'primary-outline':
                return 'px-6 py-[0.69rem] border border-btn_primary text-btn_primary shadow-sm hover:text-btn_outline__hover hover:border-btn_outline__hover active:text-btn_primary__active active:border-btn_primary__active';
            case 'primary-footer':
                return ' w-full px-6 py-3 bg-btn_primary text-default hover:bg-btn_primary__hover active:bg-btn_primary__active';
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



