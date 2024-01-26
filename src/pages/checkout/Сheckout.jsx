import React from 'react';
import Select from 'react-select'; 

import './styleDropDown.css';

import { useForm } from "react-hook-form";


const Сheckout = (item) => {

    const phoneCode = [
        { value: '+1', label: '+1' },
        { value: '+49', label: '+49' },
        { value: '+380', label: '+380'}
    ]

    return (
        <div className='max-w-[38.438rem]'>
            <div className='w-full'>
                <form className='flex flex-col gap-14'>
                    <fieldset>
                        <legend className='text-2xl font-bold text-black-900'>
                            <span>1. Contact Information</span>
                        </legend>
                        <label htmlFor="fullName" className="flex flex-col">
                            Full Name
                            <input
                                type="text"
                                className="p-2 border border-gray-300 rounded mt-1"
                                placeholder='Enter your Full Name'
                            />
                        </label>
                        <div className='flex justify-between items-center'>
                            <label htmlFor="phoneCode">
                                <span>Dial Code</span>
                                <Select
                                    options={phoneCode}
                                    className='w-[6.25rem]'
                                    classNamePrefix='react-select'
                                    defaultValue={phoneCode[2]}
                                />
                            </label>
                            <label htmlFor="phoneNumber" className="flex flex-col max-w-[30.625rem] w-full">
                                <span>Phone Number</span>
                                <input
                                    type='tel'
                                    placeholder='Enter phone number'
                                    className=" min-h-[2.75rem] p-2 border border-gray-300 rounded"
                                />
                            </label>
                        </div>
                        <label htmlFor="Email" className="flex flex-col w-full">
                            <span>Email</span>
                            <input
                                type="email"
                                placeholder="Enter your email address"
                                className="p-2 border border-gray-300 rounded mt-1"
                            />
                        </label>
                        <label htmlFor="">
                            <input type="checkbox" />
                            <span>Sign up for emails to get the scoop on discounts, product launches, are more.</span>
                        </label>
                    </fieldset>
                    <fieldset>
                        <legend className='text-2xl font-bold text-black-900'>
                            <span>2. Shipping Information</span>
                        </legend>
                    </fieldset>
                    <fieldset>
                        <legend className='text-2xl font-bold text-black-900'>
                            <span>3. Payment</span>
                        </legend>
                    </fieldset>
                </form>
            </div>
        </div>
    );
}

export default Сheckout;