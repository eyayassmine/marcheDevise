import { useState } from 'react'
import reactLogo from './assets/react.svg'
import viteLogo from '/vite.svg'
import './App.css'
import Home from './components/home/Home';
import NavBar from './components/navBar/NavBar';

function App() {

  return (
    <>
            <NavBar />
            <Home />
    </>
  )
}

export default App
