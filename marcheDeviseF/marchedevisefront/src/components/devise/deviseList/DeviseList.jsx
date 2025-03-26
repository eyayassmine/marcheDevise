import React, { useState, useEffect } from "react";
import { getAllDevises } from "../../../services/devise/Devise";
import "./DeviseList.css";

const columns = [
  { columnId: "libelle", title: "Devise" },
  { columnId: "borrow", title: "Interest rate of Borrow" },
  { columnId: "lend", title: "Interest rate of Lend" },
  { columnId: "intrestaverage", title: "Moyenne des intérêts" },
  { columnId: "intrestspread", title: "Spread" },
  { columnId: "createdDate", title: "Created date" }
];

const DeviseList = () => {
  const [rows, setRows] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);
  const [zoom, setZoom] = useState(1);

  const zoomOut = () => {
    setZoom(prevZoom => Math.max(prevZoom - 0.2, 0.5));
  };

  useEffect(() => {
    const fetchDevises = async () => {
      try {
        const data = await getAllDevises();
        const formattedRows = data.map((devise) => ({
          id: devise.id,
          libelle: devise.libelle,
          borrow: devise.borrow ? `${devise.borrow}%` : "N/A",
          lend: devise.lend ? `${devise.lend}%` : "N/A",
          intrestaverage: devise.intrestaverage?.toFixed(2) ?? "N/A",
          intrestspread: devise.intrestspread?.toFixed(2) ?? "N/A",
          createdDate: devise.createdDate
        }));
        setRows(formattedRows);
      } catch (err) {
        setError("Failed to load data.");
      } finally {
        setLoading(false);
      }
    };

    fetchDevises();
  }, []);

  return (
    <div className="DeviseList">
      <div className="left-panel">
        <div className="filterbuttons">
          <button className="filter-btn">Filter 1</button>
          <button className="filter-btn">Filter 2</button>
          <button className="filter-btn">Filter 3</button>
        </div>

        <div className="inner-part">
          <div className="grid-containertl" style={{ transform: `scale(${zoom})`, transformOrigin: "top left" }}>
            <div className="overflow-auto max-h-64 border border-gray-300 rounded-lg">
              {loading && <p>Loading data...</p>}
              {error && <p>{error}</p>}
              <table className="border-collapse">
                <thead className="devisetablehead">
                  <tr>
                    {columns.map((col) => (
                      <th key={col.columnId} className="p-2 border border-gray-600">
                        {col.title}
                      </th>
                    ))}
                  </tr>
                </thead>
                <tbody>
                  {rows.map((row) => (
                    <tr key={row.id} className="bg-gray-100">
                      <td className="p-2 border border-gray-400 text-center">{row.libelle}</td>
                      <td className="p-2 border border-gray-400 text-center">{row.borrow}</td>
                      <td className="p-2 border border-gray-400 text-center">{row.lend}</td>
                      <td className="p-2 border border-gray-400 text-center">{row.intrestaverage}</td>
                      <td className="p-2 border border-gray-400 text-center">{row.intrestspread}</td>
                      <td className="p-2 border border-gray-400 text-center">{new Date(row.createdDate).toLocaleString()}</td>
                    </tr>
                  ))}
                </tbody>
              </table>
            </div>
          </div>
        </div>
      </div>
      <button className="zoom-out" onClick={zoomOut}>Zoom Out</button>
    </div>
  );
};

export default DeviseList;






// import React, { useState, useEffect } from "react";
// import { getAllDevises } from "../../../services/devise/Devise"
// import "./DeviseList.css"; // Import CSS file


// const columns = [
  
//   { columnId: "libelle", title: "Devise" },
//   { columnId: "borrow", title: "intrest rate of Borrow" },
//   { columnId: "lend", title: "intrest rate of Lend" },
//   { columnId: "intrestaverage", title: "Moyenne des intérêts" },
//   { columnId: "intrestspread", title: "Spread" },
//   { columnId: "lastUpdated", title: "Last Updated" }
  

// ];


// const DeviseList = () => {


//   const [rows, setRows] = useState([]); // State to store fetched data
//   const [loading, setLoading] = useState(true);
//   const [error, setError] = useState(null);

//   // useEffect(() => {
//   //   const fetchDevises = async () => {
//   //     try {
//   //       const data = await getAllDevises();
//   //       console.log("Fetched Data:", data); // Check API response
//   //       const formattedRows = data.map((devise) => ({
//   //           id: devise.id, // Assuming each devise has a unique ID from the backend
//   //           libelle: devise.libelle,
//   //           borrow: devise.borrow ? `${devise.borrow}%`: "N/A",
//   //           lend:  devise.lend ? `${devise.lend}%`: "N/A",
//   //           // intrestaverage: devise.moyenneInterets,
//   //           // intrestspread: devise.spread,
//   //           intrestaverage: devise.intrestaverage?.toFixed(2) ?? "N/A", // Fix undefined error
//   //           intrestspread: devise.intrestspread?.toFixed(2) ?? "N/A",
//   //           lastUpdated: devise.lastUpdated, // Add this if available
//   //       }));
//   //       setRows(formattedRows);
//   //     } catch (err) {
//   //       console.error("Error fetching devises:", err);
//   //       setError("Failed to load data.");
//   //     } finally {
//   //       setLoading(false);
//   //     }
//   //   };

//   //   fetchDevises();
//   // }, []);

//     // Fetch data inside the useEffect
//     useEffect(() => {
//       const fetchDevises = async () => {
//         try {
//           const data = await getAllDevises();
//           console.log("Fetched Data:", data); // Check API response
//           const formattedRows = data.map((devise) => ({
//             id: devise.id, // Assuming each devise has a unique ID from the backend
//             libelle: devise.libelle,
//             borrow: devise.borrow ? `${devise.borrow}%` : "N/A",
//             lend: devise.lend ? `${devise.lend}%` : "N/A",
//             intrestaverage: devise.intrestaverage?.toFixed(2) ?? "N/A",
//             intrestspread: devise.intrestspread?.toFixed(2) ?? "N/A",
//             lastUpdated: devise.lastUpdated, // Add this if available
//           }));
//           setRows(formattedRows);
//         } catch (err) {
//           console.error("Error fetching devises:", err);
//           setError("Failed to load data.");
//         } finally {
//           setLoading(false);
//         }
//       };
  
//       fetchDevises(); // Call the function here
//     }, []); // Empty dependency array to run this effect once when the component mounts
  



//   const [zoom, setZoom] = useState(1); // State to control zoom level

//   const zoomOut = () => {
//     setZoom(prevZoom => Math.max(prevZoom - 0.2, 0.5)); // Prevent zooming out too much
//   };

//   return (
//     <div className="DeviseList" >
//       {/* Left Panel */}
//       <div className="left-panel">

//         <div className="filterbuttons">
//         <button className="filter-btn">Filter 1</button>
//         <button className="filter-btn">Filter 2</button>
//         <button className="filter-btn">Filter 3</button>
//         </div>
//         <div className="inner-part">
//         <div className="grid-container" style={{ transform: `scale(${zoom})`, transformOrigin: "top left" }}>
//             <div className="overflow-auto max-h-64 border border-gray-300 rounded-lg">
//             <table className="w-full border-collapse">
//               {/* Table Head */}
//               <thead className="devisetablehead">
//                 <tr>
//                   {columns.map((col) => (
//                     <th key={col.columnId} className="p-2 border border-gray-600">
//                       {col.title}
//                     </th>
//                   ))}
//                 </tr>
//               </thead>
              
//               {/* Table Body */}
//               <tbody>
//               {rows.map((row) => (
//                       <tr key={row.id} className="bg-gray-100">
//                         <td className="p-2 border border-gray-400 text-center">{row.libelle}</td>
//                         <td className="p-2 border border-gray-400 text-center">{row.borrow}</td>
//                         <td className="p-2 border border-gray-400 text-center">{row.lend}</td>
//                         <td className="p-2 border border-gray-400 text-center">{row.intrestaverage}</td>
//                         <td className="p-2 border border-gray-400 text-center">{row.intrestspread}</td>
//                         <td className="p-2 border border-gray-400 text-center">{new Date(row.lastUpdated).toLocaleString()}</td>
//                       </tr>
//                     ))}
//               </tbody>
//             </table>
//           </div>



//           {/*Array.from({ length: 12 }).map((_, index) => (
//             <div key={index} className="grid-item">Row {Math.floor(index / 4) + 1}, Col {(index % 4) + 1}</div>
//           ))*/}
//         </div>
//         <button className="zoom-out" onClick={zoomOut}>Zoom Out</button>
//       </div>
//       </div>

//     </div>
//   );
// };

// export default DeviseList;

//kdima

 // Handle SSE connection
//  useEffect(() => {
//   fetchDevises(); // Fetch initial data

//   const eventSource = new EventSource("/api/devise/stream"); // SSE endpoint
//   eventSource.onmessage = (event) => {
//     const newDevise = JSON.parse(event.data);
//     setRows((prevRows) => {
//       const updatedRows = [...prevRows];
//       const index = updatedRows.findIndex(row => row.id === newDevise.id);

//       if (index > -1) {
//         // Update existing devise
//         updatedRows[index] = { ...updatedRows[index], ...newDevise };
//       } else {
//         // Add new devise
//         updatedRows.push(newDevise);
//       }
//       return updatedRows;
//     });
//   };

//   eventSource.onerror = (err) => {
//     console.error("Error receiving SSE:", err);
//     eventSource.close(); // Close the connection on error
//   };

//   // Cleanup on unmount
//   return () => {
//     eventSource.close();
//   };

//     //   fetchDevises();
// }, []); // Only run on mount




//   { columnId: "symbol", width: 100, title: "Devise" },
//   { columnId: "interet emprunt", width: 100, title: "Interet emprunt" },
//   { columnId: "interet pret", width: 100, title: "interet pret" },
//   { columnId: "moyenne des interets", width: 100, title: "Moyenne des interets" },
//   { columnId: "spread", width: 100, title: "Spread" }
// const initialRows = [
//   { rowId: 1, cells: [{ type: "text", text: "EUR"}, { type: "text", text: "3.5%"}, { type: "text", text: "+2.5%"}, { type: "text", text: "1.2M"}, { type: "text", text: "1.2M"}] },
//   { rowId: 2, cells: [{ type: "text", text: "CAD"}, { type: "text", text: "1.8%"}, { type: "text", text: "1.3%"}, { type: "text", text: "500K"}, { type: "text", text: "1.2M"}] },
//   { rowId: 3, cells: [{ type: "text", text: "JPY"}, { type: "text", text: "5%"}, { type: "text", text: "4.8%"}, { type: "text", text: "900K"}, { type: "text", text: "1.2M"} ]},
//   { rowId: 4, cells: [{ type: "text", text: "GPB"}, { type: "text", text: "6.4%"}, { type: "text", text: "5.5%"}, { type: "text", text: "2M"}, { type: "text", text: "1.2M"}] },
//   { rowId: 5, cells: [{ type: "text", text: "AUD"}, { type: "text", text: "9.4%"}, { type: "text", text: "8.1%"}, { type: "text", text: "1.1M"}, { type: "text", text: "1.2M"}] },
//   { rowId: 6, cells: [{ type: "text", text: "USD"}, { type: "text", text: "7.2%"}, { type: "text", text: "6.6%"}, { type: "text", text: "1.1M"}, { type: "text", text: "1.2M"}] },
//   { rowId: 7, cells: [{ type: "text", text: "SGD"}, { type: "text", text: "4.3%"}, { type: "text", text: "3.9%"}, { type: "text", text: "1.1M"}, { type: "text", text: "025"}] }

// ];



