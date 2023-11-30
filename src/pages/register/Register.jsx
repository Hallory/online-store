import React from 'react';
import AuthForm from '../../components/auth/AuthForm';
import axios from 'axios';
import { motion } from 'framer-motion';

const Register = () => {
    const onSubmit = (data) => {
        console.log(data);
    };

    return (
        <motion.div
            initial={{ x: '100%' }}
            animate={{ x: 0 }}
            exit={{ x: '-100%' }}
        >
            <h2>Register with your email</h2>
            <AuthForm onSubmit={onSubmit} isRegistration={true} />
        </motion.div>
    );
};

export default Register;
