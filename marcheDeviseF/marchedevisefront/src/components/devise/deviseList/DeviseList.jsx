import React, { useState, useEffect } from "react";
import "./DeviseList.css"; // Import CSS file


const columns = [
  { columnId: "symbol", width: 100, title: "Devise" },
  { columnId: "interet emprunt", width: 100, title: "Interet emprunt" },
  { columnId: "interet pret", width: 100, title: "interet pret" },
  { columnId: "moyenne des interets", width: 100, title: "Moyenne des interets" },
  { columnId: "spread", width: 100, title: "Spread" }
];

const initialRows = [
  { rowId: 1, cells: [{ type: "text", text: "EUR"}, { type: "text", text: "3.5%"}, { type: "text", text: "+2.5%"}, { type: "text", text: "1.2M"}, { type: "text", text: "1.2M"}] },
  { rowId: 2, cells: [{ type: "text", text: "CAD"}, { type: "text", text: "1.8%"}, { type: "text", text: "1.3%"}, { type: "text", text: "500K"}, { type: "text", text: "1.2M"}] },
  { rowId: 3, cells: [{ type: "text", text: "JPY"}, { type: "text", text: "5%"}, { type: "text", text: "4.8%"}, { type: "text", text: "900K"}, { type: "text", text: "1.2M"} ]},
  { rowId: 4, cells: [{ type: "text", text: "GPB"}, { type: "text", text: "6.4%"}, { type: "text", text: "5.5%"}, { type: "text", text: "2M"}, { type: "text", text: "1.2M"}] },
  { rowId: 5, cells: [{ type: "text", text: "AUD"}, { type: "text", text: "9.4%"}, { type: "text", text: "8.1%"}, { type: "text", text: "1.1M"}, { type: "text", text: "1.2M"}] },
  { rowId: 6, cells: [{ type: "text", text: "USD"}, { type: "text", text: "7.2%"}, { type: "text", text: "6.6%"}, { type: "text", text: "1.1M"}, { type: "text", text: "1.2M"}] },
  { rowId: 7, cells: [{ type: "text", text: "SGD"}, { type: "text", text: "4.3%"}, { type: "text", text: "3.9%"}, { type: "text", text: "1.1M"}, { type: "text", text: "025"}] }

];

const DeviseList = () => {

/*  useEffect(() => {
    const handleMouseMove = (e) => {
      const movement = e.movementX;
      setSize(size + movement);
    };

    document.addEventListener("mousemove", handleMouseMove);
    return () => {
      document.removeEventListener("mousemove", handleMouseMove);
    };
  }, [size]); 
  style={{ width: `${size}px` }}
  */



  const [rows, setRows] = useState(initialRows);

  const [zoom, setZoom] = useState(1); // State to control zoom level

  const zoomOut = () => {
    setZoom(prevZoom => Math.max(prevZoom - 0.2, 0.5)); // Prevent zooming out too much
  };

  return (
    <div className="DeviseList" >
      {/* Left Panel */}
      <div className="left-panel">

        <div className="filterbuttons">
        <button className="filter-btn">Filter 1</button>
        <button className="filter-btn">Filter 2</button>
        <button className="filter-btn">Filter 3</button>
        </div>
        <div className="inner-part">
        <div className="grid-container" style={{ transform: `scale(${zoom})`, transformOrigin: "top left" }}>
            <div className="overflow-auto max-h-64 border border-gray-300 rounded-lg">
            <table className="w-full border-collapse">
              {/* Table Head */}
              <thead className="devisetablehead">
                <tr>
                  {columns.map((col) => (
                    <th key={col.columnId} className="p-2 border border-gray-600">
                      {col.title}
                    </th>
                  ))}
                </tr>
              </thead>
              
              {/* Table Body */}
              <tbody>
                {rows.map((row, rowIndex) => (
                  <tr key={rowIndex} className={rowIndex % 2 === 0 ? "bg-gray-100" : "bg-white"}>
                    {row.cells.map((cell, colIndex) => (
                      <td key={colIndex} className="p-2 border border-gray-400 text-center">
                        {cell.text}
                      </td>
                    ))}
                  </tr>
                ))}
              </tbody>
            </table>
          </div>



          {/*Array.from({ length: 12 }).map((_, index) => (
            <div key={index} className="grid-item">Row {Math.floor(index / 4) + 1}, Col {(index % 4) + 1}</div>
          ))*/}
        </div>
        <button className="zoom-out" onClick={zoomOut}>Zoom Out</button>
      </div>
      </div>

    </div>
  );
};

export default DeviseList;