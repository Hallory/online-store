/** @type {import('tailwindcss').Config} */
module.exports = {
  content: [
    "./src/**/*.{js,jsx,ts,tsx}",
  ],
  mode:'jit',
  darkMode: 'class',
  theme: {
    colors: {
      default: '#ffffff',
      black: '#080817',
      //BACKGROUND COLORS
      bg_default: '#FFFFFF',
      bg_secondary: '#FAFAFB',
      //BUTTON COLORS
      btn_primary: '#3052FF',
      btn_primary__hover: '#0313FF',
      btn_primary__active: '#0616CD',
      btn_outline__hover: '#0C1EFF',
      //SEARCH BAR COLORS
      brd: '#E6E6EA',
      placeholder: '#757283',
      //TEXT COLORS
      clr_txt: '#9190A1',
      clr_txt_secondary: '#504F59',
      //FOOTER COLORS
      clr_foo_bg_line: '#AAAAAA',
    },
    extend: {
    },

  },
  plugins: [
    require('tailwindcss'),
    require('autoprefixer'),
  ],

}

