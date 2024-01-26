import React from 'react';
import Logo from '../../elements/Logo';
import { Link } from 'react-router-dom';
import Button from '../../elements/Button';

const Footer = (props) => {

    const categoryData = [
        {
            name: 'Smartphones',
            link: '/smartphones'

        },
        {
            name: 'Laptops',
            link: '/laptops'

        },
        {
            name: 'Accessories',
            link: '/accessories'

        }

    ]

    const helpData = [
    {
        title: 'Customer Support',
        link: '/customerSupport'

    },
    {
        title: 'Delivery Details',
        link: '/deliveryDetails'

    },
    {
        title: 'Terms & Conditions',
        link: '/termsConditions'

    },
    {
        title: 'Privacy Policy',
        link: '/privacyPolicy'

    },


]

    return (
        <footer className='py-10 bg-gray-200 '>
            <div className='flex justify-around  w-full mx-auto'>
                <div className='px-6 max-w-[16.6rem] w-full'>
                    <Logo />
                    <p className='text-[1.125rem] text-black-600 mt-6'>
                        ElectroMagic is your guide in the world of advanced technologies, offering innovative smartphones and laptops to improve your experience.
                    </p>
                </div>
                <div>
                    <h6 className='text-[1.125rem] text-black-600 font-bold'>
                        Category
                    </h6>
                    <ul>
                        {categoryData.map((item, index) => {
                            return (
                                <li key={index}
                                    className='mt-8 leading-[140%] text-black-800'>
                                    <Link to={item.link}>
                                        {item.name}
                                    </Link>
                                </li>
                            )
                        })}
                    </ul>
                </div>
                <div>
                    <h6 className='text-[1.125rem] text-black-600 font-bold'>
                        Help
                    </h6>
                    <ul>
                        {helpData.map((item, index) => {
                            return (
                                <li key={index}
                                    className='mt-8 leading-[140%] text-black-800'>
                                    <Link to={item.link}>
                                        {item.title}
                                    </Link>
                                </li>
                            )
                        })}
                    </ul>
                </div>
                <div className='max-w-[15rem] w-full'>
                    <h6 className='text-[1.125rem] text-black-600 font-bold mb-8'>
                        Newsletter
                    </h6>
                    <div className='px-3 py-2 mb-3 items-center self-stretch bg-black-100 border border-black-200 rounded-md text-black-900 focus-visible:outline-none'>
                        <input
                            className='text-[1.125rem] overflow-hidden text-black-900 leading-[140%] bg-transparent placeholder:text-[1.125rem] placeholder:text-black-700 placeholder:text-ellipsis placeholder:whitespace-nowrap placeholder:font-normal focus-visible:outline-none'
                            type="text"
                            placeholder='Enter your email address' />
                    </div>
                    <Button
                        label='Subscribe Now'
                        onClick={() => { console.log('Click!'); }}
                        variant='primary-full'/>
                </div>
            </div>
            <div className='h-[1px] max-w-[73.125rem] w-full bg-clr_foo_bg_line mx-auto my-16'>
            </div>
            <div className='font-poppins text-center text-sm'>
                <span>
                    © Copyright 2022, All Rights Reserved by
                    <a className='pl-1 font-bold' href="/">ElectroMagic</a>
                </span>
            </div>
        </footer>
    )
}

export default Footer;