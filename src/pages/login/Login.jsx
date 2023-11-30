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
        className='flex flex-col w-full h-screen bg-gray-100 dark:bg-gray-800'
            initial={{ x: '-100%' }}
            animate={{ x: 0 }}
            exit={{ x: '100%' }}
        >
            <h2>Log in</h2>
            <AuthForm onSubmit={onSubmit} isRegistration={false} />
        </motion.div>
    );
};

export default Login;
