// Login.jsx
import React from 'react';
import AuthForm from '../../components/auth/AuthForm';
import { motion } from 'framer-motion';

const Login = ({ shouldAnimate}) => {
  const onSubmit = (data) => {
    console.log(data);
  };

  return (
    <motion.div
      className='flex items-center justify-center w-full h-[90%]  dark:bg-gray-800'
      initial={shouldAnimate ? { x: '100%' } : false}
      animate={shouldAnimate ? { x: 0 } : false}
      exit={shouldAnimate ? { x: '100%' } : false}
      transition={{ duration: 0.5 }}
    >
      <AuthForm onSubmit={onSubmit} isRegistration={false} />
    </motion.div>
  );
};

export default Login;
