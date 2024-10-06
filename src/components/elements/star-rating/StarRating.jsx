import React, { useState } from 'react';
import { FaStar } from 'react-icons/fa';

const StarRating = ({ totalStars, onRate, usersRating }) => {
  const [rating, setRating] = useState(0);
  const [hoverRating, setHoverRating] = useState(0);
  const activeColor = '#ffc107';
  const inactiveColor = '#e4e5e9';

  const handleMouseEnter = (value) => {
    setHoverRating(value);
  };

  const handleMouseLeave = () => {
    setHoverRating(0);
  };

  const handleClick = (value) => {
    setRating(value);
    onRate(value);
  };

  return (
    <div className='flex items-center'>
      {[...Array(totalStars)].map((_, index) => {
        const isActive = (hoverRating || rating) > index;
        const isActiveRange = usersRating > index;

        return (
          <FaStar
            key={index}
            color={isActive ? activeColor : isActiveRange ? inactiveColor : inactiveColor}
            onClick={() => handleClick(index + 1)}
            onMouseEnter={() => handleMouseEnter(index + 1)}
            onMouseLeave={handleMouseLeave}
            style={{ cursor: 'pointer' }}
            className='hover:text-yellow-500'
            size={20}
          />
        );
      })}
      {usersRating && <p className='ml-2'>{usersRating}</p>}
    </div>
  );
};

export default StarRating;
