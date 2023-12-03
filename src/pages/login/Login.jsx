import React from 'react';
import AuthForm from '../../components/auth/AuthForm';
import axios from 'axios';
import { motion } from 'framer-motion';
const Login = () => {
    const onSubmit = (data) => {
        console.log(data);
    };

    return (
      
        <motion.div
  className='flex items-center justify-center w-full h-screen bg-gray-100 dark:bg-gray-800'
  initial={{ x: '-100%' }}
  animate={{ x: 0 }}
  exit={{ x: '100%' }}
  transition={{ duration: 1}}

>
  
    <AuthForm onSubmit={onSubmit} isRegistration={false} />

    
</motion.div>

    );
};

export default Login;
