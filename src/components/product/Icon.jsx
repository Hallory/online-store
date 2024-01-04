import React from 'react';
import Icons from './svg/sprite.svg';

const Icon = ({ name }) => {

    return (
        <svg className='w-6 h-6 fill-black-400'>
            <use href={Icons + "#" + name}></use>
        </svg>
    );
};

export default Icon;