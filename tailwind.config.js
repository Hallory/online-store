/** @type {import('tailwindcss').Config} */
module.exports = {
  content: [
    "./src/**/*.{js,jsx,ts,tsx}",
  ],
  mode: 'jit',
  darkMode: 'class',
  theme: {
    colors: {
      'primary': {
        '100': '#d5e7ff',
        '200': '#b3d1ff',
        '300': '#85b0ff',
        '400': '#5680ff',
        '500': '#3052ff',
        '600': '#0c1eff',
        '700': '#0313ff',
        '800': '#0616cd',
        '900': '#0d1982',
      },
      'success': {
        '100': '#f2fbcc',
        '200': '#e2f89b',
        '300': '#c7ec68',
        '400': '#a9d941',
        '500': '#82c10d',
        '600': '#69a509',
        '700': '#538a06',
        '800': '#3e6f04',
        '900': '#305c02',
      },
      'info': {
        '100': '#d8f4fe',
        '200': '#b1e5fe',
        '300': '#89d1fe',
        '400': '#6cbefd',
        '500': '#3c9ffc',
        '600': '#2b7cd8',
        '700': '#1e5cb5',
        '800': '#134192',
        '900': '#0b2d78',
      },
      'warning': {
        '100': '#fff8d6',
        '200': '#ffefad',
        '300': '#ffe483',
        '400': '#ffda65',
        '500': '#ffc014',
        '600': '#dba524',
        '700': '#b78419',
        '800': '#93650f',
        '900': '#7a4f09',
      },
      'danger': {
        '100': '#ffe9d8',
        '200': '#ffceb2',
        '300': '#ffad8b',
        '400': '#ffa3a3',
        '500': '#ff593f',
        '600': '#db372e',
        '700': '#b71f23',
        '800': '#931420',
        '900': '#7a0c1f',
      },
      'black': {
        '100': '#ffffff',
        '200': '#e6e6ea',
        '300': '#c3c4cd',
        '400': '#c3c4cd',
        '500': '#a6a6b4',
        '600': '#9190a1',
        '700': '#757283',
        '800': '#504f59',
        '900': '#080817',
        '1000': '#000000',
      },
      'gray': {
        '100': '#fcfdfe',
        '150': '#eff4fe',
        '200': '#fafafb',
        '300': '#e6eefd',
        '400': '#d3e0f8',
        '500': '#c5d2ea',
        '600': '#adbbd4',
        '700': '#778ab1',
        '800': '#4b5e8f',
        '900': '#2d3e76',
      },
      'dark-theme': {
        '100': '#8a8aab',
        '200': '#6d6d8a',
        '300': '#43435c',
        '400': '#35354b',
        '500': '#2c2c3d',
        '600': '#202034',
        '700': '#16162b',
        '800': '#0e0e23',
        '900': '#08081d',
      },
    },
    extend: {},
  },
  plugins: [
    require('tailwindcss'),
    require('autoprefixer'),

    function ({ addUtilities }) { 
      const newUtilities = {
        ".no-scrollbar::-webkit-scrollbar": {
          display: "none",
        },
        ".no-scrollbar": {
          "-ms-overflow-style": "none",
          "scrollbar-width": "none",
        },
      };
      
      addUtilities(newUtilities);
    },
  ],
};

