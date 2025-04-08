import { StrictMode } from 'react'
import { createRoot } from 'react-dom/client'
import './index.css'
import App from './App.jsx'
// import 'bootstrap/dist/css/bootstrap.min.css'; // For CSS
// import 'bootstrap/dist/js/bootstrap.bundle.min.js'; // For JS (optional, for dropdown functionality)


createRoot(document.getElementById('root')).render(
  <StrictMode>
    <App />
  </StrictMode>,
)
