// ForexStreamComponent.jsx
import React, { useState, useEffect } from 'react';
import { fetchForexStream } from '../../../services/devise/StreamDevise';  // Import the service
import "./DeviseListStream.css";
import DialogDemo from '../../operation/AddOperation';
import AddOperationComponent from '../../operation/AddOperationTest';


const columns = [
    { columnId: "label", title: "Devise" },
    { columnId: "symbol", title: "Symbol" },
    { columnId: "borrow", title: "Interest rate of Borrow" },
    { columnId: "lend", title: "Interest rate of Lend" },
    { columnId: "intrestaverage", title: "Moyenne des intérêts" },
    { columnId: "intrestspread", title: "Spread" },
    { columnId: "lastUpdated", title: "Last Update" }
  ];


const ForexStreamComponent = () => {
  const [deviseData, setDeviseData] = useState([]);  // State to store unique forex data
  const [selectedDevise, setSelectedDevise] = useState(null);  // State to store the selected devise
  const [openDialog, setOpenDialog] = useState(true);  // State to control the dialog visibility



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


  
  const handleRowClick = (deviseId, deviseLabel, deviseSymbol, borrowRate, lendRate) => {
    setSelectedDevise({ id: deviseId, label: deviseLabel, symbol: deviseSymbol, borrow: borrowRate, lend: lendRate }); 
    setOpenDialog(true)
    };
  const handleDialogClose = () => {
    setOpenDialog(false);  // Close the dialog
  };


  return (
    
    <div className="left-paneltstream">
      <h3>Latest Forex Stream Data</h3>
        <div className="filterbuttons">
          <button className="filter-btn">Filter 1</button>
          <button className="filter-btn">Filter 2</button>
          <button className="filter-btn">Filter 3</button>
          <button className="add-operation-button">Add operation</button>
          {/* <DialogDemo /> */}
          <AddOperationComponent 
          selectedDevise={selectedDevise} 
          openDialog={openDialog}
          onClose={handleDialogClose} 
        />
        </div>
        <div className="inner-part">
            <div className="grid-containertl" >
                <div className="overflow-auto max-h-64 border border-gray-300 rounded-lg">
                    <table className="border-collapse">
                        <thead className="devisetablehead">
                        <tr>
                            {columns.map((col) => (
                            <th key={col.columnId} className="p-2 border border-gray-600 text-center text-sm">
                                {col.title}
                            </th>
                            ))}
                        </tr>
                        </thead>
                        <tbody>
                        {deviseData.length > 0 ? (
                            deviseData.map((forex, index) => (  
                            <tr key={index} className="bg-gray-100"> 
                            <td className="p-1 border border-gray-400 text-center text-sm">{forex.label}</td>
                            <td className="p-1 border border-gray-400 text-center text-sm cursor-pointer text-blue-600 underline"
                            onClick={() => handleRowClick(forex.id, forex.label, forex.ssymbol, forex.sborrow, forex.slend)}
                            >{forex.ssymbol}</td>
                            <td className="p-1 border border-gray-400 text-center text-sm" style={{ color: forex.colorBorrow }}>
                                {forex.sborrow}
                            </td>
                            <td className="p-1 border border-gray-400 text-center text-sm" style={{ color: forex.colorLend }}>
                                {forex.slend}
                            </td>
                            <td className="p-1 border border-gray-400 text-center text-sm" style={{ color: forex.colorAvg }}>
                                {forex.sintrestaverage}
                            </td>
                            <td className="p-1 border border-gray-400 text-center text-sm" style={{ color: forex.colorSpread }}>
                                {forex.sintrestspread}
                            </td>

                            <td className="p-1 border border-gray-400 text-center text-sm"> {forex.lastUpdated}</td>
                            </tr>
                            ))
                            ) : (
                            <p>No new data yet.</p>  // Placeholder if no data received yet
                            )}

                        </tbody>
                    </table>
                    
      {/* {openDialog && (
        <AddOperationComponent
          selectedDevise={selectedDevise} // Passing selectedDevise to AddOperationComponent
          onClose={handleDialogClose} // Close the dialog when needed
        />
      )} */}
                </div>
            </div>
          
        </div>



    </div>  
    
  );
};

export default ForexStreamComponent;

      /* {deviseData.length > 0 ? (
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
      )} */



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
