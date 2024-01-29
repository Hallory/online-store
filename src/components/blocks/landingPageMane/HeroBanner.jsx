import React, { useState, useEffect } from 'react';
import Slider from 'rc-slider';
import 'rc-slider/assets/index.css';

const HeroBanner = () => {
  const images = [
    'image1.jpg',
    'image2.jpg',
    'image3.jpg',
    // Add more image URLs as needed
  ];

  const [currentImageIndex, setCurrentImageIndex] = useState(0);
  const [timer, setTimer] = useState(5); // Set the initial timer value in seconds

  useEffect(() => {
    const intervalId = setInterval(() => {
      // Update the current image index
      setCurrentImageIndex((prevIndex) =>
        prevIndex === images.length - 1 ? 0 : prevIndex + 1
      );

      // Reset the timer when reaching the last image
      setTimer(images.length - 1 === currentImageIndex ? 5 : timer);
    }, 5000); // Set the interval time in milliseconds (5000 = 5 seconds)

    return () => clearInterval(intervalId); // Cleanup the interval on component unmount
  }, [currentImageIndex, timer, images.length]);

  const handleSliderChange = (value) => {
    setCurrentImageIndex(value);
  };

  return (
    <div className="hero-banner">
      <h1>My Hero Banner</h1>
      <TimerDisplay timer={timer} />
      <Slider value={currentImageIndex} max={images.length - 1} onChange={handleSliderChange} />
      <div className="slider-image">
        <img src={images[currentImageIndex]} alt={`Slide ${currentImageIndex + 1}`} />
      </div>
    </div>
  );
};

const TimerDisplay = ({ timer }) => {
  return <div className="timer">Timer: {timer} seconds</div>;
};

export default HeroBanner;
