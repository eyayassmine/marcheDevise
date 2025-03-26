// ForexStreamComponent.jsx
import React, { useState, useEffect } from 'react';
import { fetchForexStream } from '../../../services/devise/StreamDevise';  // Import the service

const ForexStreamComponent = () => {
  const [deviseData, setDeviseData] = useState([]);  // State to store unique forex data

  useEffect(() => {
    // Clear the localStorage (if you're storing any data there)
    localStorage.removeItem('deviseData'); // Clear old data from localStorage

    // Initialize the stream and pass the current deviseData to the service
    const eventSource = fetchForexStream(setDeviseData, deviseData);

    // Cleanup the event source connection when the component unmounts
    return () => {
      eventSource.close();
    };
  }, []); // The empty dependency array ensures this runs only on mount

  return (
    <div>
      <h1>Latest Forex Stream Data</h1>
      {deviseData.length > 0 ? (
        deviseData.map((forex, index) => (
          <div key={index} style={{ border: "1px solid black", padding: "10px", margin: "5px" }}>
            <p><strong>Libelle:</strong> {forex.slibelle}</p>
            <p><strong>Symbol:</strong> {forex.ssymbol}</p>
            <p><strong>Borrow Rate:</strong> {forex.sborrow}</p>
            <p><strong>Lend Rate:</strong> {forex.slend}</p>
            <p><strong>Interest Average:</strong> {forex.sintrestaverage}</p>
            <p><strong>Interest Spread:</strong> {forex.sintrestspread}</p>
            <p><strong>Last Updated:</strong> {forex.lastUpdated}</p>
          </div>
        ))
      ) : (
        <p>No new data yet.</p>  // Placeholder if no data received yet
      )}
    </div>
  );
};

export default ForexStreamComponent;


// import React from "react";
// import useForexStream from "../../../services/devise/StreamDevise"; // Import the service

// const ForexStreamComponent = () => {
//   const forexData = useForexStream();

//   return (
//     <div>
//       <h1>Real-time Forex Rates</h1>
//       {forexData.length > 0 ? (
//         <div>
//           {forexData.map((forex, index) => (
//             <div key={index} style={{ border: "1px solid black", padding: "10px", margin: "5px" }}>
//               <p><strong>Libelle:</strong> {forex.slibelle}</p>
//               <p><strong>Symbol:</strong> {forex.ssymbol}</p>
//               <p><strong>Borrow Rate:</strong> {forex.sborrow}</p>
//               <p><strong>Lend Rate:</strong> {forex.slend}</p>
//               <p><strong>Interest Average:</strong> {forex.sintrestaverage}</p>
//               <p><strong>Interest Spread:</strong> {forex.sintrestspread}</p>
//               <p><strong>Last Updated:</strong> {forex.lastUpdated}</p>
//             </div>
//           ))}
//         </div>
//       ) : (
//         <p>Waiting for forex data...</p>
//       )}
//     </div>
//   );
// };

// export default ForexStreamComponent;




// import React from "react";
// import useForexStream from "../../../services/devise/StreamDevise"; // Import the service

// const ForexStreamComponent = () => {
//   const forexData = useForexStream(); // Get real-time data from SSE

//   return (
//     <div>
//       <h1>Real-time Forex Rates</h1>
//       {forexData && forexData.length > 0 ? ( // Ensure forexData is not null before accessing length
//         <div>
//           {forexData.map((data, index) => (
//             <div key={index} style={{ border: "1px solid #ddd", padding: "10px", marginBottom: "10px" }}>
//               <p><strong>Libelle:</strong> {data.slibelle}</p>
//               <p><strong>Symbol:</strong> {data.ssymbol}</p>
//               <p><strong>Borrow Rate:</strong> {data.sborrow}</p>
//               <p><strong>Lend Rate:</strong> {data.slend}</p>
//               <p><strong>Interest Average:</strong> {data.sintrestaverage}</p>
//               <p><strong>Interest Spread:</strong> {data.sintrestspread}</p>
//               <p><strong>Last Updated:</strong> {new Date(data.lastUpdated).toLocaleString()}</p>
//             </div>
//           ))}
//         </div>
//       ) : (
//         <p>Waiting for forex data...</p>
//       )}
//     </div>
//   );
// };

// export default ForexStreamComponent;



//import { useEffect, useState } from "react";
// function DeviseListStream() {
//     const [deviseData, setDeviseData] = useState([]);

//     useEffect(() => {
//         // Establish the SSE connection
//         const eventSource = createSseConnection(
//             (newDeviseH) => {
//                 // On receiving new data, update the state
//                 setDeviseData(prevData => [...prevData, newDeviseH]);
//             },
//             (error) => {
//                 // Handle errors (e.g., log or update UI)
//                 console.error("SSE connection error:", error);
//             }
//         );

//         // Cleanup when the component unmounts
//         return () => {
//             eventSource.close(); // Close the connection on unmount
//         };
//     }, []); // Empty dependency array means it only runs once when the component mounts

//     return (
//         <div className="DeviseListstream">
//             <h3>Streamed Devises</h3>
//             <ul>
//                 {deviseData.map((deviseH, index) => (
//                     <li key={index}>
//                         {deviseH.libelle}: {deviseH.borrow} / {deviseH.lend}
//                     </li>
//                 ))}
//             </ul>
//         </div>
//     );
// }

// export default DeviseListStream;
